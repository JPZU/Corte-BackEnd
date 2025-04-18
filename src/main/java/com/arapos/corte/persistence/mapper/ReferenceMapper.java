package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.persistence.entity.Reference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {

    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "referenceId", target = "referenceId"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    ReferenceResponseDTO toReferenceResponseDTO(Reference reference);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "referenceId", target = "referenceId"),
            @Mapping(source = "description", target = "description"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "itemReferencesList", ignore = true),
    })
    Reference toReference(CreateReferenceDTO createReferenceDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
}
