package com.artemyakkonen.aston_spring_boot.service;

import com.artemyakkonen.aston_spring_boot.dto.UserCreateDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserParamsDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserUpdateDTO;
import com.artemyakkonen.aston_spring_boot.exception.UserNotFoundException;
import com.artemyakkonen.aston_spring_boot.mapper.UserMapper;
import com.artemyakkonen.aston_spring_boot.repository.UserRepository;
import com.artemyakkonen.aston_spring_boot.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;

    public UserDTO findUser(Long id){
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
        return userMapper.map(user);
    }

    public UserDTO createUser(UserCreateDTO userDTO){
        var user = userMapper.map(userDTO);
        var savedUser = userRepository.save(user);
        return userMapper.map(savedUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        }
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Long id, UserUpdateDTO dto){
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
        userMapper.update(dto, user);
        var updatedUser = userRepository.save(user);
        return userMapper.map(updatedUser);
    }

    public List<UserDTO> findAllUsers(UserParamsDTO params){
        var spec = userSpecification.build(params);
        var pageable = params.toPageable();
        var usersPage = userRepository.findAll(spec, pageable);
        var users = usersPage.getContent();
        return userMapper.fromUsers(users);
    }
}
