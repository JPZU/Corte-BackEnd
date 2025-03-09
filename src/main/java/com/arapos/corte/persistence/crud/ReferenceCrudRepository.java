package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Reference;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReferenceCrudRepository extends CrudRepository<Reference, String> {
    List<Reference> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
