package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.persistence.entity.Op;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OpRepository {
    List<OpResponseDTO> getAll();
    Optional<OpResponseDTO> getById(int opId);
    List<OpResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OpResponseDTO> getByUserId(int userId);
    List<OpResponseDTO> getByUserName(String UserName);
    OpResponseDTO save(Op op);
    void delete(Op op);
}
