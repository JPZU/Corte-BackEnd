package com.arapos.corte.domain.repository;

import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.persistence.entity.Rol;
import com.arapos.corte.persistence.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserResponseDTO> getAll();
    Optional<UserResponseDTO> getById(int userId);
    Optional<UserResponseDTO> getByName(String name);
    Optional<UserResponseDTO> getByEmail(String email);
    List<UserResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<UserResponseDTO> findByRole(Rol role);
    UserResponseDTO save(CreateUserDTO user);
    UserResponseDTO update(CreateUserDTO user);
    void delete(int userId);
}
