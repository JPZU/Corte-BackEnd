package com.arapos.corte.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import com.arapos.corte.persistence.entity.ClothEntry;

import com.arapos.corte.persistence.mapper.ClothEntryMapper;
import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;
import com.arapos.corte.domain.repository.ClothEntryRepository;
import com.arapos.corte.persistence.crud.ClothEntryCrudRepository;
import java.util.stream.Collectors;

@Repository
public class ClothEntryRepositoryImp implements ClothEntryRepository{

    @Autowired
    private ClothEntryCrudRepository clothEntryCrudRepository;

    @Autowired
    private ClothEntryMapper clothEntryMapper;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */

    @Override
    public List<ClothEntryResponseDTO> getAll(){
        Iterable<ClothEntry> clothEntries = clothEntryCrudRepository.findAll();
        return StreamSupport.stream(clothEntries.spliterator(), false)
                .map(clothEntryMapper::toClothEntryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClothEntryResponseDTO> getById(int clothEntryId){
        return clothEntryCrudRepository.findById(clothEntryId)
                .map(clothEntryMapper::toClothEntryResponseDTO);
    }

    @Override
    public ClothEntryResponseDTO save(CreateClothEntryDTO createClothEntryDTO){
        if(createClothEntryDTO.getClothEntryId() != 0){
            throw new IllegalArgumentException("Id cannot be present for create a new cloth entry");
        }
        ClothEntry clothEntryEntity = clothEntryMapper.toClothEntry(createClothEntryDTO);
        clothEntryEntity.setApprove(true);
        ClothEntry savedClothEntry = clothEntryCrudRepository.save(clothEntryEntity);
        return clothEntryMapper.toClothEntryResponseDTO(savedClothEntry);
    }

    @Override
    public ClothEntryResponseDTO update(CreateClothEntryDTO createClothEntryDTO){
        Optional<ClothEntry> existingClothEntryOpt = clothEntryCrudRepository.findById(createClothEntryDTO.getClothEntryId());

        if(existingClothEntryOpt.isPresent()){
            ClothEntry clothEntryToUpdate = existingClothEntryOpt.get();

            // Actualizar valores con los nuevos datos
            clothEntryToUpdate.setSupplierInvoice(createClothEntryDTO.getSupplierInvoice());
            clothEntryToUpdate.setNotes(createClothEntryDTO.getNotes());
            clothEntryToUpdate.setApprove(createClothEntryDTO.getApprove());

            // Mapear entidades usando el Mapper
            clothEntryToUpdate.setSupplier(clothEntryMapper.mapSupplier(createClothEntryDTO.getSupplierId()));
            clothEntryToUpdate.setUser(clothEntryMapper.mapUser(createClothEntryDTO.getUserId()));

            ClothEntry updatedClothEntry = clothEntryCrudRepository.save(clothEntryToUpdate);

            return clothEntryMapper.toClothEntryResponseDTO(updatedClothEntry);
        }else{
            throw new IllegalArgumentException("Cloth Entry not found with ID: " + createClothEntryDTO.getClothEntryId());
        }
    }

    @Override
    public void delete(int clothEntryId){
        if(clothEntryCrudRepository.findById(clothEntryId).isPresent()){
            clothEntryCrudRepository.deleteById(clothEntryId);
        }else{
            throw new IllegalArgumentException("Cloth entry not found");
        }
    }

    /* --------------------------------------------------------
    PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @Override
    public List<ClothEntryResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<ClothEntry> clothEntries = clothEntryCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(clothEntries.spliterator(), false)
        .map(clothEntryMapper::toClothEntryResponseDTO)
        .collect(Collectors.toList());
    }

    @Override
    public Page<ClothEntryResponseDTO> getAllPagedClothEntry(int page, int size){
        Sort sort = Sort.by(Sort.Order.desc("createdAt"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ClothEntry> clothEntryPage = clothEntryCrudRepository.findAll(pageable);
        return clothEntryPage.map(clothEntryMapper::toClothEntryResponseDTO);
    }

    @Override
    public List<ClothEntryResponseDTO> findBySupplierInvoice(String supplierInvoice){
        Iterable<ClothEntry> clothEntries = clothEntryCrudRepository.findBySupplierInvoice(supplierInvoice);
        return StreamSupport.stream(clothEntries.spliterator(), false)
                .map(clothEntryMapper::toClothEntryResponseDTO)
                .collect(Collectors.toList());
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @Override
    public List<ClothEntryResponseDTO> findBySupplierId(String supplierId){
            Iterable<ClothEntry> clothEntries = clothEntryCrudRepository.findBySupplier_SupplierId(supplierId);
        return StreamSupport.stream(clothEntries.spliterator(), false)
                .map(clothEntryMapper::toClothEntryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothEntryResponseDTO> findByUserId(int userId){
            Iterable<ClothEntry> clothEntries = clothEntryCrudRepository.findByUser_UserId(userId);
        return StreamSupport.stream(clothEntries.spliterator(), false)
                .map(clothEntryMapper::toClothEntryResponseDTO)
                .collect(Collectors.toList());
    }
}
