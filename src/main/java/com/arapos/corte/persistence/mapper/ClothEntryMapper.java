package com.arapos.corte.persistence.mapper;
import org.mapstruct.*;

import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;
import com.arapos.corte.persistence.entity.Supplier;
import com.arapos.corte.persistence.entity.User;
import com.arapos.corte.persistence.entity.ClothEntry;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SupplierMapper.class})
public interface ClothEntryMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "clothEntryId", target = "clothEntryId"),
            @Mapping(source = "supplierInvoice", target = "supplierInvoice"),
            @Mapping(source = "notes", target = "notes"),
            @Mapping(source = "approve", target = "approve"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
            @Mapping(source = "user", target = "user"), // Usa UserMapper
            @Mapping(source = "supplier", target = "supplier"), // Usa SupplierMapper
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    ClothEntryResponseDTO toClothEntryResponseDTO(ClothEntry clothEntry);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "clothEntryId", target = "clothEntryId"),
            @Mapping(source = "supplierInvoice", target = "supplierInvoice"),
            @Mapping(source = "notes", target = "notes"),
            @Mapping(source = "approve", target = "approve"),
    /* --------------------------------------------------------
                    relationships entity
    --------------------------------------------------------- */
            @Mapping(target = "clothEntryItemsList", ignore = true), // Evita mapear relaciones OneToMany
            @Mapping(target = "user", expression = "java(mapUser(createClothEntryDTO.getUserId()))"), // Convierte ID a entidad
            @Mapping(target = "supplier", expression = "java(mapSupplier(createClothEntryDTO.getSupplierId()))"),// Convierte ID a entidad
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
    })
    ClothEntry toClothEntry(CreateClothEntryDTO createClothEntryDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */

    default User mapUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    default Supplier mapSupplier(String supplierId) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        return supplier;
    }
}
