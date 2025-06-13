package com.arapos.corte.domain.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;
import com.arapos.corte.domain.repository.ClothEntryRepository;

@Service
public class ClothEntryService {

    @Autowired
    private ClothEntryRepository clothEntryRepository;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ClothEntryResponseDTO> getAll(){
        return clothEntryRepository.getAll();
    }

    public Optional<ClothEntryResponseDTO> getById(int clothEntryId){
        return clothEntryRepository.getById(clothEntryId);
    }

    public ClothEntryResponseDTO save(CreateClothEntryDTO createClothEntry){
        return clothEntryRepository.save(createClothEntry);
    }

    public ClothEntryResponseDTO update(CreateClothEntryDTO createClothEntry){
        return clothEntryRepository.update(createClothEntry);
    }

    public boolean delete(int clothEntryId){
        if (clothEntryRepository.getById(clothEntryId).isPresent()) {
            clothEntryRepository.delete(clothEntryId);
            return true;
        } else{
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */

    public List<ClothEntryResponseDTO> findBySupplierInvoice(String supplierInvoice){
        return clothEntryRepository.findBySupplierInvoice(supplierInvoice);
    }

    public List<ClothEntryResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return clothEntryRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public Page<ClothEntryResponseDTO> getAllPagedClothEntry(int page, int size){
        return clothEntryRepository.getAllPagedClothEntry(page, size);
    }
    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */

    public List<ClothEntryResponseDTO> findBySupplierId(String supplierId){
        return clothEntryRepository.findBySupplierId(supplierId);
    }

    public List<ClothEntryResponseDTO> findByUserId(int userId){
        return clothEntryRepository.findByUserId(userId);
    }
}
