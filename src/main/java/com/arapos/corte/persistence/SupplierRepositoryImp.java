package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.domain.repository.SupplierRepository;
import com.arapos.corte.persistence.crud.SupplierCrudRepository;
import com.arapos.corte.persistence.entity.Supplier;
import com.arapos.corte.persistence.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class SupplierRepositoryImp implements SupplierRepository {

    @Autowired
    private SupplierCrudRepository supplierCrudRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<SupplierResponseDTO> getAll(){
        Iterable<Supplier> suppliers = supplierCrudRepository.findAll();
        return StreamSupport.stream(suppliers.spliterator(), false)
                .map(supplierMapper::toSupplierResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierResponseDTO> getById(String supplierId){
        return supplierCrudRepository.findById(supplierId)
                .map(supplierMapper::toSupplierResponseDTO);
    }

    @Override
    public Optional<SupplierResponseDTO> getByName(String supplierId){
        return supplierCrudRepository.findByName(supplierId)
                .map(supplierMapper::toSupplierResponseDTO);
    }

    @Override
    public List<SupplierResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<Supplier> suppliers = supplierCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(suppliers.spliterator(), false)
                .map(supplierMapper::toSupplierResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDTO save(CreateSupplierDTO createSupplierDTO){
        if (createSupplierDTO.getSupplierId() == null || createSupplierDTO.getSupplierId().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID is required for creating a new supplier.");
        }

        Supplier supplierEntity = supplierMapper.toSupplier(createSupplierDTO);
        Supplier savedSupplier = supplierCrudRepository.save(supplierEntity);
        return supplierMapper.toSupplierResponseDTO(savedSupplier);
    }


    @Override
    public SupplierResponseDTO update(CreateSupplierDTO createSupplierDTO) {
        if (createSupplierDTO.getSupplierId() == null || createSupplierDTO.getSupplierId().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID is required for updating a supplier.");
        }

        Optional<Supplier> existingSupplier = supplierCrudRepository.findById(createSupplierDTO.getSupplierId());

        if (existingSupplier.isPresent()) {
            Supplier supplierToUpdate = existingSupplier.get();
            supplierToUpdate.setName(createSupplierDTO.getName()); // Actualiza solo el nombre

            Supplier updatedSupplier = supplierCrudRepository.save(supplierToUpdate); // 🔹 Guarda la actualización en BD
            return supplierMapper.toSupplierResponseDTO(updatedSupplier);
        } else {
            throw new IllegalArgumentException("Supplier not found.");
        }
    }


    @Override
    public void delete(String supplierId){
        if(supplierCrudRepository.existsById(supplierId)){
            supplierCrudRepository.deleteById(supplierId);
        } else {
            throw new IllegalArgumentException("Supplier not found.");
        }
    }
}
