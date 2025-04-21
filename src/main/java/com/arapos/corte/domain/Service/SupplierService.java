package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.Supplier.CreateSupplierDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.domain.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<SupplierResponseDTO> getAll() {
        return supplierRepository.getAll();
    }

    public Optional<SupplierResponseDTO> getById(String supplierId) {
        return supplierRepository.getById(supplierId);
    }

    public SupplierResponseDTO save(CreateSupplierDTO createSupplierDTO) {
        return supplierRepository.save(createSupplierDTO);
    }

    public SupplierResponseDTO update(CreateSupplierDTO createSupplierDTO) {
        return supplierRepository.update(createSupplierDTO);
    }

    public Boolean delete(String supplierId) {
        if (supplierRepository.getById(supplierId).isPresent()) {
            supplierRepository.delete(supplierId);
            return true;
        } else {
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public Optional<SupplierResponseDTO> getByName(String name) {
        return supplierRepository.getByName(name);
    }

    public List<SupplierResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return supplierRepository.findByCreatedAtBetween(startDate, endDate);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
}
