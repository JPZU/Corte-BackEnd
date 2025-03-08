package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.persistence.entity.ItemCloth;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemClothRepository{
    List<ItemClothResponseDTO> getAll();
    Optional<ItemClothResponseDTO> getById(int itemClothId);
    List<ItemClothResponseDTO> findByClothId(int clothId);  // Buscar por tela
    List<ItemClothResponseDTO> findByOpId(int opId);        // Buscar por orden de producción (Op)
    List<ItemClothResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ItemClothResponseDTO save(ItemCloth itemCloth);
    void delete(ItemCloth itemCloth);
}

