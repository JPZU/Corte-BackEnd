package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CategoryCrudRepository extends CrudRepository<Category,Integer> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    Optional<Category> findByName(String name);
    List<Category> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
}
