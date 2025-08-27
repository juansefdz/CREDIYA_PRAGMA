package co.com.pragma.usecase.user;

import co.com.pragma.model.exceptions.BusinessException;
import co.com.pragma.model.exceptions.DocumentAlreadyExistsException;
import co.com.pragma.model.exceptions.EmailAlreadyExistsException;
import co.com.pragma.model.exceptions.InvalidSalaryException;
import co.com.pragma.model.role.gateways.RoleRepository;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final BigDecimal MAX_SALARY = new BigDecimal("15000000");

    public Mono<User> execute(User userDraft, String roleId) {
        log.info("Iniciando CU crear usuario, email hash={}", userDraft.getEmail().hashCode());

        return roleRepository.findById(roleId)
                .switchIfEmpty(Mono.error(new BusinessException("El rol con id '" + roleId + "' no existe.")))
                .map(userDraft::withRol)
                .flatMap(this::validateAllRules)
                .flatMap(userRepository::save)
                .doOnSuccess(u -> log.info("Usuario creado ok, id={}, rol={}", u.getId(), u.getRol().getNombre()))
                .doOnError(e -> log.error("Error creando usuario: {}", e.getMessage()));
    }

    private Mono<User> validateAllRules(User user) {
        // La validación de salario es síncrona, puede ir primero.
        if (user.getSalarioBase().compareTo(BigDecimal.ZERO) < 0 || user.getSalarioBase().compareTo(MAX_SALARY) > 0) {
            return Mono.error(new InvalidSalaryException("El salario base debe estar entre 0 y 15,000,000."));
        }
        Mono<Boolean> emailExists = userRepository.existsByEmail(user.getEmail());
        Mono<Boolean> documentExists = userRepository.existsByDocumentoIdentidad(user.getDocumentoIdentidad());

        return Mono.zip(emailExists, documentExists)
                .flatMap(tuple -> {
                    boolean emailTaken = tuple.getT1();
                    boolean documentTaken = tuple.getT2();

                    if (emailTaken) {
                        return Mono.error(new EmailAlreadyExistsException("El correo electrónico ya está registrado."));
                    }
                    if (documentTaken) {
                        return Mono.error(new DocumentAlreadyExistsException("El documento de identidad ya está registrado."));
                    }
                    return Mono.just(user);
                });
    }
}