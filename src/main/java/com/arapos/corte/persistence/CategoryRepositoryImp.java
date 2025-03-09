package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Category.CreateCategoryDTO;
import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.repository.CategoryRepository;
import com.arapos.corte.persistence.crud.CategoryCrudRepository;
import com.arapos.corte.persistence.entity.Category;
import com.arapos.corte.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class CategoryRepositoryImp implements CategoryRepository {

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> getAll() {
        Iterable<Category>  categories = categoryCrudRepository.findAll();
        return StreamSupport.stream(categories.spliterator(), false)
                .map(categoryMapper::toCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryResponseDTO> getById(int categoryId) {
        return categoryCrudRepository.findById(categoryId)
                .map(categoryMapper::toCategoryResponseDTO);
    }

    @Override
    public Optional<CategoryResponseDTO> getByName(String name) {
        return categoryCrudRepository.findByName(name)
                .map(categoryMapper::toCategoryResponseDTO);
    }

    @Override
    public List<CategoryResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        Iterable<Category>  categories = categoryCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(categories.spliterator(), false)
                .map(categoryMapper::toCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO save(CreateCategoryDTO  createCategoryDTO){
        // Verificamos si el ID ya existe en la BD
        if (createCategoryDTO.getCategoryId() != 0) {
            throw new IllegalArgumentException("ID cannot be present for create a new category.");
        }
        Category categoryEntity = categoryMapper.toCategory(createCategoryDTO);
        Category savedCategory = categoryCrudRepository.save(categoryEntity);
        return categoryMapper.toCategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO update(CreateCategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryCrudRepository.findById(categoryDTO.getCategoryId());

        if (existingCategory.isPresent()) {
            Category categoryToUpdate = existingCategory.get();
            categoryToUpdate.setName(categoryDTO.getName()); // ✅ Actualiza solo el nombre
            Category updatedCategory = categoryCrudRepository.save(categoryToUpdate);
            return categoryMapper.toCategoryResponseDTO(updatedCategory);
        } else {
            throw new RuntimeException("Category not found"); // Manejo de error
        }
    }

    @Override
    public void delete(int categoryId) {
        if (categoryCrudRepository.existsById(categoryId)) {
            categoryCrudRepository.deleteById(categoryId);
        } else {
            throw new RuntimeException("Category with ID " + categoryId + " not found");
        }
    }
}
