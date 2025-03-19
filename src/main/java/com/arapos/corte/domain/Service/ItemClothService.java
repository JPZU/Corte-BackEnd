package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.domain.repository.ItemClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemClothService {

    @Autowired
    private ItemClothRepository itemClothRepository;

    public List<ItemClothResponseDTO> getAll(){
        return itemClothRepository.getAll();
    }

    public Optional<ItemClothResponseDTO> getById(int ClothId) {
        return itemClothRepository.getById(ClothId);
    }

    public List<ItemClothResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return itemClothRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<ItemClothResponseDTO> getByClothId(int clothId){
        return itemClothRepository.findByClothId(clothId);
    }

    public List<ItemClothResponseDTO> getByOpId(int opId){
        return itemClothRepository.findByOpId(opId);
    }

    public ItemClothResponseDTO save(CreateItemClothDTO createItemClothDTO){
        return itemClothRepository.save(createItemClothDTO);
    }

    public ItemClothResponseDTO update(CreateItemClothDTO createItemClothDTO){
        return itemClothRepository.update(createItemClothDTO);
    }

    public boolean delete(int itemClothId){
        if(itemClothRepository.getById(itemClothId).isPresent()){
            itemClothRepository.delete(itemClothId);
            return true;
        }else{
            return false;
        }
    }
}
