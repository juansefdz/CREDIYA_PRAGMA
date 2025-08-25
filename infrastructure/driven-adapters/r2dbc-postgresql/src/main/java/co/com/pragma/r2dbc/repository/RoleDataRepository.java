package co.com.pragma.r2dbc.repository;

import co.com.pragma.r2dbc.data.RoleData;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RoleDataRepository extends R2dbcRepository<RoleData, Long> {
}
