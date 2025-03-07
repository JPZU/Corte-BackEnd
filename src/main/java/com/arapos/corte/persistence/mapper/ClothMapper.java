package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.ClothDTO;
import com.arapos.corte.persistence.entity.Cloth;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, CategoryMapper.class, SupplierMapper.class})
public interface ClothMapper {
    @Mappings({
            @Mapping(source = "clothId", target = "clothId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "supplier", target = "supplier"),
    })
    ClothDTO toClothDTO(Cloth cloth);

    @InheritInverseConfiguration
    @Mapping(target = "itemClothsList", ignore = true)
    Cloth toCloth(ClothDTO clothDTO);
}
