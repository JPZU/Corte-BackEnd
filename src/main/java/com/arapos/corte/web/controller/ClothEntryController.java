package com.arapos.corte.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arapos.corte.domain.Service.ClothEntryService;
import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cloths_entry")
public class ClothEntryController {

    @Autowired
    private ClothEntryService clothEntryService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @GetMapping("/all")
    public ResponseEntity<List<ClothEntryResponseDTO>> getAll() {
        return ResponseEntity.ok(clothEntryService.getAll());
    }

    @GetMapping("/id/{clothEntryId}")
    public ResponseEntity<ClothEntryResponseDTO> getById(@PathVariable int clothEntryId) {
        return clothEntryService.getById(clothEntryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ClothEntryResponseDTO> save(@Valid @RequestBody CreateClothEntryDTO createClothEntryDTO) {
        return new ResponseEntity<>(clothEntryService.save(createClothEntryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ClothEntryResponseDTO> update(@Valid @RequestBody CreateClothEntryDTO createClothEntryDTO) {
        return ResponseEntity.ok(clothEntryService.update(createClothEntryDTO));
    }

    @DeleteMapping("/delete/{clothEntryId}")
    public ResponseEntity<Void> delete(@PathVariable("clothEntryId") int clothEntryId) {
        return clothEntryService.delete(clothEntryId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Categoría no encontrada
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */

    @GetMapping("/supplier-invoice/{supplierInvoice}")
    public ResponseEntity<List<ClothEntryResponseDTO>> getBySupplierInvoice(@PathVariable String supplierInvoice) {
        return ResponseEntity.ok(clothEntryService.findBySupplierInvoice(supplierInvoice));
    }

    @GetMapping("/created-between")
    public ResponseEntity<List<ClothEntryResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(clothEntryService.findByCreatedAtBetween(startDate, endDate));
    }

    @GetMapping("/paged")
    public ResponseEntity<Map<String, Object>> getAllPagedClothEntry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size
    ) {
        Page<ClothEntryResponseDTO> clothEntryPage = clothEntryService.getAllPagedClothEntry(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", clothEntryPage.getContent());
        response.put("currentPage", clothEntryPage.getNumber());
        response.put("totalItems", clothEntryPage.getTotalElements());
        response.put("totalPages", clothEntryPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ClothEntryResponseDTO>> getBySupplierName(@PathVariable String supplierId) {
        return ResponseEntity.ok(clothEntryService.findBySupplierId(supplierId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClothEntryResponseDTO>> getByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(clothEntryService.findByUserId(userId));
    }

}
