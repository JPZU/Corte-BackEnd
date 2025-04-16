package com.arapos.corte.persistence.crud;
import com.arapos.corte.persistence.entity.ItemReference;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemReferenceCrudRepository extends CrudRepository<ItemReference, Integer> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ItemReference> findByReference_ReferenceId(String referenceId);
    List<ItemReference> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ItemReference> findByOp_OpId(int opId);
}
