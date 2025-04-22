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

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @GetMapping("/all")
    public ResponseEntity<List<OpResponseDTO>> getAll() {
        return ResponseEntity.ok(opService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OpResponseDTO> getById(@PathVariable("id") int userId) {
        return opService.getById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<OpResponseDTO> save(@RequestBody CreateOpDTO createOpDTO) {
        return new ResponseEntity<>(opService.save(createOpDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<OpResponseDTO> update(@RequestBody CreateOpDTO createOpDTO) {
        return ResponseEntity.ok(opService.update(createOpDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int opId) {
        return opService.delete(opId)
                ? new ResponseEntity<>(HttpStatus.OK) // Eliminación exitosa
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @GetMapping("/created-between")
    public ResponseEntity<List<OpResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(opService.getByCreatedAtBetween(startDate, endDate));
    }

    @GetMapping("/consecutive/{number}")
    public ResponseEntity<OpResponseDTO> getByConsecutiveNumber(@PathVariable("number") int number) {
        return opService.getByConsecutiveNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OpResponseDTO>> getByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(opService.getByUserId(userId));
    }
}