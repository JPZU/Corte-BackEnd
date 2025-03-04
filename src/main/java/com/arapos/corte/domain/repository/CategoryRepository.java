package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.CategoryDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryDTO> getAll();
    Optional<CategoryDTO> getById(int categoryId);
    Optional<CategoryDTO> getByName(String name);
    List<CategoryDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    CategoryDTO save(CategoryDTO categoryDTO);
    void delete(CategoryDTO categoryDTO);
}
