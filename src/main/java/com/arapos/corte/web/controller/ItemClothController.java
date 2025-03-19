package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.domain.Service.ItemClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/item-cloths")
public class ItemClothController {

    @Autowired
    private ItemClothService itemClothService;

    // Obtener todos los ítems de tela
    @GetMapping("/all")
    public ResponseEntity<List<ItemClothResponseDTO>> getAll() {
        return ResponseEntity.ok(itemClothService.getAll());
    }

    // Obtener ítem de tela por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ItemClothResponseDTO> getById(@PathVariable("id") int clothId) {
        return itemClothService.getById(clothId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar ítems de tela por rango de fechas
    @GetMapping("/created-between")
    public ResponseEntity<List<ItemClothResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(itemClothService.getByCreatedAtBetween(startDate, endDate));
    }

    // Obtener ítems de tela por Cloth ID
    @GetMapping("/cloth/{clothId}")
    public ResponseEntity<List<ItemClothResponseDTO>> getByClothId(@PathVariable("clothId") int clothId) {
        return ResponseEntity.ok(itemClothService.getByClothId(clothId));
    }

    // Obtener ítems de tela por Op ID
    @GetMapping("/op/{opId}")
    public ResponseEntity<List<ItemClothResponseDTO>> getByOpId(@PathVariable("opId") int opId) {
        return ResponseEntity.ok(itemClothService.getByOpId(opId));
    }

    // Crear nuevo ítem de tela
    @PostMapping("/create")
    public ResponseEntity<ItemClothResponseDTO> save(@RequestBody CreateItemClothDTO createItemClothDTO) {
        return new ResponseEntity<>(itemClothService.save(createItemClothDTO), HttpStatus.CREATED);
    }

    // Actualizar ítem de tela
    @PutMapping("/update")
    public ResponseEntity<ItemClothResponseDTO> update(@RequestBody CreateItemClothDTO createItemClothDTO) {
        return ResponseEntity.ok(itemClothService.update(createItemClothDTO));
    }

    // Eliminar ítem de tela por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int itemClothId) {
        return itemClothService.delete(itemClothId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
