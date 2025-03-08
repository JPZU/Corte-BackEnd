package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.persistence.entity.Reference;
import com.arapos.corte.persistence.entity.Supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    List<SupplierResponseDTO> getAll();
    Optional<SupplierResponseDTO> getById(String supplierId);
    Optional<SupplierResponseDTO> getByName(String name);
    List<SupplierResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    SupplierResponseDTO save(Supplier supplier);
    void delete(Supplier supplier);
}
