package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClothCrudRepository extends JpaRepository<Cloth, Integer>, JpaSpecificationExecutor<Cloth> {
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
    List<Cloth> findByCategory_CategoryId(int categoryId);
}
