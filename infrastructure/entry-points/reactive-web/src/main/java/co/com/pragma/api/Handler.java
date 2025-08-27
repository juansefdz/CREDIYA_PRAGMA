package co.com.pragma.api;

import co.com.pragma.api.dto.UserRequestDTO;
import co.com.pragma.api.mapper.UserApiMapper;
import co.com.pragma.usecase.user.UserUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Handler {

    private final UserUseCase userUseCase;
    private final UserApiMapper userApiMapper;
    private final Validator validator;

    public Mono<ServerResponse> registrarUsuario(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(this::validateDTO)
                .flatMap(dto -> {
                    var user = userApiMapper.fromDTO(dto);
                    var roleId = String.valueOf(dto.getIdRol());
                    return userUseCase.execute(user, roleId);
                })
                .flatMap(user ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(user)
                );
    }


    private Mono<UserRequestDTO> validateDTO(UserRequestDTO dto) {
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            return Mono.error(new ServerWebInputException(violations.toString()));
        }
        return Mono.just(dto);
    }
}