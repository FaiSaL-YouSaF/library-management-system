package com.faisalyousaf777.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Column(name = "user_id", updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_name", nullable = false, unique = true)
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters long")
    private String userName;

    @Column(name = "nic_number", nullable = false, unique = true)
    @Pattern(regexp = "\\d{5}-\\d{7}-\\d", message = "NIC must be in the format 12345-1234567-1")
    @Size(min = 15, max = 15, message = "NIC must be exact 15 digits long including dashes.")
    private String nationalIdentityCardNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "password", nullable = false)
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private LibraryCard libraryCard;

    public User(String userName, String nationalIdentityCardNumber, String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth, String password) {
        this.userName = userName;
        this.nationalIdentityCardNumber = nationalIdentityCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }


    public String toString(){
        return "User [userId=" + userId + ", " +
                "userName=" + userName + ", " +
                "nationalIdentityCardNumber=" + nationalIdentityCardNumber + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "email=" + email + ", " +
                "phoneNumber=" + phoneNumber + ", " +
                "dateOfBirth=" + dateOfBirth + ", " +
                "password=" + password + "]";
    }

}
