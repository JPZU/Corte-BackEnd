package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Category.CreateCategoryDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryResponseDTO> getAll();
    Optional<CategoryResponseDTO> getById(int categoryId);
    Optional<CategoryResponseDTO> getByName(String name);
    List<CategoryResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    CategoryResponseDTO save(CreateCategoryDTO category);
    void delete(int categoryId);
}
