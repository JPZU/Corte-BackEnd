package com.arapos.corte.persistence;


import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.domain.repository.OpRepository;
import com.arapos.corte.persistence.crud.OpCrudRepository;
import com.arapos.corte.persistence.entity.Op;
import com.arapos.corte.persistence.mapper.OpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class OpRepositoryImp implements OpRepository {

    @Autowired
    private OpCrudRepository opCrudRepository;

    @Autowired
    private OpMapper opMapper;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    @Override
    public List<OpResponseDTO> getAll(){
        Iterable<Op> ops = opCrudRepository.findAll();
        return StreamSupport.stream(ops.spliterator(), false)
                .map(opMapper::toOpResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OpResponseDTO> getById(int opId){
        return opCrudRepository.findById(opId)
                .map(opMapper::toOpResponseDTO);
    }

    @Override
    public OpResponseDTO save(CreateOpDTO createOpDTO){
        if(createOpDTO.getOpId() != 0){
            throw new IllegalArgumentException("Id cannot be present for create a new Op");
        }

        Op opEntity = opMapper.toOp(createOpDTO);

        // Generar el número consecutivo
        int lastConsecutive = opCrudRepository.findMaxConsecutiveNumber();
        opEntity.setConsecutiveNumber(lastConsecutive + 1);

        Op savedOp = opCrudRepository.save(opEntity);
        return opMapper.toOpResponseDTO(savedOp);
    }


    @Override
    public OpResponseDTO update(CreateOpDTO createOpDTO){
        Optional<Op> existingOp = opCrudRepository.findById(createOpDTO.getOpId());
        if(existingOp.isPresent()){
            Op opToUpdate = existingOp.get();

            opToUpdate.setQuantityCloths(createOpDTO.getQuantityCloths());
            opToUpdate.setTotalMeters(createOpDTO.getTotalMeters());
            opToUpdate.setSchemaLength(createOpDTO.getSchemaLength());
            opToUpdate.setDescriptions(createOpDTO.getDescriptions());

            // Mapear entidades usando el Mapper
            opToUpdate.setUser(opMapper.mapUser(createOpDTO.getUserId()));

            Op updatedOp = opCrudRepository.save(opToUpdate);
            return opMapper.toOpResponseDTO(updatedOp);
        }else{
            throw new IllegalArgumentException("Op not found with id " + createOpDTO.getOpId());
        }
    }

    @Override
    public void delete(int opId){
        if(opCrudRepository.findById(opId).isPresent()){
            opCrudRepository.deleteById(opId);
        }else {
            throw new IllegalArgumentException("Op not found");
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    @Override
    public List<OpResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<Op> ops = opCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(ops.spliterator(), false)
                .map(opMapper::toOpResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OpResponseDTO> getByConsecutiveNumber(int consecutiveNumber) {
        return opCrudRepository.findByConsecutiveNumber(consecutiveNumber)
                .map(opMapper::toOpResponseDTO);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    @Override
    public List<OpResponseDTO> getByUserId(int userId){
        Iterable<Op> ops = opCrudRepository.findByUser_UserId(userId);
        return StreamSupport.stream(ops.spliterator(), false)
                .map(opMapper::toOpResponseDTO)
                .collect(Collectors.toList());
    }
}
