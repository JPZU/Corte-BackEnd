package com.arapos.corte.persistence.crud;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.arapos.corte.persistence.entity.ClothEntry;

public interface ClothEntryCrudRepository extends JpaRepository<ClothEntry, Integer>, JpaSpecificationExecutor<ClothEntry>{
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ClothEntry> findBySupplierInvoice(String supplierInvoice);
    List<ClothEntry> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ClothEntry> findBySupplier_SupplierId(String supplierId);
    List<ClothEntry> findByUser_UserId(int userId);
}
