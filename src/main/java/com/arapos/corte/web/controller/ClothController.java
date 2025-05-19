package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.Service.ClothService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cloths")
public class ClothController {

    @Autowired
    private ClothService clothService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @GetMapping("/all")
    public ResponseEntity<List<ClothResponseDTO>> getAll() {
        return ResponseEntity.ok(clothService.getAll());
    }

    @GetMapping("/id/{clothId}")
    public ResponseEntity<ClothResponseDTO> getById(@PathVariable int clothId) {
        return clothService.getById(clothId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ClothResponseDTO> save(@Valid @RequestBody CreateClothDTO createClothDTO) {
        return new ResponseEntity<>(clothService.save(createClothDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ClothResponseDTO> update(@Valid @RequestBody CreateClothDTO createClothDTO) {
        return ResponseEntity.ok(clothService.update(createClothDTO));
    }

    @DeleteMapping("/delete/{clothId}")
    public ResponseEntity<Void> delete(@PathVariable("clothId") int clothId) {
        return clothService.delete(clothId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Categoría no encontrada
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @GetMapping("/name/{name}")
    public ResponseEntity<ClothResponseDTO> getByName(@PathVariable String name) {
        return clothService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/created-between")
    public ResponseEntity<List<ClothResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(clothService.getByCreatedAtBetween(startDate, endDate));
    }

    @GetMapping("/is-active")
    public ResponseEntity<List<ClothResponseDTO>> getIsActive() {
        return ResponseEntity.ok(clothService.getIsActive());
    }

    @GetMapping("/is-not-active")
    public ResponseEntity<List<ClothResponseDTO>> getIsNotActive() {
        return ResponseEntity.ok(clothService.getIsNotActive());
    }

    @GetMapping("/supplier-invoice/{supplierInvoice}")
    public ResponseEntity<List<ClothResponseDTO>> getBySupplierInvoice(@PathVariable String supplierInvoice) {
        return ResponseEntity.ok(clothService.getBySupplierInvoice(supplierInvoice));
    }

    @GetMapping("/paged")
    public ResponseEntity<Map<String, Object>> getAllClothsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ClothResponseDTO> clothPage = clothService.getAllPagedCloths(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", clothPage.getContent());
        response.put("currentPage", clothPage.getNumber());
        response.put("totalItems", clothPage.getTotalElements());
        response.put("totalPages", clothPage.getTotalPages());

        return ResponseEntity.ok(response);
    }


    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ClothResponseDTO>> getBySupplierName(@PathVariable String supplierId) {
        return ResponseEntity.ok(clothService.getBySupplierId(supplierId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ClothResponseDTO>> getByCategoryName(@PathVariable int categoryId) {
        return ResponseEntity.ok(clothService.getByCategoryId(categoryId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClothResponseDTO>> getByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(clothService.findByUserId(userId));
    }
}
