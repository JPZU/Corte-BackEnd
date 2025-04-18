package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Category.CreateCategoryDTO;
import com.arapos.corte.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    /* --------------------------------------------------------
                    ENTITY -> RESPONSEDTO
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
        @Mapping(source = "categoryId", target = "categoryId"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
    })
    CategoryResponseDTO toCategoryResponseDTO(Category category);

    /* --------------------------------------------------------
                    CREATEDTO -> ENTITY
    --------------------------------------------------------- */
    @Mappings({
    /* --------------------------------------------------------
                        mapped
    --------------------------------------------------------- */
            @Mapping(source = "categoryId", target = "categoryId"),
            @Mapping(source = "name", target = "name"),
    /* --------------------------------------------------------
                    relationships
    --------------------------------------------------------- */
    /* --------------------------------------------------------
                        unmapped
    --------------------------------------------------------- */
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "clothsList", ignore = true)
    })
    Category toCategory(CreateCategoryDTO createCategoryDTO);

    /* --------------------------------------------------------
                AUXILIARY METHODS TO MAPPER ENTITIES
    --------------------------------------------------------- */
}
