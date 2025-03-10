package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.Service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/cloths")
public class ClothController {

    @Autowired
    private ClothService clothService;

    // Obtener todos los Cloths
    @GetMapping("/all")
    public ResponseEntity<List<ClothResponseDTO>> getAll() {
        return ResponseEntity.ok(clothService.getAll());
    }

    // Obtener Cloth por ID
    @GetMapping("/id/{clothId}")
    public ResponseEntity<ClothResponseDTO> getById(@PathVariable int clothId) {
        return clothService.getById(clothId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener Cloth por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<ClothResponseDTO> getByName(@PathVariable String name) {
        return clothService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar Cloths por rango de fechas de creación
    @GetMapping("/created-between")
    public ResponseEntity<List<ClothResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(clothService.getByCreatedAtBetween(startDate, endDate));
    }

    // Obtener Cloths con metros igual a 0
    @GetMapping("/meters-zero")
    public ResponseEntity<List<ClothResponseDTO>> getByMetersEqualsZero() {
        return ResponseEntity.ok(clothService.getByMetersEqualsZero());
    }

    // Obtener Cloths por nombre de proveedor
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ClothResponseDTO>> getBySupplierName(@PathVariable String supplierId) {
        return ResponseEntity.ok(clothService.getBySupplierId(supplierId));
    }

    // Obtener Cloths por nombre de categoría
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ClothResponseDTO>> getByCategoryName(@PathVariable int categoryId) {
        return ResponseEntity.ok(clothService.getByCategoryId(categoryId));
    }

    // Obtener Cloths por ID de usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClothResponseDTO>> getByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(clothService.findByUserId(userId));
    }

    // Crear un nuevo Cloth
    @PostMapping("/create")
    public ResponseEntity<ClothResponseDTO> save(@RequestBody CreateClothDTO createClothDTO) {
        return new ResponseEntity<>(clothService.save(createClothDTO), HttpStatus.CREATED);
    }

    // Actualizar un Cloth existente
    @PutMapping("/update")
    public ResponseEntity<ClothResponseDTO> update(@RequestBody CreateClothDTO createClothDTO) {
        return ResponseEntity.ok(clothService.update(createClothDTO));
    }

    // Eliminar un Cloth por ID
    @DeleteMapping("/delete/{clothId}")
    public ResponseEntity<Void> delete(@PathVariable("clothId") int clothId) {
        return clothService.delete(clothId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Categoría no encontrada
    }
}
