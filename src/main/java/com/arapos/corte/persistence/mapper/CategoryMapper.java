package com.arapos.corte.persistence.mapper;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Category.CreateCategoryDTO;
import com.arapos.corte.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    Entity -> ResponseDTO
    @Mappings({
            @Mapping(source = "categoryId", target = "categoryId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt")
    })
    CategoryResponseDTO toCategoryResponseDTO(Category category);

//    CreateDTO -> Entity
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(target = "categoryId", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "clothsList", ignore = true)
    })
    Category toCategory(CreateCategoryDTO createCategoryDTO);
}
