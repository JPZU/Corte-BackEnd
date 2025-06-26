package com.arapos.corte.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.arapos.corte.domain.Service.ClothEntryItemService;
import com.arapos.corte.domain.dto.ClothEntryItem.ClothEntryItemResponseDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cloths-entry-item")
public class ClothEntryItemController {

    @Autowired
    private ClothEntryItemService clothEntryItemService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */

    @GetMapping("/all")
    public ResponseEntity<List<ClothEntryItemResponseDTO>> getAll() {
        return ResponseEntity.ok(clothEntryItemService.getAll());
    }

    @GetMapping("/id/{clothEntryItemId}")
    public ResponseEntity<ClothEntryItemResponseDTO> getById(@PathVariable int clothEntryItemId) {
        return clothEntryItemService.getById(clothEntryItemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ClothEntryItemResponseDTO> save(@Valid @RequestBody CreateClothEntryItemDTO createClothEntryItemDTO) {
        return new ResponseEntity<>(clothEntryItemService.save(createClothEntryItemDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{clothEntryItemId}")
    public ResponseEntity<Void> delete(@PathVariable("clothEntryItemId") int clothEntryItemId) {
        return clothEntryItemService.delete(clothEntryItemId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Categoría no encontrada
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @GetMapping("/created-between")
    public ResponseEntity<List<ClothEntryItemResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(clothEntryItemService.findByCreatedAtBetween(startDate, endDate));
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @GetMapping("/cloth-entry/{clothEntryId}")
    public ResponseEntity<List<ClothEntryItemResponseDTO>> getByClothEntryId(@PathVariable int clothEntryId) {
        return ResponseEntity.ok(clothEntryItemService.findByClothEntryId(clothEntryId));
    }

    @GetMapping("/cloth/{clothId}")
    public ResponseEntity<List<ClothEntryItemResponseDTO>> getByClothyId(@PathVariable int clothId) {
        return ResponseEntity.ok(clothEntryItemService.findByClothId(clothId));
    }

}
