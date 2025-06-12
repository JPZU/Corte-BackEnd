package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.persistence.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    UserResponseDTO toUserResponseDTO(User user);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "userId",target = "userId"), // Se generará automáticamente por la BD
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role"),
    /* --------------------------------------------------------
                    relationships entity
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "opsList", ignore = true), // Evita mapear relaciones OneToMany
            @Mapping(target = "clothEntriesList", ignore = true)
    })
    User toUser(CreateUserDTO createUserDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
}
