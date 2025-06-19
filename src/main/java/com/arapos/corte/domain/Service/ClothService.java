package com.arapos.corte.domain.Service;


import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClothService {
    @Autowired
    private ClothRepository clothRepository;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ClothResponseDTO> getAll(){
        return clothRepository.getAll();
    }

    public Optional<ClothResponseDTO> getById(int clothId){
        return clothRepository.getById(clothId);
    }

    public ClothResponseDTO save(CreateClothDTO createClothDTO){
        // Logic to set boolean in cloth
        boolean isActiveOrNot = createClothDTO.getMeters().compareTo(new BigDecimal("1")) > 0;
        createClothDTO.setIsActive(isActiveOrNot);
        return clothRepository.save(createClothDTO);
    }

    public ClothResponseDTO update(CreateClothDTO createClothDTO){
        // Logic to set boolean in cloth
        boolean isActiveOrNot = createClothDTO.getMeters().compareTo(new BigDecimal("1")) > 0;
        createClothDTO.setIsActive(isActiveOrNot);
        return clothRepository.update(createClothDTO);
    }

    public boolean delete(int clothId){
        if (clothRepository.getById(clothId).isPresent()) {
            clothRepository.delete(clothId);
            return true;
        } else{
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public List<ClothResponseDTO> getIsActive(){
        return clothRepository.findByIsActiveTrue();
    }

    public List<ClothResponseDTO> getIsNotActive(){
        return clothRepository.findByIsActiveFalse();
    }

    public Optional<ClothResponseDTO> getByName(String name){
        return clothRepository.getByName(name);
    }

    public List<ClothResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return clothRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public Page<ClothResponseDTO> getAllPagedCloths(int page, int size){
        return clothRepository.getAllPagedCloths(page, size);
    }

    public Page<ClothResponseDTO> filterCloths(String name, Boolean isActive, Integer categoryId, int page, int size) {
        return clothRepository.filterCloths(name, isActive, categoryId, page, size);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */

    public List<ClothResponseDTO> getByCategoryId(int categoryId){
        return clothRepository.findByCategoryId(categoryId);
    }
}
