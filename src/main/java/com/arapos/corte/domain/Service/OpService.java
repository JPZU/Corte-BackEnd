package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.domain.repository.OpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OpService {

    @Autowired
    private OpRepository opRepository;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<OpResponseDTO> getAll(){
        return opRepository.getAll();
    }

    public Optional<OpResponseDTO> getById(int opId){
        return opRepository.getById(opId);
    }

    public OpResponseDTO save(CreateOpDTO createOpDTO){
        return opRepository.save(createOpDTO);
    }

    public OpResponseDTO update(CreateOpDTO createOpDTO){
        return opRepository.update(createOpDTO);
    }

    public boolean delete(int opId){
        if (opRepository.getById(opId).isPresent()){
            opRepository.delete(opId);
            return true;
        }else{
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public List<OpResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return opRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public Optional<OpResponseDTO> getByConsecutiveNumber(int number) {
        return opRepository.getByConsecutiveNumber(number);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    public List<OpResponseDTO> getByUserId(int userId){
        return opRepository.getByUserId(userId);
    }
}
