package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import com.arapos.corte.domain.Service.ItemReferenceService;
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

    // Obtener todas las referencias de ítems
    @GetMapping("/all")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getAll() {
        return ResponseEntity.ok(itemReferenceService.getAll());
    }

    // Obtener ítem de referencia por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ItemReferenceResponseDTO> getById(@PathVariable("id") int itemReferenceId) {
        return itemReferenceService.getById(itemReferenceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar ítems de referencia por rango de fechas
    @GetMapping("/created-between")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(itemReferenceService.getByCreatedAtBetween(startDate, endDate));
    }

    // Obtener ítems de referencia por referencia ID
    @GetMapping("/reference/{referenceId}")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getByReferenceId(@PathVariable("referenceId") String referenceId) {
        return ResponseEntity.ok(itemReferenceService.getByReferenceId(referenceId));
    }

    // Obtener ítems de referencia por Op ID
    @GetMapping("/op/{opId}")
    public ResponseEntity<List<ItemReferenceResponseDTO>> getByOpId(@PathVariable("opId") int opId) {
        return ResponseEntity.ok(itemReferenceService.getByOpId(opId));
    }

    // Crear nuevo ítem de referencia
    @PostMapping("/create")
    public ResponseEntity<ItemReferenceResponseDTO> save(@RequestBody CreateItemReferenceDTO createItemReferenceDTO) {
        return new ResponseEntity<>(itemReferenceService.save(createItemReferenceDTO), HttpStatus.CREATED);
    }

    // Actualizar ítem de referencia
    @PutMapping("/update")
    public ResponseEntity<ItemReferenceResponseDTO> update(@RequestBody CreateItemReferenceDTO createItemReferenceDTO) {
        return ResponseEntity.ok(itemReferenceService.update(createItemReferenceDTO));
    }

    // Eliminar ítem de referencia por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int itemReferenceId) {
        return itemReferenceService.delete(itemReferenceId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
