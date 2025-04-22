package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Op;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OpCrudRepository extends CrudRepository<Op, Integer> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<Op> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT COALESCE(MAX(o.consecutiveNumber), 0) FROM Op o")
    int findMaxConsecutiveNumber();
    Optional<Op> findByConsecutiveNumber(int consecutiveNumber);

    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<Op> findByUser_UserId(int userId);
}
