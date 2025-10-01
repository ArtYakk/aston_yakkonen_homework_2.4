package com.artemyakkonen.controller;

import com.artemyakkonen.dto.UserDTO;
import com.artemyakkonen.service.UserService;
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
    List<UserDTO> getAllUses(){
        return userService.findAllUsers();
    }

}
