package com.sp.library.service;

import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void  addUser(UserRequestDto dto);
    List<UserResponseDto> getUsers();
    void getUser();
}
