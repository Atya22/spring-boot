package com.sp.library.dto;

import com.sp.library.util.enums.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Data
@Getter
@SuperBuilder
public class UserRequestDto {
    private String fname;
    private String lname;
    private LocalDate bday;
    private Gender gender;
}
