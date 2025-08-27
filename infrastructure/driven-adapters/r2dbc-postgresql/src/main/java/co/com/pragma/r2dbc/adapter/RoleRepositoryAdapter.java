package co.com.pragma.r2dbc.adapter;

import co.com.pragma.model.role.Role;
import co.com.pragma.model.role.gateways.RoleRepository;
import co.com.pragma.r2dbc.mapper.RolePersistenceMapper;
import co.com.pragma.r2dbc.repository.RoleDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleDataRepository repository;
    private final RolePersistenceMapper mapper;

    @Override
    public Mono<Role> findById(String id) {
        Long pk = Long.valueOf(id);
        return repository.findById(pk)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Role> findByNombre(String nombre) {
        return repository.findByNombre(nombre)
                .map(mapper::toDomain);
    }
}
