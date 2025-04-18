package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<SupplierResponseDTO> getAll();
    Optional<SupplierResponseDTO> getById(String supplierId);
    Optional<SupplierResponseDTO> getByName(String name);
    List<SupplierResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    SupplierResponseDTO save(CreateSupplierDTO supplier);
    SupplierResponseDTO update(CreateSupplierDTO supplier);
    void delete(String supplierId);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
}
