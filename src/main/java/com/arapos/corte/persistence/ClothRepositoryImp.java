package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.repository.ClothRepository;
import com.arapos.corte.persistence.crud.ClothCrudRepository;
import com.arapos.corte.persistence.entity.Cloth;
import com.arapos.corte.persistence.mapper.ClothMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ClothRepositoryImp implements ClothRepository {

    @Autowired
    private ClothCrudRepository clothCrudRepository;

    @Autowired
    private ClothMapper clothMapper;

    @Override
    public List<ClothResponseDTO> getAll(){
        Iterable<Cloth> cloths = clothCrudRepository.findAll();
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClothResponseDTO> getById(int clothId){
        return clothCrudRepository.findById(clothId)
                .map(clothMapper::toClothResponseDTO);
    }

    @Override
    public Optional<ClothResponseDTO> getByName(String name){
        return clothCrudRepository.findByName(name)
                .map(clothMapper::toClothResponseDTO);
    }

    @Override
    public List<ClothResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<Cloth> cloths = clothCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothResponseDTO> findByMeters(BigDecimal meters){
        Iterable<Cloth> cloths = clothCrudRepository.findByMeters(meters);
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothResponseDTO> findBySupplierId(String supplierName){
        Iterable<Cloth> cloths = clothCrudRepository.findBySupplier_SupplierId(supplierName);
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothResponseDTO> findByCategoryId(int categoryId){
        Iterable<Cloth> cloths = clothCrudRepository.findByCategory_CategoryId(categoryId);
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothResponseDTO> findByUserId(int userId){
        Iterable<Cloth> cloths = clothCrudRepository.findByUser_UserId(userId);
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClothResponseDTO save(CreateClothDTO createClothDTO){
        if(createClothDTO.getClothId() != 0){
            throw new IllegalArgumentException("Id cannot be present for create a new cloth");
        }
        Cloth clothEntity = clothMapper.toCloth(createClothDTO);
        Cloth savedCloth = clothCrudRepository.save(clothEntity);
        return clothMapper.toClothResponseDTO(savedCloth);
    }

    @Override
    public ClothResponseDTO update(CreateClothDTO createClothDTO) {
        Optional<Cloth> existingClothOpt = clothCrudRepository.findById(createClothDTO.getClothId());

        if (existingClothOpt.isPresent()) {
            Cloth clothToUpdate = existingClothOpt.get();

            // Actualizar valores con los nuevos datos
            clothToUpdate.setName(createClothDTO.getName());
            clothToUpdate.setColor(createClothDTO.getColor());
            clothToUpdate.setMeters(createClothDTO.getMeters());

            // Mapear entidades usando el Mapper
            clothToUpdate.setUser(clothMapper.mapUser(createClothDTO.getUserId()));
            clothToUpdate.setCategory(clothMapper.mapCategory(createClothDTO.getCategoryId()));
            clothToUpdate.setSupplier(clothMapper.mapSupplier(createClothDTO.getSupplierId()));

            // Guardar cambios en la base de datos
            Cloth updatedCloth = clothCrudRepository.save(clothToUpdate);

            // Convertir la entidad actualizada a DTO
            return clothMapper.toClothResponseDTO(updatedCloth);
        } else {
            throw new IllegalArgumentException("Cloth not found with ID: " + createClothDTO.getClothId());
        }
    }

    @Override
    public void delete(int clothId){
        if(clothCrudRepository.findById(clothId).isPresent()){
            clothCrudRepository.deleteById(clothId);
        } else {
            throw new IllegalArgumentException("Cloth not found");
        }
    }
}
