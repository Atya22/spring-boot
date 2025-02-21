package com.sp.library.controller;

import com.sp.library.dao.repository.UserRepository;
import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import com.sp.library.mapper.UserMapper;
import com.sp.library.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponseDto addUser(@Valid @RequestBody UserRequestDto dto) {
        userService.addUser(dto);
        return new UserResponseDto();
//        because UserResponseDto has only 1 parameter there's no need for the builder.
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/cache")
    @CacheEvict(value = "users", allEntries = true)
    public void clearCache() {
    }

    @GetMapping("{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("set-is-deleted-true")
    public void updatedSetIsDeletedTrue() {
        var userList = userRepository.findAllByIsDeleted(false);
        for (var user : userList) {
            user.setIsDeleted(true);
            user.setIsActive(false);
        }
        userRepository.saveAll(userList);
    }

    @PatchMapping("update/{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return userService.updateUser(id, dto);
    }
}

