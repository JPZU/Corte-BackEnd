package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.domain.Service.OpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ops")
public class OpController {

    @Autowired
    private OpService opService;

    // Obtener todas las operaciones
    @GetMapping("/all")
    public ResponseEntity<List<OpResponseDTO>> getAll() {
        return ResponseEntity.ok(opService.getAll());
    }

    // Obtener operación por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<OpResponseDTO> getById(@PathVariable("id") int userId) {
        return opService.getById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar operaciones por rango de fechas
    @GetMapping("/created-between")
    public ResponseEntity<List<OpResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(opService.getByCreatedAtBetween(startDate, endDate));
    }

    // Obtener operaciones por User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OpResponseDTO>> getByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(opService.getByUserId(userId));
    }

    // Crear nueva operación
    @PostMapping("/create")
    public ResponseEntity<OpResponseDTO> save(@RequestBody CreateOpDTO createOpDTO) {
        return new ResponseEntity<>(opService.save(createOpDTO), HttpStatus.CREATED);
    }

    // Actualizar operación
    @PutMapping("/update")
    public ResponseEntity<OpResponseDTO> update(@RequestBody CreateOpDTO createOpDTO) {
        return ResponseEntity.ok(opService.update(createOpDTO));
    }

    // Eliminar operación por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int opId) {
        return opService.delete(opId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
