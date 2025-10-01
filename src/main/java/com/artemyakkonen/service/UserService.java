package com.artemyakkonen.service;

import com.artemyakkonen.dto.UserCreateDTO;
import com.artemyakkonen.dto.UserDTO;
import com.artemyakkonen.dto.UserUpdateDTO;
import com.artemyakkonen.exception.UserNotFoundException;
import com.artemyakkonen.mapper.UserMapper;
import com.artemyakkonen.repository.UserRepository;
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

    public List<UserDTO> findAllUsers(){
        var users = userRepository.findAll();
        return userMapper.fromUsers(users);
    }
}
