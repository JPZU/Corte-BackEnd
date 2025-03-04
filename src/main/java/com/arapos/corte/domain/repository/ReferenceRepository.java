package com.arapos.corte.domain.repository;


import com.arapos.corte.domain.dto.ReferenceDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReferenceRepository {
    List<ReferenceDTO> getAll();
    Optional<ReferenceDTO> getById(String id);
    List<ReferenceDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ReferenceDTO save(ReferenceDTO referenceDTO);
    void delete(ReferenceDTO referenceDTO);
}
