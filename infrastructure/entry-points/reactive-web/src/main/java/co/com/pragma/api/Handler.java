package co.com.pragma.api;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import co.com.pragma.api.mapper.UserMapper;
import co.com.pragma.usecase.user.UserUseCase;

@Component
@RequiredArgsConstructor
public class Handler {

    private final UserMapper userMapper;
    private final UserUseCase userUseCase;

}
