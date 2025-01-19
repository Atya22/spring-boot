package com.sp.library.controller;

import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import com.sp.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
        public void addUser(@RequestBody UserRequestDto dto){
        userService.addUser(dto);
    }

    @GetMapping
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }
}
