package com.arapos.corte.domain.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.CreateClothEntryDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.ClothEntryItemResponseDTO;
import com.arapos.corte.domain.repository.ClothEntryRepository;

import jakarta.transaction.Transactional;

@Service
public class ClothEntryService {

    @Autowired
    private ClothEntryRepository clothEntryRepository;

    @Autowired
    private ClothEntryItemService clothEntryItemService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ClothEntryResponseDTO> getAll(){
        return clothEntryRepository.getAll();
    }

    public Optional<ClothEntryResponseDTO> getById(int clothEntryId){
        return clothEntryRepository.getById(clothEntryId);
    }

    public ClothEntryResponseDTO save(CreateClothEntryDTO createClothEntry) {
        // Solo validamos si se quiere aprobar (approve = true)
        if (createClothEntry.getApprove()) {
            List<ClothEntryResponseDTO> existing = clothEntryRepository.findBySupplierInvoice(createClothEntry.getSupplierInvoice());

            boolean exists = existing.stream()
                .anyMatch(e ->
                    e.getSupplier() != null &&
                    e.getSupplier().getSupplierId().equals(createClothEntry.getSupplierId()) &&
                    e.getApprove()
                );

            if (exists) {
                throw new RuntimeException("Ya existe una entrada activa con la misma factura y proveedor.");
            }
        }

        return clothEntryRepository.save(createClothEntry);
    }



    @Transactional
    public ClothEntryResponseDTO update(CreateClothEntryDTO dto) {
        // Obtener la entrada original
        ClothEntryResponseDTO existing = clothEntryRepository.getById(dto.getClothEntryId())
                .orElseThrow(() -> new RuntimeException("Cloth Entry no encontrada"));

        // Si ya estaba inactiva, no se puede volver a activar
        if (!existing.getApprove() && dto.getApprove()) {
            throw new RuntimeException("No se puede volver a aprobar una entrada desactivada");
        }

        // Si se va a desactivar, revertir el impacto sobre las telas
        if (existing.getApprove() && !dto.getApprove()) {
            List<ClothEntryItemResponseDTO> items = clothEntryItemService.findByClothEntryId(dto.getClothEntryId());

            for (ClothEntryItemResponseDTO item : items) {
                clothEntryItemService.reverse(item);
            }
        }

        // Continuar con el update normal
        return clothEntryRepository.update(dto);
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
