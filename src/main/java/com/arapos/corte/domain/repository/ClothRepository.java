package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClothRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ClothResponseDTO> getAll();
    Optional<ClothResponseDTO> getById(int clothId);
    Optional<ClothResponseDTO> getByName(String name);
    List<ClothResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<ClothResponseDTO> findByIsActiveTrue();
    List<ClothResponseDTO> findByIsActiveFalse();
    List<ClothResponseDTO> findBySupplierInvoice(String supplierInvoice);
    Page<ClothResponseDTO> getAllPagedCloths(int page, int size);
    Page<ClothResponseDTO> filterCloths(String name, Boolean isActive, Integer categoryId, String supplierId, int page, int size);
    ClothResponseDTO save(CreateClothDTO cloth);
    ClothResponseDTO update(CreateClothDTO cloth);
    void delete(int clothId);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ClothResponseDTO> findBySupplierId(String supplierId);
    List<ClothResponseDTO> findByCategoryId(int categoryId);
    List<ClothResponseDTO> findByUserId(int userId);
}
