package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemClothRepository{
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ItemClothResponseDTO> getAll();
    Optional<ItemClothResponseDTO> getById(int itemClothId);
    List<ItemClothResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ItemClothResponseDTO save(CreateItemClothDTO createItemClothDTO);
    ItemClothResponseDTO update(CreateItemClothDTO createItemClothDTO);
    void delete(int itemClothId);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ItemClothResponseDTO> findByOpId(int opId);        // Buscar por orden de producción (Op)
    List<ItemClothResponseDTO> findByClothId(int clothId);  // Buscar por tela
}

