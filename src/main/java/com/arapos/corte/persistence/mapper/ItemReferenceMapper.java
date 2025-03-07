package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.ItemReferenceDTO;
import com.arapos.corte.persistence.entity.ItemReference;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",
        uses = {OpMapper.class, ReferenceMapper.class})
public interface ItemReferenceMapper {
    @Mappings({
            @Mapping(source = "itemReferenceId", target = "itemReferenceId"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "op", target = "op"),
            @Mapping(source = "reference", target = "reference")
    })
    ItemReferenceDTO toItemReferenceDTO(ItemReference itemReference);

    @InheritInverseConfiguration
    ItemReference toItemReference(ItemReferenceDTO itemReferenceDTO);
}
