package com.sp.library.dto;

import com.sp.library.util.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Data
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @Email
    private String fname;
    @Pattern(regexp = "^.{2,15}$")
    private String lname;
    private LocalDate bday;
    private Gender gender;
}
