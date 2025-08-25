package co.com.pragma.r2dbc.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;

import co.com.pragma.r2dbc.data.UserData;
import reactor.core.publisher.Mono;

public interface UserDataRepository extends R2dbcRepository<UserData, Long> {

    Mono<Boolean> existsByEmail(String email);

}
