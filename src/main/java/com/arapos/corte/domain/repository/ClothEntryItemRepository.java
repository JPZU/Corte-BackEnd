package com.arapos.corte.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.arapos.corte.domain.dto.ClothEntryItem.*;

public interface ClothEntryItemRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ClothEntryItemResponseDTO> getAll();
    Optional<ClothEntryItemResponseDTO> getById(int clothEntryItemId);
    List<ClothEntryItemResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ClothEntryItemResponseDTO save(CreateClothEntryItemDTO clothEntryItem);
    ClothEntryItemResponseDTO update(CreateClothEntryItemDTO clothEntryItem);
    void delete(int clothEntryItemId);

    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ClothEntryItemResponseDTO> findByClothEntryId(int clothEntryId);
    List<ClothEntryItemResponseDTO> findByClothId(int clothId);

}
