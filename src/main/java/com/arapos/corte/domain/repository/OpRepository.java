package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OpRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<OpResponseDTO> getAll();
    Optional<OpResponseDTO> getById(int opId);
    List<OpResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    Optional<OpResponseDTO> getByConsecutiveNumber(int consecutiveNumber);
    OpResponseDTO save(CreateOpDTO createOpDTO);
    OpResponseDTO update(CreateOpDTO createOpDTO);
    void delete(int opId);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<OpResponseDTO> getByUserId(int userId);
}
