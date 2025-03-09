package com.arapos.corte.web.controller;

import com.arapos.corte.domain.Service.SupplierService;
import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Obtener todos los proveedores
    @GetMapping("/all")
    public ResponseEntity<List<SupplierResponseDTO>> getAll() {
        return new ResponseEntity<>(supplierService.getAll(), HttpStatus.OK);
    }

    // Buscar proveedor por ID
    @GetMapping("/id/{supplierId}")
    public ResponseEntity<SupplierResponseDTO> getById(@PathVariable("supplierId") String supplierId) {
        return supplierService.getById(supplierId)
                .map(supplier -> new ResponseEntity<>(supplier, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Buscar proveedor por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierResponseDTO> getByName(@PathVariable("name") String name) {
        return supplierService.getByName(name)
                .map(supplier -> new ResponseEntity<>(supplier, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Filtrar proveedores por rango de fecha de creación
    @GetMapping("/created-between")
    public ResponseEntity<List<SupplierResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(supplierService.getByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
    }

    // Crear un nuevo proveedor
    @PostMapping("/create")
    public ResponseEntity<SupplierResponseDTO> save(@RequestBody CreateSupplierDTO createSupplierDTO) {
        return new ResponseEntity<>(supplierService.save(createSupplierDTO), HttpStatus.CREATED);
    }

    // Actualizar un proveedor
    @PutMapping("/update")
    public ResponseEntity<SupplierResponseDTO> update(@RequestBody CreateSupplierDTO createSupplierDTO) {
        return new ResponseEntity<>(supplierService.update(createSupplierDTO), HttpStatus.OK);
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<Void> delete(@PathVariable("supplierId") String supplierId) {
        if (supplierService.delete(supplierId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
