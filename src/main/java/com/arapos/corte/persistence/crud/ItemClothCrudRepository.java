package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.ItemCloth;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemClothCrudRepository extends CrudRepository<ItemCloth, Integer> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ItemCloth> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ItemCloth> findByOp_OpId(int opId);
    List<ItemCloth> findByCloth_ClothId(int clothId);
}
