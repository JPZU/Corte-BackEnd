package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.persistence.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mappings({
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt")
    })
    SupplierResponseDTO toSupplierResponseDTO(Supplier supplier);

    @Mappings({
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "clothsList", ignore = true),
    })
    Supplier toSupplier(CreateSupplierDTO createSupplierDTO);
}
