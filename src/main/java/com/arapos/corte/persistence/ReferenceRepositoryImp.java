package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.Reference.CreateReferenceDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;
import com.arapos.corte.domain.repository.ReferenceRepository;
import com.arapos.corte.persistence.crud.ReferenceCrudRepository;
import com.arapos.corte.persistence.entity.Reference;
import com.arapos.corte.persistence.mapper.ReferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ReferenceRepositoryImp implements ReferenceRepository {

    @Autowired
    private ReferenceCrudRepository referenceCrudRepository;

    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public List<ReferenceResponseDTO> getAll(){
        Iterable<Reference> references = referenceCrudRepository.findAll();
        return StreamSupport.stream(references.spliterator(), false)
                .map(referenceMapper::toReferenceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReferenceResponseDTO> getById(String referenceId){
        return referenceCrudRepository.findById(referenceId)
                .map(referenceMapper::toReferenceResponseDTO);

    }

    @Override
    public List<ReferenceResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<Reference> references = referenceCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(references.spliterator(), false)
                .map(referenceMapper::toReferenceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReferenceResponseDTO save(CreateReferenceDTO createReferenceDTO){
        if(createReferenceDTO.getReferenceId() == null ||
            createReferenceDTO.getReferenceId().isEmpty()) {
            throw new RuntimeException("Reference ID is required for creating a new reference");
        }

        if(referenceCrudRepository.existsById(createReferenceDTO.getReferenceId())) {
            throw new RuntimeException("Reference already exists");
        }

        Reference referenceEntity = referenceMapper.toReference(createReferenceDTO);
        Reference savedReference = referenceCrudRepository.save(referenceEntity);
        return referenceMapper.toReferenceResponseDTO(savedReference);
    }

    @Override
    public ReferenceResponseDTO update(CreateReferenceDTO createReferenceDTO){
        if(createReferenceDTO.getReferenceId() == null ||
            createReferenceDTO.getReferenceId().isEmpty()) {
            throw new RuntimeException("Reference ID is required for updating a new reference");
        }
        Optional<Reference> existingReference = referenceCrudRepository
                .findById(createReferenceDTO.getReferenceId());

        if(existingReference.isPresent()) {
            Reference referenceToUpdate = existingReference.get();
            referenceToUpdate.setDescription(createReferenceDTO.getDescription());
            Reference updatedReference = referenceCrudRepository.save(referenceToUpdate);
            return referenceMapper.toReferenceResponseDTO(updatedReference);
        } else{
            throw new RuntimeException("Reference not found");
        }
    }

    @Override
    public void delete(String referenceId){
        if (referenceCrudRepository.existsById(referenceId)) {
            referenceCrudRepository.deleteById(referenceId);
        } else {
            throw new RuntimeException("Reference not found");
        }
    }
}
