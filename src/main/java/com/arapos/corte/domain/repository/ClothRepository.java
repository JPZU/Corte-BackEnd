package com.arapos.corte.domain.repository;
import com.arapos.corte.domain.dto.ClothDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClothRepository {
    List<ClothDTO> getAll();
    Optional<ClothDTO> getById(int id);
    Optional<ClothDTO> getByName(String name);
    List<ClothDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<ClothDTO> findByMetersEqualsZero();
    List<ClothDTO> findBySupplierName(String name);
    List<ClothDTO> findByCategoryName(String name);
    List<ClothDTO> findByUserName(String name);
    ClothDTO save(ClothDTO clothDTO);
    void delete(ClothDTO clothDTO);
}
