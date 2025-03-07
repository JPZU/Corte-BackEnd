package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.ItemClothDTO;
import com.arapos.corte.persistence.entity.ItemCloth;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",
        uses = {ClothMapper.class, OpMapper.class})
public interface ItemClothMapper {
    @Mappings({
            @Mapping(source = "itemClothId", target = "itemClothId"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "cloth", target = "cloth"),
            @Mapping(source = "op", target = "op")
    })
    ItemClothDTO toItemClothDTO(ItemCloth itemCloth);

    @InheritInverseConfiguration
    ItemCloth toItemCloth(ItemClothDTO itemClothDTO);
}
