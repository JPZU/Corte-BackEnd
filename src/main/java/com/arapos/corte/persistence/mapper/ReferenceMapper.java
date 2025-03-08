package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.persistence.entity.Reference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {

    // 📌 Convertir de Entity a ReferenceResponse
    @Mappings({
            @Mapping(source = "referenceId", target = "referenceId"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    })
    ReferenceResponseDTO toReferenceResponseDTO(Reference reference);

    // 📌 Convertir de Create DTO a Entity (para guardar en BD)
    @Mappings({
            @Mapping(source = "referenceId", target = "referenceId"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "itemReferencesList", ignore = true),
    })
    Reference toReference(CreateReferenceDTO createReferenceDTO);
}
