package com.arapos.corte.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arapos.corte.domain.dto.ClothEntryItem.ClothEntryItemResponseDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;
import com.arapos.corte.persistence.crud.ClothEntryItemCrudRepository;
import com.arapos.corte.domain.repository.ClothEntryItemRepository;
import com.arapos.corte.persistence.entity.ClothEntryItem;
import com.arapos.corte.persistence.mapper.ClothEntryItemMapper;

@Repository
public class ClothEntryItemRepositoryImp implements ClothEntryItemRepository{

    @Autowired
    private ClothEntryItemCrudRepository clothEntryItemCrudRepository;

    @Autowired
    private ClothEntryItemMapper clothEntryItemMapper;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */

    @Override
    public List<ClothEntryItemResponseDTO> getAll(){
        Iterable<ClothEntryItem> clothEntryItems = clothEntryItemCrudRepository.findAll();
        return StreamSupport.stream(clothEntryItems.spliterator(), false)
                .map(clothEntryItemMapper::toClothEntryItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClothEntryItemResponseDTO> getById(int clothEntryItemId){
        return clothEntryItemCrudRepository.findById(clothEntryItemId)
                .map(clothEntryItemMapper::toClothEntryItemResponseDTO);
    }

    @Override
    public ClothEntryItemResponseDTO save(CreateClothEntryItemDTO createClothEntryItem){
        if (createClothEntryItem.getClothEntryItemId() != 0) {
            throw new IllegalArgumentException("Id cannot be present for create a new cloth entry item");
        }

        ClothEntryItem clothEntryItemEntity = clothEntryItemMapper.toClothEntryItem(createClothEntryItem);
        ClothEntryItem savedClothEntryItem = clothEntryItemCrudRepository.save(clothEntryItemEntity);
        return clothEntryItemMapper.toClothEntryItemResponseDTO(savedClothEntryItem);
    }

    @Override
    public ClothEntryItemResponseDTO update(CreateClothEntryItemDTO createClothEntryItem){
        Optional<ClothEntryItem> existingClothEntryItemOpt = clothEntryItemCrudRepository.findById(createClothEntryItem.getClothEntryItemId());

        if (existingClothEntryItemOpt.isPresent()) {
            ClothEntryItem clothEntryItemToUpdate = existingClothEntryItemOpt.get();

            // Actualizar valores con los nuevos datos
            clothEntryItemToUpdate.setColor(createClothEntryItem.getColor());
            clothEntryItemToUpdate.setPrice(createClothEntryItem.getPrice());
            clothEntryItemToUpdate.setMetersAdded(createClothEntryItem.getMetersAdded());

            // Mapear entidades usando el Mapper
            clothEntryItemToUpdate.setClothEntry(clothEntryItemMapper.mapClothEntry(createClothEntryItem.getClothEntryId()));
            clothEntryItemToUpdate.setCloth(clothEntryItemMapper.mapCloth(createClothEntryItem.getClothId()));

            ClothEntryItem updatedClothEntryItem = clothEntryItemCrudRepository.save(clothEntryItemToUpdate);

            return clothEntryItemMapper.toClothEntryItemResponseDTO(updatedClothEntryItem);
        } else {
            throw new IllegalArgumentException("Cloth entry item not found with ID: " + createClothEntryItem.getClothEntryItemId());
        }
    }

    @Override
    public void delete(int clothEntryItemId){
        if (clothEntryItemCrudRepository.findById(clothEntryItemId).isPresent()) {
            clothEntryItemCrudRepository.deleteById(clothEntryItemId);
        }else{
            throw new IllegalArgumentException("Cloth entry item not found");
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @Override
    public List<ClothEntryItemResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<ClothEntryItem> clothEntryItems = clothEntryItemCrudRepository.findByCreatedAtBetween(startDate,endDate);
        return StreamSupport.stream(clothEntryItems.spliterator(), false)
                .map(clothEntryItemMapper::toClothEntryItemResponseDTO)
                .collect(Collectors.toList());
    }
    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @Override
    public List<ClothEntryItemResponseDTO> findByClothEntryId(int clothEntryId){
        Iterable<ClothEntryItem> clothEntryItems = clothEntryItemCrudRepository.findByClothEntry_ClothEntryId(clothEntryId);
        return StreamSupport.stream(clothEntryItems.spliterator(), false)
                .map(clothEntryItemMapper::toClothEntryItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothEntryItemResponseDTO> findByClothId(int clothId){
        Iterable<ClothEntryItem> clothEntryItems = clothEntryItemCrudRepository.findByCloth_ClothId(clothId);
        return StreamSupport.stream(clothEntryItems.spliterator(), false)
                .map(clothEntryItemMapper::toClothEntryItemResponseDTO)
                .collect(Collectors.toList());
    }

}
