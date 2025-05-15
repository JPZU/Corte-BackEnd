package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import com.arapos.corte.domain.Service.ItemReferenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/item-references")
public class ItemReferenceController {

    @Autowired
    private ItemReferenceService itemReferenceService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @GetMapping("/all")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getAll() {
        return ResponseEntity.ok(itemReferenceService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ItemReferenceResponseDTO> getById(@PathVariable("id") int itemReferenceId) {
        return itemReferenceService.getById(itemReferenceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ItemReferenceResponseDTO> update(@Valid @RequestBody CreateItemReferenceDTO createItemReferenceDTO) {
        return ResponseEntity.ok(itemReferenceService.update(createItemReferenceDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int itemReferenceId) {
        return itemReferenceService.delete(itemReferenceId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @GetMapping("/created-between")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(itemReferenceService.getByCreatedAtBetween(startDate, endDate));
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @GetMapping("/reference/{referenceId}")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getByReferenceId(@PathVariable("referenceId") String referenceId) {
        return ResponseEntity.ok(itemReferenceService.getByReferenceId(referenceId));
    }

    @GetMapping("/op/{opId}")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getByOpId(@PathVariable("opId") int opId) {
        return ResponseEntity.ok(itemReferenceService.getByOpId(opId));
    }
}
