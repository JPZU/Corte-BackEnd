package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Reference;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface ReferenceCrudRepository extends JpaRepository<Reference, String>, JpaSpecificationExecutor<Reference> {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<Reference> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
}
