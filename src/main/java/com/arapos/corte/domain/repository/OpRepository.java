package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.OpDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OpRepository {
    List<OpDTO> getAll();
    Optional<OpDTO> getById(int opId);
    List<OpDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OpDTO> getByUserId(int userId);
    List<OpDTO> getByUserName(String UserName);
    OpDTO save(OpDTO opDTO);
    void delete(OpDTO opDTO);
}
