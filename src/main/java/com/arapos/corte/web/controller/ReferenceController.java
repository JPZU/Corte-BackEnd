package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.domain.Service.ReferenceService;
import jakarta.validation.Valid;
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

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @GetMapping("/all")
    public ResponseEntity<List<ReferenceResponseDTO>> getAll() {
        return new ResponseEntity<>(referenceService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReferenceResponseDTO> getById(@PathVariable("id") String referenceId) {
        return referenceService.getById(referenceId)
                .map(reference -> new ResponseEntity<>(reference, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<ReferenceResponseDTO> save(@Valid @RequestBody CreateReferenceDTO createReferenceDTO) {
        return new ResponseEntity<>(referenceService.save(createReferenceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ReferenceResponseDTO> update(@Valid @RequestBody CreateReferenceDTO createReferenceDTO) {
        return new ResponseEntity<>(referenceService.update(createReferenceDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String referenceId) {
        return referenceService.delete(referenceId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @GetMapping("/created-between")
    public ResponseEntity<List<ReferenceResponseDTO>> getByCreatedAtBetween(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {
        return new ResponseEntity<>(referenceService.getByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
}
