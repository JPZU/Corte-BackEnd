package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.persistence.entity.Op;
import com.arapos.corte.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class})
public interface OpMapper {
    @Mappings({
            @Mapping(source = "opId", target = "opId"),
            @Mapping(source = "totalMeters", target = "totalMeters"),
            @Mapping(source = "quantityCloths", target = "quantityCloths"),
            @Mapping(source = "schemaLength", target = "schemaLength"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "user", target = "user")
    })
    OpResponseDTO toOpResponseDTO(Op op);

    // 📌 Convertir de Create DTO a Entity (para guardar en BD)
    @Mappings({
            @Mapping(source = "totalMeters", target = "totalMeters"),
            @Mapping(source = "quantityCloths", target = "quantityCloths"),
            @Mapping(source = "schemaLength", target = "schemaLength"),
            @Mapping(target = "opId", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "itemReferencesList", ignore = true), // Evita mapear relaciones OneToMany
            @Mapping(target = "itemClothsList", ignore = true),
            @Mapping(target = "user", expression = "java(mapUser(createOpDTO.getUserId()))") // Mapea ID de usuario
    })
    Op toOp(CreateOpDTO createOpDTO);

    // 📌 Método auxiliar para mapear userId a User
    default User mapUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }
}
