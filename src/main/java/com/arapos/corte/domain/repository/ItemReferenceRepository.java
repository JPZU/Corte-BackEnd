package com.arapos.corte.domain.repository;
import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ItemReferenceRepository {
    /* --------------------------------------------------------
                            METHOD CLASS
    --------------------------------------------------------- */
    List<ItemReferenceResponseDTO> getAll();
    Optional<ItemReferenceResponseDTO> getById(int itemReferenceId);
    List<ItemReferenceResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    ItemReferenceResponseDTO save(CreateItemReferenceDTO createItemReferenceDTO);
    ItemReferenceResponseDTO update(CreateItemReferenceDTO createItemReferenceDTO);
    void delete(int itemReference);
    /* --------------------------------------------------------
                        METHOD REAlATIONSHIP
    --------------------------------------------------------- */
    List<ItemReferenceResponseDTO> findByOpId(int opId);
    List<ItemReferenceResponseDTO> findByReferenceId(String referenceId);
}
