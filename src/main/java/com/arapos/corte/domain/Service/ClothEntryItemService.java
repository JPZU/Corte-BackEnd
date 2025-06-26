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
    public void reverse(ClothEntryItemResponseDTO item) {
    // Obtener la tela relacionada
    ClothResponseDTO cloth = clothRepository.getById(item.getCloth().getClothId())
            .orElseThrow(() -> new RuntimeException("Tela no encontrada"));

    // Restar los metros
    BigDecimal updatedMeters = cloth.getMeters().subtract(item.getMetersAdded());

    if (updatedMeters.compareTo(BigDecimal.ZERO) < 0) {
        throw new RuntimeException("No se pueden dejar metros negativos al revertir");
    }

    CreateClothDTO updateDTO = new CreateClothDTO();
    updateDTO.setClothId(cloth.getClothId());
    updateDTO.setName(cloth.getName());
    updateDTO.setMeters(updatedMeters);
    updateDTO.setIsActive(updatedMeters.compareTo(new BigDecimal("1")) > 0);
    updateDTO.setCategoryId(cloth.getCategory().getCategoryId());

    clothRepository.update(updateDTO);

    // (Opcional) Eliminar o desactivar el item si decides mantener historial
    // clothEntryItemRepository.delete(item.getClothEntryItemId());
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
