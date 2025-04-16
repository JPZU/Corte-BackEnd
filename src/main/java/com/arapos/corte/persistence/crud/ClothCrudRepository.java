package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Cloth;
import org.springframework.data.repository.CrudRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClothCrudRepository extends CrudRepository<Cloth, Integer> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    Optional<Cloth> findByName(String name);
    List<Cloth> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Cloth> findByIsActiveTrue();
    List<Cloth> findByIsActiveFalse();
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<Cloth> findBySupplier_SupplierId(String supplierId);
    List<Cloth> findByCategory_CategoryId(int categoryId);
    List<Cloth> findByUser_UserId(int userId);
}
