package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.OpDTO;
import com.arapos.corte.persistence.entity.Op;
import org.mapstruct.InheritInverseConfiguration;
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
    OpDTO toOpDTO(Op op);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "itemClothsList", ignore = true),
        @Mapping(target = "itemReferencesList", ignore = true)
    })
    Op toOp(OpDTO opDTO);
}
