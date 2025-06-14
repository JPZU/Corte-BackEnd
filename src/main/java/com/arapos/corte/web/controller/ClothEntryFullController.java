package com.arapos.corte.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arapos.corte.domain.Service.ClothEntryFullService;
import com.arapos.corte.domain.dto.ClothEntry.FullClothEntryDTO;

@RestController
@RequestMapping("/cloth-entries")
public class ClothEntryFullController {

    @Autowired
    private ClothEntryFullService clothEntryFullService;

    @PostMapping("/full")
    public ResponseEntity<Void> createFullEntry(@RequestBody FullClothEntryDTO dto) {
        clothEntryFullService.createCompleteEntry(dto.getEntry(), dto.getItems());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
