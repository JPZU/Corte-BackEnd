package com.arapos.corte.persistence.crud;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.arapos.corte.persistence.entity.ClothEntryItem;

public interface ClothEntryItemCrudRepository extends JpaRepository<ClothEntryItem, Integer>, JpaSpecificationExecutor<ClothEntryItem>{
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ClothEntryItem> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ClothEntryItem> findByCategory_CategoryId(int categoryId);
    List<ClothEntryItem> findByClothEntry_ClothEntryId(int clothEntryId);
    List<ClothEntryItem> findByCloth_ClothId(int clothId);

}
