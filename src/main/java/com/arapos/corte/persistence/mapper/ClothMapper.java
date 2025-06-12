package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.persistence.entity.Cloth;
import com.arapos.corte.persistence.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ClothMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "clothId", target = "clothId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "isActive", target = "isActive"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
            @Mapping(source = "category", target = "category"), // Usa CategoryMapper
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    ClothResponseDTO toClothResponseDTO(Cloth cloth);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "clothId", target = "clothId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "meters", target = "meters"),
            @Mapping(source = "isActive", target = "isActive"),
    /* --------------------------------------------------------
                    relationships entity
    --------------------------------------------------------- */
            @Mapping(target = "itemClothsList", ignore = true), // Evita mapear relaciones OneToMany
            @Mapping(target = "clothEntryItemsList", ignore = true), // Evita mapear relaciones OneToMany
            @Mapping(target = "category", expression = "java(mapCategory(createClothDTO.getCategoryId()))"), // Convierte ID a entidad
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true), // Se generará automáticamente por la BD
            @Mapping(target = "updatedAt", ignore = true), // Se generará automáticamente por la BD
    })
    Cloth toCloth(CreateClothDTO createClothDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */

    default Category mapCategory(int categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return category;
    }

}
