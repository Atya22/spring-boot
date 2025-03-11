package com.sp.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sp.library.util.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fname;
    private String lname;
    private LocalDate bday;
    private Gender gender;
    private boolean isActive;
}
