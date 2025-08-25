package co.com.pragma.usecase.user;

import co.com.pragma.model.exceptions.BusinessException;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        log.info("Iniciando caso de uso para registrar usuario con email: {}", user.getEmail());

        return Mono.just(user)
                .flatMap(this::validateRequiredFields)
                .flatMap(this::validateBusinessRules)
                .flatMap(this::validateUniqueEmail)
                .flatMap(userRepository::save)
                .doOnSuccess(u -> log.info("Usuario {} registrado exitosamente", u.getEmail()))
                .doOnError(e -> log.error("Error al registrar usuario: {}", e.getMessage()));
    }

    private Mono<User> validateRequiredFields(User user) {
        if (Objects.isNull(user.getNombre()) || user.getNombre().isBlank() ||
                Objects.isNull(user.getApellido()) || user.getApellido().isBlank() ||
                Objects.isNull(user.getFechaNacimiento()) ||
                Objects.isNull(user.getDireccion()) || user.getDireccion().isBlank() ||
                Objects.isNull(user.getDocumentoIdentidad()) || user.getDocumentoIdentidad().isBlank() ||
                Objects.isNull(user.getEmail()) || user.getEmail().isBlank() ||
                Objects.isNull(user.getSalarioBase())) {
            return Mono.error(new BusinessException(
                    "Error de validación: Los campos nombre, apellido, fecha de nacimiento, dirección, documento de identidad, email y salario son obligatorios."));
        }
        return Mono.just(user);
    }

    private Mono<User> validateBusinessRules(User user) {
        if (user.getSalarioBase().compareTo(BigDecimal.ZERO) < 0 ||
                user.getSalarioBase().compareTo(new BigDecimal("15000000")) > 0) {
            return Mono.error(
                    new BusinessException("Error de validación: El salario base debe estar entre 0 y 15,000,000."));
        }
        return Mono.just(user);
    }

    private Mono<User> validateUniqueEmail(User user) {
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new BusinessException(
                                "Error de validación: El correo electrónico ya está registrado."));
                    }
                    return Mono.just(user);
                });
    }
}