package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.SupplierDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    List<SupplierDTO> getAll();
    Optional<SupplierDTO> getById(String id);
    Optional<SupplierDTO> getByName(String name);
    List<SupplierDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    SupplierDTO save(SupplierDTO supplierDTO);
    void delete(SupplierDTO supplierDTO);
}
