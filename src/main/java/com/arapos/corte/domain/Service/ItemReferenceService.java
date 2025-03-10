package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import com.arapos.corte.domain.repository.ItemReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemReferenceService {

    @Autowired
    private ItemReferenceRepository itemReferenceRepository;

    public List<ItemReferenceResponseDTO> getAll() {
        return itemReferenceRepository.getAll();
    }

    public Optional<ItemReferenceResponseDTO> getById(int itemReferenceId) {
        return itemReferenceRepository.getById(itemReferenceId);
    }

    public List<ItemReferenceResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return itemReferenceRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<ItemReferenceResponseDTO> getByReferenceId(String referenceId){
        return itemReferenceRepository.findByReferenceId(referenceId);
    }

    public List<ItemReferenceResponseDTO> getByOpId(int opId){
        return itemReferenceRepository.findByOpId(opId);
    }

    public ItemReferenceResponseDTO save(CreateItemReferenceDTO createItemReferenceDTO) {
        return itemReferenceRepository.save(createItemReferenceDTO);
    }
    public ItemReferenceResponseDTO update(CreateItemReferenceDTO createItemReferenceDTO) {
        return itemReferenceRepository.update(createItemReferenceDTO);
    }

    public boolean delete(int itemReferenceId) {
        if (itemReferenceRepository.getById(itemReferenceId).isPresent()) {
            itemReferenceRepository.delete(itemReferenceId);
            return true;
        } else {
            return false;
        }
    }

}
