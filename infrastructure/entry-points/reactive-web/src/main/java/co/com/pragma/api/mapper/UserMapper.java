package co.com.pragma.api.mapper;

import org.mapstruct.Mapper;

import co.com.pragma.api.dto.UserRequestDTO;
import co.com.pragma.model.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDTO(UserRequestDTO dto);
}