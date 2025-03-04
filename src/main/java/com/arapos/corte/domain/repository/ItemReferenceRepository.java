package com.arapos.corte.domain.repository;


import com.arapos.corte.domain.dto.ItemReferenceDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemReferenceRepository {
    List<ItemReferenceDTO> getAll();
    Optional<ItemReferenceDTO> getById(int itemReferenceId);
    List<ItemReferenceDTO> findByReferenceId(String referenceId);
    List<ItemReferenceDTO> findByOpId(int opId);
    List<ItemReferenceDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ItemReferenceDTO save(ItemReferenceDTO itemReferenceDTO);
    void delete(ItemReferenceDTO itemReferenceDTO);
}
