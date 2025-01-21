package com.sp.library.dto;

import com.sp.library.util.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Data
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String fname;
    private String lname;
    private LocalDate bday;
    private Gender gender;
}
