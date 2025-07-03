package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.domain.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ReferenceResponseDTO> getAll(){
        return referenceRepository.getAll();
    }

    public Optional<ReferenceResponseDTO> getById(String referenceId){
        return referenceRepository.getById(referenceId);
    }

    public ReferenceResponseDTO save(CreateReferenceDTO createReferenceDTO){
        return referenceRepository.save(createReferenceDTO);
    }
    public ReferenceResponseDTO update(CreateReferenceDTO createReferenceDTO){
        return referenceRepository.update(createReferenceDTO);
    }

    public boolean delete(String referenceId){
        if(referenceRepository.getById(referenceId).isPresent()){
            referenceRepository.delete(referenceId);
            return true;
        } else {
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public List<ReferenceResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return referenceRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public Page<ReferenceResponseDTO> getAllPagedReferences(int page, int size){
        return referenceRepository.getAllPagedReferences(page, size);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
}
