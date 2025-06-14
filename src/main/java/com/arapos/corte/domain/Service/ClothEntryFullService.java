package com.arapos.corte.domain.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;

@Service
public class ClothEntryFullService {

    @Autowired
    private ClothEntryService clothEntryService;

    @Autowired
    private ClothEntryItemService clothEntryItemService;

    @Transactional
    public void createCompleteEntry(CreateClothEntryDTO entryDTO, List<CreateClothEntryItemDTO> itemsDTO) {
        // 1. Crear la entrada
        ClothEntryResponseDTO savedEntry = clothEntryService.save(entryDTO);

        // 2. Crear cada ítem asociado
        for (CreateClothEntryItemDTO itemDTO : itemsDTO) {
            itemDTO.setClothEntryId(savedEntry.getClothEntryId());
            clothEntryItemService.save(itemDTO); // Ya maneja lógica interna de creación o suma de telas
        }
    }
}
