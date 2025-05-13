package com.arapos.corte;

import com.arapos.corte.domain.Service.UserService;
import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.domain.repository.UserRepository;
import com.arapos.corte.persistence.entity.Rol;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CorteApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void save_ShouldStoreUserInDatabase() {
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setName("Test User");
		createUserDTO.setEmail("test@example.com");
		createUserDTO.setPassword("password");
		createUserDTO.setRole(Rol.EDITOR);

		UserResponseDTO savedUser = userService.save(createUserDTO);
		Optional<UserResponseDTO> result = userService.getById(savedUser.getUserId());

		assertTrue(result.isPresent());
		UserResponseDTO userResponse = result.get();

		assertEquals("Test User", userResponse.getName());
		assertEquals("test@example.com", userResponse.getEmail());
		assertEquals(Rol.EDITOR, userResponse.getRole());

		assertTrue(userResponse.getUserId() > 0);
	}
}
