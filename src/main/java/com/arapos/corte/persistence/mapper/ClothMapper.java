package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.persistence.entity.Cloth;
import com.arapos.corte.persistence.entity.Category;
import com.arapos.corte.persistence.entity.Supplier;
import com.arapos.corte.persistence.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, SupplierMapper.class})
public interface ClothMapper {

    // Convertir de Entity a Response DTO (para enviar datos completos al front)
    @Mappings({
            @Mapping(source = "clothId", target = "clothId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(source = "user", target = "user"), // Usa UserMapper
            @Mapping(source = "category", target = "category"), // Usa CategoryMapper
            @Mapping(source = "supplier", target = "supplier") // Usa SupplierMapper
    })
    ClothResponseDTO toClothResponseDTO(Cloth cloth);

    // Convertir de Create DTO a Entity (para guardar en BD)
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(target = "clothId", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "itemClothsList", ignore = true), // Evita mapear relaciones OneToMany
            @Mapping(target = "user", expression = "java(mapUser(createClothDTO.getUserId()))"), // Convierte ID a entidad
            @Mapping(target = "category", expression = "java(mapCategory(createClothDTO.getCategoryId()))"), // Convierte ID a entidad
            @Mapping(target = "supplier", expression = "java(mapSupplier(createClothDTO.getSupplierId()))") // Convierte ID a entidad
    })
    Cloth toCloth(CreateClothDTO createClothDTO);

    // 📌 Métodos auxiliares para mapear IDs a entidades
    default User mapUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    default Category mapCategory(int categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return category;
    }

    default Supplier mapSupplier(String supplierId) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        return supplier;
    }
}
