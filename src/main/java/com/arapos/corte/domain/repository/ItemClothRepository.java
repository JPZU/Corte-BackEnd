package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.ItemClothDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemClothRepository{
    List<ItemClothDTO> getAll();
    Optional<ItemClothDTO> getById(int itemClothId);
    List<ItemClothDTO> findByClothId(int clothId);  // Buscar por tela
    List<ItemClothDTO> findByOpId(int opId);        // Buscar por orden de producción (Op)
    List<ItemClothDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ItemClothDTO save(ItemClothDTO itemClothDTO);
    void delete(ItemClothDTO itemClothDTO);
}

