package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.SupplierDTO;
import com.arapos.corte.persistence.entity.Supplier;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mappings({
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "user", target = "user")
    })
    SupplierDTO toSupplierDTO(Supplier supplier);

    @InheritInverseConfiguration
    @Mapping(target = "clothsList", ignore = true)
    Supplier toSupplier(SupplierDTO supplierDTO);
}
