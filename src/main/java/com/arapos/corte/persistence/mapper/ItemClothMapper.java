package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.persistence.entity.ItemCloth;
import com.arapos.corte.persistence.entity.Op;
import com.arapos.corte.persistence.entity.Cloth;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OpMapper.class, ClothMapper.class})
public interface ItemClothMapper {

    // 📌 Convertir de Entity a Response DTO (para enviar datos completos al front)
    @Mappings({
            @Mapping(source = "itemClothId", target = "itemClothId"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "op", target = "op"), // Usa OpMapper
            @Mapping(source = "cloth", target = "cloth") // Usa ClothMapper
    })
    ItemClothResponseDTO toItemClothResponseDTO(ItemCloth itemCloth);

    // 📌 Convertir de Create DTO a Entity (para guardar en BD)
    @Mappings({
            @Mapping(source = "meters", target = "meters"),
            @Mapping(target = "itemClothId", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "op", expression = "java(mapOp(createItemClothDTO.getOpId()))"), // Asigna el ID a la entidad Op
            @Mapping(target = "cloth", expression = "java(mapCloth(createItemClothDTO.getClothId()))") // Asigna el ID a la entidad Cloth
    })
    ItemCloth toItemCloth(CreateItemClothDTO createItemClothDTO);

    // 📌 Métodos auxiliares para mapear IDs a entidades
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
