package co.com.pragma.r2dbc;

import co.com.pragma.r2dbc.mapper.UserPersistenceMapper;
import co.com.pragma.r2dbc.repository.RoleDataRepository;
import org.springframework.stereotype.Repository;
import co.com.pragma.model.role.Role;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.mapper.RolePersistenceMapper;
import co.com.pragma.r2dbc.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserDataRepository userDataRepository;
    private final RoleDataRepository roleDataRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    private final RolePersistenceMapper rolePersistenceMapper;

    @Override
    public Mono<User> save(User user) {
        return Mono.just(userPersistenceMapper.toData(user))
                .flatMap(userDataRepository::save)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Mono<User> findById(Long id) {
        return userDataRepository.findById(id)
                .flatMap(userData -> {
                    Mono<Role> roleMono = roleDataRepository.findById(userData.getIdRol())
                            .map(rolePersistenceMapper::toDomain);

                    return roleMono.map(role -> {
                        User user = userPersistenceMapper.toDomain(userData);
                        user.setRol(role);
                        return user;
                    });
                });
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return userDataRepository.existsByEmail(email);
    }
}