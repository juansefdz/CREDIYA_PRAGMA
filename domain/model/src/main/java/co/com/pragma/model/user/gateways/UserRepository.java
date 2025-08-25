package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<User> findById(Long id);

    // validación si el correo electrónico existe (criterio de aceptación)
    Mono<Boolean> existsByEmail (String email);
}
