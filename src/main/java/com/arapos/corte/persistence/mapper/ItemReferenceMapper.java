package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import com.arapos.corte.persistence.entity.ItemReference;
import com.arapos.corte.persistence.entity.Op;
import com.arapos.corte.persistence.entity.Reference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",
        uses = {OpMapper.class, ReferenceMapper.class})
public interface ItemReferenceMapper {

    // 📌 Convertir de Entity a Response DTO (para enviar datos completos al front)
    @Mappings({
            @Mapping(source = "itemReferenceId", target = "itemReferenceId"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "op", target = "op"),
            @Mapping(source = "reference", target = "reference")
    })
    ItemReferenceResponseDTO toItemReferenceResponseDTO(ItemReference itemReference);

    // 📌 Convertir de Create DTO a Entity (para guardar en BD)
    @Mappings({
            @Mapping(source = "itemReferenceId",target = "itemReferenceId"),
            @Mapping(target = "op", expression = "java(mapOp(createItemReferenceDTO.getOpId()))"),
            @Mapping(target = "reference", expression = "java(mapReference(createItemReferenceDTO.getReferenceId()))")
    })
    ItemReference toItemReference(CreateItemReferenceDTO createItemReferenceDTO);

    // 📌 Métodos auxiliares para mapear IDs a entidades
    default Op mapOp(int opId) {
        Op op = new Op();
        op.setOpId(opId);
        return op;
    }

    default Reference mapReference(String referenceId) {
        Reference reference = new Reference();
        reference.setReferenceId(referenceId);
        return reference;
    }
}
