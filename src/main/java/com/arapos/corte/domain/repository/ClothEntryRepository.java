package com.arapos.corte.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;

public interface ClothEntryRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ClothEntryResponseDTO> getAll();
    Optional<ClothEntryResponseDTO> getById(int clothEntryId);
    List<ClothEntryResponseDTO> findBySupplierInvoice(String supplierInvoice);
    List<ClothEntryResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    Page<ClothEntryResponseDTO> getAllPagedClothEntry(int page, int size);
    ClothEntryResponseDTO save(CreateClothEntryDTO clothEntry);
    ClothEntryResponseDTO update(CreateClothEntryDTO clothEntry);
    void delete(int clothEntryId);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ClothEntryResponseDTO> findBySupplierId(String supplierId);
    List<ClothEntryResponseDTO> findByUserId(int userId);
}
