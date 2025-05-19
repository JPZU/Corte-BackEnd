package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.repository.ClothRepository;
import com.arapos.corte.persistence.crud.ClothCrudRepository;
import com.arapos.corte.persistence.entity.Cloth;
import com.arapos.corte.persistence.mapper.ClothMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
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

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
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
    public ClothResponseDTO save(CreateClothDTO createClothDTO){
        if(createClothDTO.getClothId() != 0){
            throw new IllegalArgumentException("Id cannot be present for create a new cloth");
        }
        Cloth clothEntity = clothMapper.toCloth(createClothDTO);
        clothEntity.setIsActive(createClothDTO.getIsActive()); // 💡 Agrega esta línea
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
            clothToUpdate.setIsActive(createClothDTO.getIsActive());// 💡 Agrega esta línea también
            clothToUpdate.setNotes(createClothDTO.getNotes());
            clothToUpdate.setPrice(createClothDTO.getPrice());
            clothToUpdate.setSupplierInvoice(createClothDTO.getSupplierInvoice());

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
    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
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
    public List<ClothResponseDTO> findByIsActiveTrue() {
        Iterable<Cloth> cloths = clothCrudRepository.findByIsActiveTrue();
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothResponseDTO> findByIsActiveFalse() {
        Iterable<Cloth> cloths = clothCrudRepository.findByIsActiveFalse();
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public  List<ClothResponseDTO> findBySupplierInvoice(String supplierInvoice) {
        Iterable<Cloth> cloths = clothCrudRepository.findBySupplierInvoice(supplierInvoice);
        return StreamSupport.stream(cloths.spliterator(), false)
                .map(clothMapper::toClothResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClothResponseDTO> getAllPagedCloths(int page, int size) {
        // Primero isActive (true primero), luego createdAt descendente
        Sort sort = Sort.by(Sort.Order.desc("isActive"), Sort.Order.desc("createdAt"));
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Cloth> clothPage = clothCrudRepository.findAll(pageable);
        return clothPage.map(clothMapper::toClothResponseDTO);
    }

    @Override
    public Page<ClothResponseDTO> filterCloths(String name, Boolean isActive, Integer categoryId, String supplierId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Specification<Cloth> spec = Specification.where(null);

        if (name != null && !name.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")
            );
        }

        if (isActive != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("isActive"), isActive)
            );
        }

        if (categoryId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category").get("categoryId"), categoryId)
            );
        }

        if (supplierId != null && !supplierId.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("supplier").get("supplierId"), supplierId)
            );
        }

        Page<Cloth> clothPage = clothCrudRepository.findAll(spec, pageable);
        return clothPage.map(clothMapper::toClothResponseDTO);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
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
}
