package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.domain.Service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/references")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    // 🔹 Obtener todas las referencias
    @GetMapping("/all")
    public ResponseEntity<List<ReferenceResponseDTO>> getAll() {
        return new ResponseEntity<>(referenceService.getAll(), HttpStatus.OK);
    }

    // 🔹 Obtener referencia por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ReferenceResponseDTO> getById(@PathVariable("id") String referenceId) {
        return referenceService.getById(referenceId)
                .map(reference -> new ResponseEntity<>(reference, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 🔹 Filtrar referencias por rango de fecha de creación
    @GetMapping("/created-between")
    public ResponseEntity<List<ReferenceResponseDTO>> getByCreatedAtBetween(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {
        return new ResponseEntity<>(referenceService.getByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
    }

    // 🔹 Crear una nueva referencia
    @PostMapping("/create")
    public ResponseEntity<ReferenceResponseDTO> save(@RequestBody CreateReferenceDTO createReferenceDTO) {
        return new ResponseEntity<>(referenceService.save(createReferenceDTO), HttpStatus.CREATED);
    }

    // 🔹 Actualizar una referencia existente
    @PutMapping("/update")
    public ResponseEntity<ReferenceResponseDTO> update(@RequestBody CreateReferenceDTO createReferenceDTO) {
        return new ResponseEntity<>(referenceService.update(createReferenceDTO), HttpStatus.OK);
    }

    // 🔹 Eliminar una referencia por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String referenceId) {
        return referenceService.delete(referenceId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
