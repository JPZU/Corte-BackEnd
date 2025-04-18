package com.arapos.corte.domain.repository;


import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReferenceRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ReferenceResponseDTO> getAll();
    Optional<ReferenceResponseDTO> getById(String referenceId);
    List<ReferenceResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ReferenceResponseDTO save(CreateReferenceDTO reference);
    ReferenceResponseDTO update(CreateReferenceDTO reference);
    void delete(String referenceId);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
}
