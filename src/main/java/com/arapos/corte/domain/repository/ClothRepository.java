package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.persistence.entity.Cloth;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClothRepository {
    List<ClothResponseDTO> getAll();
    Optional<ClothResponseDTO> getById(int clothId);
    Optional<ClothResponseDTO> getByName(String name);
    List<ClothResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<ClothResponseDTO> findByMetersEqualsZero();
    List<ClothResponseDTO> findBySupplierName(String name);
    List<ClothResponseDTO> findByCategoryName(String name);
    List<ClothResponseDTO> findByUserName(String name);
    ClothResponseDTO save(Cloth cloth);
    void delete(Cloth cloth);
}
