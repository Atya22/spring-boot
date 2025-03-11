package com.sp.library.service;

import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void  addUser(UserRequestDto dto);
    List<UserResponseDto> getUsers();
    UserResponseDto getUser(Long id);
    Long updateUser(Long id, UserRequestDto dto);
}
