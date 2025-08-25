package co.com.pragma.r2dbc.mapper;

import co.com.pragma.model.user.User;
import co.com.pragma.r2dbc.data.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    @Mapping(target = "idRol", source = "rol.id")
    UserData toData(User user);
    @Mapping(target = "rol", ignore = true)
    User toDomain(UserData userData);
}