package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.CreateUserDTO;
import com.arapos.corte.domain.dto.UserResponseDTO;
import com.arapos.corte.persistence.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "userId", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt")
    })
    UserResponseDTO toUserResponseDTO(User user);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role"),
            @Mapping(target = "clothsList", ignore = true),
            @Mapping(target = "opsList", ignore = true)
    })
    User toUser(CreateUserDTO createUserDTO);
}
