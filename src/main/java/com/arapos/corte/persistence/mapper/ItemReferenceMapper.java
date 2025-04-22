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

    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "itemReferenceId", target = "itemReferenceId"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
            @Mapping(source = "op", target = "op"),
            @Mapping(source = "reference", target = "reference")
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    ItemReferenceResponseDTO toItemReferenceResponseDTO(ItemReference itemReference);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "itemReferenceId",target = "itemReferenceId"),
    /* --------------------------------------------------------
                    relationships entity
    --------------------------------------------------------- */
            @Mapping(target = "op", expression = "java(mapOp(createItemReferenceDTO.getOpId()))"),
            @Mapping(target = "reference", expression = "java(mapReference(createItemReferenceDTO.getReferenceId()))"),
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    ItemReference toItemReference(CreateItemReferenceDTO createItemReferenceDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
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
