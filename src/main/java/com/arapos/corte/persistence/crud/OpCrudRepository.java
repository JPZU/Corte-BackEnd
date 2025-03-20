package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Op;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OpCrudRepository extends CrudRepository<Op, Integer> {
    List<Op> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Op> findByUser_UserId(int userId);
}
