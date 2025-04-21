package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Category.CreateCategoryDTO;
import com.arapos.corte.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */

    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<CategoryResponseDTO> getById(int categoryId) {
        return categoryRepository.getById(categoryId);
    }

    public CategoryResponseDTO save(CreateCategoryDTO createCategoryDTO) {
        return categoryRepository.save(createCategoryDTO);
    }

    public CategoryResponseDTO update(CreateCategoryDTO categoryDTO) {
        return categoryRepository.update(categoryDTO);
    }

    public boolean delete(int categoryId) {
        if (categoryRepository.getById(categoryId).isPresent()) {
            categoryRepository.delete(categoryId);
            return true;
        } else {
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */

    public Optional<CategoryResponseDTO> getByName(String name) {
        return categoryRepository.getByName(name);
    }

    public List<CategoryResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return categoryRepository.findByCreatedAtBetween(startDate, endDate);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
}
