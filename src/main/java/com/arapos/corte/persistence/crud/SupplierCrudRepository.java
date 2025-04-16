package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SupplierCrudRepository extends CrudRepository<Supplier, String> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    Optional<Supplier> findByName(String name);
    List<Supplier> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
}
