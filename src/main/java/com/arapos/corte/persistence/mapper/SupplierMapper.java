package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.persistence.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    SupplierResponseDTO toSupplierResponseDTO(Supplier supplier);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "name", target = "name"),
    /* --------------------------------------------------------
                    relationships entity
    --------------------------------------------------------- */
            @Mapping(target = "clothEntriesList", ignore = true),
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    Supplier toSupplier(CreateSupplierDTO createSupplierDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
}
