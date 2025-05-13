package com.arapos.corte.domain.service;

import com.arapos.corte.domain.Service.UserService;
import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.domain.repository.UserRepository;
import com.arapos.corte.persistence.entity.Rol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void save_ShouldReturnUserResponseDTO_WhenValidInput(){

//        Arrange
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("Juan Zuluaga");
        createUserDTO.setEmail("juanzuluaga@gmail.com");
        createUserDTO.setPassword("juan");
        createUserDTO.setRole(Rol.ADMIN);

//        Simula que encripta la contraseña:
        when(passwordEncoder.encode("juan")).thenReturn("hashed_password");

//        Simula que el repositorio guarda el usuario y devuelve una respuesta
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(1);
        userResponseDTO.setName("Juan Zuluaga");
        userResponseDTO.setEmail("juanzuluaga@gmail.com");
        userResponseDTO.setRole(Rol.ADMIN);

        when(userRepository.save(any(CreateUserDTO.class))).thenReturn(userResponseDTO);

//        Act
        UserResponseDTO result = userService.save(createUserDTO);

//        Assert
        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("Juan Zuluaga", result.getName());
        assertEquals("juanzuluaga@gmail.com", result.getEmail());
        assertEquals(Rol.ADMIN, result.getRole());
    }

    @Test
    void update_ShouldReturnUserResponseDTO_WhenValidInput() {
        // Arrange
        CreateUserDTO dto = new CreateUserDTO();
        dto.setUserId(1);
        dto.setName("Juan Updated");
        dto.setEmail("juan_updated@example.com");
        dto.setPassword("newPassword123");
        dto.setRole(Rol.ADMIN);

        // Simula el encoder
        when(passwordEncoder.encode("newPassword123")).thenReturn("encodedPassword");

        // Simula la respuesta del repositorio
        UserResponseDTO updatedResponse = new UserResponseDTO();
        updatedResponse.setUserId(1);
        updatedResponse.setName("Juan Updated");
        updatedResponse.setEmail("juan_updated@example.com");
        updatedResponse.setRole(Rol.ADMIN);

        when(userRepository.update(any(CreateUserDTO.class))).thenReturn(updatedResponse);

        // Act
        UserResponseDTO result = userService.update(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("Juan Updated", result.getName());
        assertEquals("juan_updated@example.com", result.getEmail());
        assertEquals(Rol.ADMIN, result.getRole());
    }
}
