package com.faisalyousaf777.dto;

import com.faisalyousaf777.models.LibraryCard;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String password;
    private LibraryCard libraryCard;
}
