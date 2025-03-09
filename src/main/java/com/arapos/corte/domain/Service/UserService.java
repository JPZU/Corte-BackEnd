package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.domain.repository.UserRepository;
import com.arapos.corte.persistence.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<UserResponseDTO> getAll(){
        return userRepository.getAll();
    }

    public Optional<UserResponseDTO> getById(int userId){
        return userRepository.getById(userId);
    }

    public Optional<UserResponseDTO> getByName(String name){
        return userRepository.getByName(name);
    }

    public Optional<UserResponseDTO> getByEmail(String email){
        return userRepository.getByEmail(email);
    }

    public List<UserResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return userRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<UserResponseDTO> getByRole(Rol role){
        return userRepository.findByRole(role);
    }

    public UserResponseDTO save(CreateUserDTO createUserDTO){
//        Hash password before save in the user
//        createUserDTO.setPassword(bCryptPasswordEncoder.encode(createUserDTO.getPassword()));
        return userRepository.save(createUserDTO);
    }

    public UserResponseDTO update(CreateUserDTO createUserDTO){
        return userRepository.update(createUserDTO);
    }

    public boolean delete(int userId){
        if(userRepository.getById(userId).isPresent()){
            userRepository.delete(userId);
            return true;
        } else {
            return false;
        }
    }
}
