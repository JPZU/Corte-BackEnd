package com.arapos.corte.domain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arapos.corte.domain.dto.ClothEntryItem.ClothEntryItemResponseDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;
import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.repository.ClothEntryItemRepository;
import com.arapos.corte.domain.repository.ClothRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
public class ClothEntryItemService {

    @Autowired
    private ClothRepository clothRepository;

    @Autowired
    private ClothEntryItemRepository clothEntryItemRepository;

/* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ClothEntryItemResponseDTO> getAll(){
        return clothEntryItemRepository.getAll();
    }

    public Optional<ClothEntryItemResponseDTO> getById(int clothEntryItemId){
        return clothEntryItemRepository.getById(clothEntryItemId);
    }

    @Transactional
    public ClothEntryItemResponseDTO save(CreateClothEntryItemDTO dto) {
        // 1. Validar que la tela exista
        ClothResponseDTO cloth = clothRepository.getById(dto.getClothId())
            .orElseThrow(() -> new RuntimeException("La tela con ID " + dto.getClothId() + " no existe"));

        // 2. Actualizar los metros disponibles sumando los nuevos metros
        BigDecimal updatedMeters = cloth.getMeters().add(dto.getMetersAdded());

        CreateClothDTO updateDTO = new CreateClothDTO();
        updateDTO.setClothId(cloth.getClothId());
        updateDTO.setName(cloth.getName());
        updateDTO.setMeters(updatedMeters);
        updateDTO.setIsActive(true);
        updateDTO.setCategoryId(cloth.getCategory().getCategoryId());

        clothRepository.update(updateDTO);

        // 3. Guardar el nuevo ítem de entrada
        return clothEntryItemRepository.save(dto);
    }


    @Transactional
    public ClothEntryItemResponseDTO update(CreateClothEntryItemDTO dto) {
        // 1. Obtener el ítem actual
        ClothEntryItemResponseDTO existing = clothEntryItemRepository.getById(dto.getClothEntryItemId())
                .orElseThrow(() -> new RuntimeException("ClothEntryItem no encontrado"));

        int oldClothId = existing.getCloth().getClothId();
        int newClothId = dto.getClothId();
        boolean sameCloth = oldClothId == newClothId;

        // 2. Si no cambió la tela, solo ajustamos los metros
        if (sameCloth) {
            BigDecimal delta = dto.getMetersAdded().subtract(existing.getMetersAdded());

            ClothResponseDTO cloth = clothRepository.getById(oldClothId)
                    .orElseThrow(() -> new RuntimeException("Tela no encontrada"));

            BigDecimal updatedMeters = cloth.getMeters().add(delta);
            if (updatedMeters.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("No se pueden dejar metros negativos");
            }

            CreateClothDTO updateDTO = new CreateClothDTO();
            updateDTO.setClothId(cloth.getClothId());
            updateDTO.setName(cloth.getName());
            updateDTO.setMeters(updatedMeters);
            updateDTO.setIsActive(updatedMeters.compareTo(new BigDecimal("1")) > 0);
            updateDTO.setCategoryId(cloth.getCategory().getCategoryId());

            clothRepository.update(updateDTO);

            return clothEntryItemRepository.update(dto);
        }

        // 3. Cambió la tela → mover metros entre telas

        // 3.1 Restar metros a la tela anterior
        ClothResponseDTO oldCloth = clothRepository.getById(oldClothId)
                .orElseThrow(() -> new RuntimeException("Tela anterior no encontrada"));

        BigDecimal oldUpdatedMeters = oldCloth.getMeters().subtract(existing.getMetersAdded());
        if (oldUpdatedMeters.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Metros negativos en tela anterior");
        }

        CreateClothDTO oldUpdate = new CreateClothDTO();
        oldUpdate.setClothId(oldCloth.getClothId());
        oldUpdate.setName(oldCloth.getName());
        oldUpdate.setMeters(oldUpdatedMeters);
        oldUpdate.setIsActive(oldUpdatedMeters.compareTo(new BigDecimal("1")) > 0);
        oldUpdate.setCategoryId(oldCloth.getCategory().getCategoryId());

        clothRepository.update(oldUpdate);

        // 3.2 Sumar metros a la nueva tela
        ClothResponseDTO newCloth = clothRepository.getById(newClothId)
                .orElseThrow(() -> new RuntimeException("Tela nueva no encontrada"));

        BigDecimal newUpdatedMeters = newCloth.getMeters().add(dto.getMetersAdded());

        CreateClothDTO newUpdate = new CreateClothDTO();
        newUpdate.setClothId(newCloth.getClothId());
        newUpdate.setName(newCloth.getName());
        newUpdate.setMeters(newUpdatedMeters);
        newUpdate.setIsActive(true);
        newUpdate.setCategoryId(newCloth.getCategory().getCategoryId());

        clothRepository.update(newUpdate);

        return clothEntryItemRepository.update(dto);
    }


    public boolean delete(int clothEntryItemId){
        if(clothEntryItemRepository.getById(clothEntryItemId).isPresent()){
            clothEntryItemRepository.delete(clothEntryItemId);
            return true;
        }else {
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public List<ClothEntryItemResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return clothEntryItemRepository.findByCreatedAtBetween(startDate, endDate);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    public List<ClothEntryItemResponseDTO> findByClothEntryId(int clothEntryId){
        return clothEntryItemRepository.findByClothEntryId(clothEntryId);
    }

    public List<ClothEntryItemResponseDTO> findByClothId(int clothId){
        return clothEntryItemRepository.findByClothId(clothId);
    }
}
