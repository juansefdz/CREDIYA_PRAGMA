package co.com.pragma.r2dbc.repository;

import co.com.pragma.r2dbc.data.RoleData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RoleDataRepository extends ReactiveCrudRepository<RoleData, Long> {
    Mono<RoleData> findByNombre(String nombre);
}