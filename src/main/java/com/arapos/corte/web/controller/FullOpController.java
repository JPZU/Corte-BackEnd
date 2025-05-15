package com.arapos.corte.web.controller;

import com.arapos.corte.domain.Service.FullOpService;
import com.arapos.corte.domain.dto.Op.FullOpRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/full-op")
public class FullOpController {

    @Autowired
    private FullOpService fullOpService;

    @PostMapping
    public void createFullOp(@Valid @RequestBody FullOpRequestDTO request) {
        fullOpService.createCompletedOperation(
                request.getOp(),
                request.getReferences(),
                request.getCloths()
        );
    }
}
