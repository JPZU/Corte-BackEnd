package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.persistence.entity.ItemCloth;
import com.arapos.corte.persistence.entity.Op;
import com.arapos.corte.persistence.entity.Cloth;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OpMapper.class, ClothMapper.class})
public interface ItemClothMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "itemClothId", target = "itemClothId"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
            @Mapping(source = "op", target = "op"), // Usa OpMapper
            @Mapping(source = "cloth", target = "cloth") // Usa ClothMapper
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    ItemClothResponseDTO toItemClothResponseDTO(ItemCloth itemCloth);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "itemClothId", target = "itemClothId"),
            @Mapping(source = "meters", target = "meters"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
            @Mapping(target = "op", expression = "java(mapOp(createItemClothDTO.getOpId()))"), // Asigna el ID a la entidad Op
            @Mapping(target = "cloth", expression = "java(mapCloth(createItemClothDTO.getClothId()))"), // Asigna el ID a la entidad Cloth
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
    })
    ItemCloth toItemCloth(CreateItemClothDTO createItemClothDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
    default Op mapOp(int opId) {
        Op op = new Op();
        op.setOpId(opId);
        return op;
    }

    default Cloth mapCloth(int clothId) {
        Cloth cloth = new Cloth();
        cloth.setClothId(clothId);
        return cloth;
    }
}
