package co.com.pragma.r2dbc.adapter;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.mapper.UserPersistenceMapper;
import co.com.pragma.r2dbc.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserDataRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> existsByDocumentoIdentidad(String documento) {
        return repository.existsByDocumentoIdentidad(documento);
    }

    @Override
    public Mono<User> save(User user) {
        return repository.save(mapper.toData(user))
                .map(savedUserData -> mapper.toDomain(savedUserData, user.getRol()));
    }
}