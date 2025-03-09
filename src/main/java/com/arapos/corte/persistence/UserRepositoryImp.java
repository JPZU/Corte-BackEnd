package com.arapos.corte.persistence;

import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;
import com.arapos.corte.domain.repository.UserRepository;
import com.arapos.corte.persistence.crud.UserCrudRepository;
import com.arapos.corte.persistence.entity.Rol;
import com.arapos.corte.persistence.entity.User;
import com.arapos.corte.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class UserRepositoryImp implements UserRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponseDTO> getAll(){
        Iterable<User> users = userCrudRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> getById(int userId){
        return userCrudRepository.findById(userId)
                .map(userMapper::toUserResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> getByName(String name){
        return userCrudRepository.findByName(name)
                .map(userMapper::toUserResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> getByEmail(String email){
        return userCrudRepository.findByEmail(email)
                .map(userMapper::toUserResponseDTO);
    }

    @Override
    public List<UserResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        Iterable<User> users = userCrudRepository.findByCreatedAtBetween(startDate, endDate);
        return StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> findByRole(Rol role){
        Iterable<User> users = userCrudRepository.findByRole(role);
        return StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO save(CreateUserDTO createUserDTO){
        if (createUserDTO.getUserId() != 0){
            throw new IllegalArgumentException("Id cannot be present for create a new user");
        }
        User userEntity = userMapper.toUser(createUserDTO);
        User savedUser = userCrudRepository.save(userEntity);
        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO update(CreateUserDTO createUserDTO){
        Optional<User> existingUser = userCrudRepository.findById(createUserDTO.getUserId());
        if (existingUser.isPresent()){
            User userToUpdate = existingUser.get();
            userToUpdate.setName(createUserDTO.getName());
            userToUpdate.setEmail(createUserDTO.getEmail());
            userToUpdate.setPassword(createUserDTO.getPassword());
            userToUpdate.setRole(createUserDTO.getRole());
            userCrudRepository.save(userToUpdate);
            return userMapper.toUserResponseDTO(userToUpdate);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public void delete(int userId){
        if(userCrudRepository.existsById(userId)){
            userCrudRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
