package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemReference.ItemReferenceResponseDTO;
import com.arapos.corte.domain.repository.ItemReferenceRepository;
import com.arapos.corte.persistence.crud.ItemReferenceCrudRepository;
import com.arapos.corte.persistence.entity.ItemReference;
import com.arapos.corte.persistence.mapper.ItemReferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ItemReferenceRepositoryImp implements ItemReferenceRepository {

    @Autowired
    private ItemReferenceCrudRepository itemReferenceCrudRepository;

    @Autowired
    private ItemReferenceMapper itemReferenceMapper;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @Override
    public List<ItemReferenceResponseDTO> getAll(){
        Iterable<ItemReference> itemReferences = itemReferenceCrudRepository.findAll();
        return StreamSupport.stream(itemReferences.spliterator(),false)
                .map(itemReferenceMapper::toItemReferenceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ItemReferenceResponseDTO> getById(int itemReferenceId){
        return itemReferenceCrudRepository.findById(itemReferenceId)
                .map(itemReferenceMapper::toItemReferenceResponseDTO);
    }

    @Override
    public ItemReferenceResponseDTO save(CreateItemReferenceDTO createItemReferenceDTO){
        if(createItemReferenceDTO.getItemReferenceId() != 0){
            throw new IllegalArgumentException("Id cannot be present for create a new item Reference");
        }

        ItemReference itemReferenceEntity = itemReferenceMapper.toItemReference(createItemReferenceDTO);
        ItemReference savedItemReference = itemReferenceCrudRepository.save(itemReferenceEntity);
        return itemReferenceMapper.toItemReferenceResponseDTO(savedItemReference);
    }

    @Override
    public ItemReferenceResponseDTO update(CreateItemReferenceDTO createItemReferenceDTO){
        Optional<ItemReference> existingItemReference = itemReferenceCrudRepository.findById(createItemReferenceDTO.getItemReferenceId());
        if(existingItemReference.isPresent()){
            ItemReference itemReferenceToUpdate = existingItemReference.get();

            // Mapear entidades usando el Mapper
            itemReferenceToUpdate.setReference(itemReferenceMapper.mapReference(createItemReferenceDTO.getReferenceId()));
            itemReferenceToUpdate.setOp(itemReferenceMapper.mapOp(createItemReferenceDTO.getOpId()));

            ItemReference updatedItemReference = itemReferenceCrudRepository.save(itemReferenceToUpdate);
            return itemReferenceMapper.toItemReferenceResponseDTO(updatedItemReference);

        }else{
            throw new IllegalArgumentException("item Reference not found with ID: " + createItemReferenceDTO.getItemReferenceId());
        }
    }

    @Override
    public void delete(int itemReferenceId){
        if (itemReferenceCrudRepository.findById(itemReferenceId).isPresent()){
            itemReferenceCrudRepository.deleteById(itemReferenceId);
        } else {
            throw new IllegalArgumentException("item reference not found");
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @Override
    public List<ItemReferenceResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<ItemReference> itemReferences = itemReferenceCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(itemReferences.spliterator(),false)
                .map(itemReferenceMapper::toItemReferenceResponseDTO)
                .collect(Collectors.toList());
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @Override
    public List<ItemReferenceResponseDTO> findByReferenceId(String referenceId){
        Iterable<ItemReference> itemReferences = itemReferenceCrudRepository.findByReference_ReferenceId(referenceId);
        return StreamSupport.stream(itemReferences.spliterator(),false)
                .map(itemReferenceMapper::toItemReferenceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemReferenceResponseDTO> findByOpId(int opId){
        Iterable<ItemReference> itemReferences = itemReferenceCrudRepository.findByOp_OpId(opId);
        return StreamSupport.stream(itemReferences.spliterator(),false)
                .map(itemReferenceMapper::toItemReferenceResponseDTO)
                .collect(Collectors.toList());
    }
}

