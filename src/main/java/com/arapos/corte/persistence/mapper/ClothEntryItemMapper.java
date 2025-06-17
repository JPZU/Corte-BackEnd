package com.arapos.corte.persistence.mapper;
import org.mapstruct.*;

import com.arapos.corte.domain.dto.ClothEntryItem.ClothEntryItemResponseDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;
import com.arapos.corte.persistence.entity.Cloth;
import com.arapos.corte.persistence.entity.ClothEntry;
import com.arapos.corte.persistence.entity.ClothEntryItem;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ClothMapper.class, ClothEntryMapper.class})
public interface ClothEntryItemMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "clothEntryItemId", target = "clothEntryItemId"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "metersAdded", target = "metersAdded"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
            @Mapping(source = "clothEntry", target = "clothEntry"),
            @Mapping(source = "cloth", target = "cloth"),
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    ClothEntryItemResponseDTO toClothEntryItemResponseDTO(ClothEntryItem clothEntryItem);

/* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "clothEntryItemId", target = "clothEntryItemId"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "metersAdded", target = "metersAdded"),
    /* --------------------------------------------------------
                    relationships entity
    --------------------------------------------------------- */
            @Mapping(target = "clothEntry", expression = "java(mapClothEntry(createClothEntryItemDTO.getClothEntryId()))"), // Convierte ID a entidad
            @Mapping(target = "cloth", expression = "java(mapCloth(createClothEntryItemDTO.getClothId()))"),// Convierte ID a entidad
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
    })
    ClothEntryItem toClothEntryItem(CreateClothEntryItemDTO createClothEntryItemDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
    default ClothEntry mapClothEntry(int clothEntryId) {
        ClothEntry clothEntry = new ClothEntry();
        clothEntry.setClothEntryId(clothEntryId);
        return clothEntry;
    }

    default Cloth mapCloth(int clothId) {
        Cloth cloth = new Cloth();
        cloth.setClothId(clothId);
        return cloth;
    }

}
