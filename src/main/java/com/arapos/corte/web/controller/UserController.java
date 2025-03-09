package com.arapos.corte.web.controller;

import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.domain.Service.UserService;
import com.arapos.corte.persistence.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    // Obtener usuario por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable("id") int userId) {
        return userService.getById(userId)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener usuario por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDTO> getByName(@PathVariable("name") String name) {
        return userService.getByName(name)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener usuario por email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getByEmail(@PathVariable("email") String email) {
        return userService.getByEmail(email)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Filtrar usuarios por rol
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDTO>> getByRole(@PathVariable("role") Rol role) {
        return new ResponseEntity<>(userService.getByRole(role), HttpStatus.OK);
    }

    // Filtrar usuarios por rango de fechas de creación
    @GetMapping("/created-between")
    public ResponseEntity<List<UserResponseDTO>> getByCreatedAtBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(userService.getByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
    }

    // Crear usuario
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> save(@RequestBody CreateUserDTO createUserDTO) {
        return new ResponseEntity<>(userService.save(createUserDTO), HttpStatus.CREATED);
    }

    // Actualizar usuario
    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> update(@RequestBody CreateUserDTO createUserDTO) {
        return new ResponseEntity<>(userService.update(createUserDTO), HttpStatus.OK);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int userId) {
        return userService.delete(userId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
