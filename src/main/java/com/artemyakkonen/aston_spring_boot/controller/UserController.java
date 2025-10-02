package com.artemyakkonen.aston_spring_boot.controller;

import com.artemyakkonen.aston_spring_boot.dto.UserCreateDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserParamsDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserUpdateDTO;
import com.artemyakkonen.aston_spring_boot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO getUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserDTO> getAllUses(@Valid UserParamsDTO params){
        return userService.findAllUsers(params);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO createUser(@RequestBody @Valid UserCreateDTO dto){
        return userService.createUser(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO updateUser(@RequestBody @Valid UserUpdateDTO dto, @PathVariable Long id){
        return userService.updateUser(id, dto);
    }

}
