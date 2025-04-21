package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.domain.repository.ItemClothRepository;
import com.arapos.corte.persistence.crud.ItemClothCrudRepository;
import com.arapos.corte.persistence.entity.ItemCloth;
import com.arapos.corte.persistence.mapper.ItemClothMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ItemClothRepositoryImp implements ItemClothRepository {

    @Autowired
    private ItemClothCrudRepository itemClothCrudRepository;

    @Autowired
    private ItemClothMapper itemClothMapper;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @Override
    public List<ItemClothResponseDTO> getAll(){
        Iterable<ItemCloth> itemCloths = itemClothCrudRepository.findAll();
        return StreamSupport.stream(itemCloths.spliterator(), false)
                .map(itemClothMapper::toItemClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ItemClothResponseDTO> getById(int itemClothId){
        return itemClothCrudRepository.findById(itemClothId)
                .map(itemClothMapper::toItemClothResponseDTO);
    }

    @Override
    public ItemClothResponseDTO save(CreateItemClothDTO createItemClothDTO){
        if(createItemClothDTO.getItemClothId() != 0){
            throw new IllegalArgumentException(("Id cannot be present for create a new item cloth"));
        }

        ItemCloth itemClothEntity = itemClothMapper.toItemCloth(createItemClothDTO);
        ItemCloth savedItemCloth = itemClothCrudRepository.save(itemClothEntity);
        return itemClothMapper.toItemClothResponseDTO(savedItemCloth);
    }

    @Override
    public ItemClothResponseDTO update(CreateItemClothDTO createItemClothDTO){
        Optional<ItemCloth> existingItemCloth = itemClothCrudRepository.findById(createItemClothDTO.getItemClothId());
        if(existingItemCloth.isPresent()){
            ItemCloth itemClothToUpdate = existingItemCloth.get();

            // Actualizar valores con los nuevos datos
            itemClothToUpdate.setMeters(createItemClothDTO.getMeters());
            // Mapear entidades usando el Mapper
            itemClothToUpdate.setCloth(itemClothMapper.mapCloth(createItemClothDTO.getClothId()));
            itemClothToUpdate.setOp(itemClothMapper.mapOp(createItemClothDTO.getOpId()));

            ItemCloth updatedItemCloth = itemClothCrudRepository.save(itemClothToUpdate);
            return itemClothMapper.toItemClothResponseDTO(updatedItemCloth);
        }else{
            throw new IllegalArgumentException("Item cloth not found with Id: " + createItemClothDTO.getItemClothId());
        }
    }

    @Override
    public void delete (int itemClothId){
        if(itemClothCrudRepository.findById(itemClothId).isPresent()){
            itemClothCrudRepository.deleteById(itemClothId);
        }else {
            throw new IllegalArgumentException("Item cloth not found with Id: " + itemClothId);
        }
    }
    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @Override
    public List<ItemClothResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<ItemCloth> itemCloths = itemClothCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(itemCloths.spliterator(), false)
                .map(itemClothMapper::toItemClothResponseDTO)
                .collect(Collectors.toList());
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @Override
    public List<ItemClothResponseDTO> findByOpId(int opId){
        Iterable<ItemCloth> itemCloths = itemClothCrudRepository.findByOp_OpId(opId);
        return StreamSupport.stream(itemCloths.spliterator(), false)
                .map(itemClothMapper::toItemClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemClothResponseDTO> findByClothId(int clothId){
        Iterable<ItemCloth> itemCloths = itemClothCrudRepository.findByCloth_ClothId(clothId);
        return StreamSupport.stream(itemCloths.spliterator(), false)
                .map(itemClothMapper::toItemClothResponseDTO)
                .collect(Collectors.toList());
    }
}
