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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;

    public UserDTO findUser(Long id){
        try {
            var user = userRepository.findById(id)
                    .orElseThrow(() ->
                            new UserNotFoundException(String.format("User with id %d not found", id)));
            return userMapper.map(user);
        }
        catch (UserNotFoundException e){
            log.info("User with id {} not found", id);
            throw e;
        }
    }

    public UserDTO createUser(UserCreateDTO userDTO){
        log.debug("Creating user with email: {}", userDTO.getEmail());
        try {
            var user = userMapper.map(userDTO);
            var savedUser = userRepository.save(user);

            log.debug("User created successfully: ID={}, email={}", savedUser.getId(), savedUser.getEmail());

            return userMapper.map(savedUser);
        }
        catch (Exception e){
            log.error("User creation failed for email: {}", userDTO.getEmail(), e);
            throw e;
        }
    }

    public void deleteUser(Long id){
        log.debug("Deleting user with id: {}", id);
        try {
            if(!userRepository.existsById(id)) {
                throw new UserNotFoundException(String.format("User with id %d not found", id));
            }
            userRepository.deleteById(id);
            log.debug("User deleted successfully: ID={}", id);
        }
        catch (UserNotFoundException e){
            log.warn("User with id {} not found", id);
            throw e;
        }
        catch (Exception e){
            log.error("User deletion failed for id: {}", id, e);
            throw e;
        }
    }

    public UserDTO updateUser(Long id, UserUpdateDTO dto){
        log.debug("Updating user with id: {}", id);
        try {
            var user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
            userMapper.update(dto, user);
            var updatedUser = userRepository.save(user);
            return userMapper.map(updatedUser);
        }
        catch (Exception e){
            throw e;
        }
    }

    public List<UserDTO> findAllUsers(UserParamsDTO params){
        log.debug("Finding users with params: {}", params);

        try {
            var spec = userSpecification.build(params);
            var pageable = params.toPageable();
            var usersPage = userRepository.findAll(spec, pageable);
            var users = usersPage.getContent();

            if (users.isEmpty()) {
                log.debug("No users found with params: {}", params);
            } else {
                log.debug("Found {} users. Total pages: {}",
                        users.size(),
                        usersPage.getTotalPages());
            }

            return userMapper.fromUsers(users);

        } catch (Exception e) {
            log.error("Failed to find users with params: {}", params, e);
            throw e;
        }
    }
}
