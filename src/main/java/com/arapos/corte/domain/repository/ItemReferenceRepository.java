package com.arapos.corte.domain.repository;


import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import com.arapos.corte.persistence.entity.ItemReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemReferenceRepository {
    List<ItemReferenceResponseDTO> getAll();
    Optional<ItemReferenceResponseDTO> getById(int itemReferenceId);
    List<ItemReferenceResponseDTO> findByReferenceId(String referenceId);
    List<ItemReferenceResponseDTO> findByOpId(int opId);
    List<ItemReferenceResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ItemReferenceResponseDTO save(ItemReference itemReference);
    void delete(ItemReference itemReference);
}
