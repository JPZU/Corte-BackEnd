package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.persistence.entity.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryResponseDTO> getAll();
    Optional<CategoryResponseDTO> getById(int categoryId);
    Optional<CategoryResponseDTO> getByName(String name);
    List<CategoryResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    CategoryResponseDTO save(Category category);
    void delete(Category category);
}
