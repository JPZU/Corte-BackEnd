package com.arapos.corte.domain.repository;


import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.persistence.entity.Reference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReferenceRepository {
    List<ReferenceResponseDTO> getAll();
    Optional<ReferenceResponseDTO> getById(String referenceId);
    List<ReferenceResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ReferenceResponseDTO save(Reference reference);
    void delete(Reference reference);
}
