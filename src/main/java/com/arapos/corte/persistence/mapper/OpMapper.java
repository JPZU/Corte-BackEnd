package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.persistence.entity.Op;
import com.arapos.corte.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OpMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
        @Mapping(source = "opId", target = "opId"),
        @Mapping(source = "totalMeters", target = "totalMeters"),
        @Mapping(source = "quantityCloths", target = "quantityCloths"),
        @Mapping(source = "schemaLength", target = "schemaLength"),
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
                @Mapping(source = "user", target = "user")
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */

    })
    OpResponseDTO toOpResponseDTO(Op op);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
        @Mapping(source = "opId", target = "opId"), // Para poder actualizar
        @Mapping(source = "quantityCloths", target = "quantityCloths"),
        @Mapping(source = "schemaLength", target = "schemaLength"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
        @Mapping(target = "user", expression = "java(mapUser(createOpDTO.getUserId()))"), // Mapea ID de usuario
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
        @Mapping(target = "totalMeters", ignore = true), // se calcula en el servicio
        @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
        @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
        @Mapping(target = "itemReferencesList", ignore = true), // Evita mapear relaciones OneToMany
        @Mapping(target = "itemClothsList", ignore = true),
    })
    Op toOp(CreateOpDTO createOpDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
    default User mapUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }
}
