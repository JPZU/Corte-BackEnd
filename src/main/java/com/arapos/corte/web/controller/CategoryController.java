package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Category.CreateCategoryDTO;
import com.arapos.corte.domain.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable("id") int categoryId) {
        return categoryService.getById(categoryId)
                .map(categoryDTO -> new ResponseEntity<>(categoryDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody CreateCategoryDTO createCategoryDTO) {
        return new ResponseEntity<>(categoryService.save(createCategoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryResponseDTO> update(@RequestBody CreateCategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.update(categoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int categoryId) {
        return categoryService.delete(categoryId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Categoría no encontrada
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponseDTO> findByName(@PathVariable("name") String name) {
        return categoryService.getByName(name)
                .map(categoryDTO -> new ResponseEntity<>(categoryDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/created-between")
    public ResponseEntity<List<CategoryResponseDTO>> findByCreatedAtBetween(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {
        return new ResponseEntity<>(categoryService.getByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
}
