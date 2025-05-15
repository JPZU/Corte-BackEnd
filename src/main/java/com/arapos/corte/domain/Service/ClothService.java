package com.arapos.corte.domain.Service;


import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return clothRepository.save(createClothDTO);
    }

    public ClothResponseDTO update(CreateClothDTO createClothDTO){
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

    public List<ClothResponseDTO> getBySupplierInvoice(String supplierInvoice){
        return clothRepository.findBySupplierInvoice(supplierInvoice);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    public List<ClothResponseDTO> getBySupplierId(String supplierId){
        return clothRepository.findBySupplierId(supplierId);
    }

    public List<ClothResponseDTO> getByCategoryId(int categoryId){
        return clothRepository.findByCategoryId(categoryId);
    }

    public List<ClothResponseDTO> findByUserId(int userId){
        return clothRepository.findByUserId(userId);
    }
}
