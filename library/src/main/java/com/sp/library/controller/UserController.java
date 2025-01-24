package com.sp.library.controller;

import com.sp.library.dao.repository.UserRepository;
import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import com.sp.library.mapper.UserMapper;
import com.sp.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping
    public void addUser(@RequestBody UserRequestDto dto) {
        userService.addUser(dto);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
       return userService.getUser(id);
    }

    @PostMapping("set-is-deleted-true")
    public void updatedSetIsDeletedTrue(){
        var userList = userRepository.findAllByIsDeleted(false);
        for(var user : userList) {
            user.setIsDeleted(true);
            user.setIsActive(false);
        }
        userRepository.saveAll(userList);
        }

        @PostMapping("update/{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto){
        return userService.updateUser(id, dto);
        }

//       put, patch, update
    }

