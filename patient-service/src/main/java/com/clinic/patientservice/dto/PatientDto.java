package com.clinic.patientservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class PatientDto {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Positive(message = "Age must be positive")
    private int age;

    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private String email;

    private String phone;
    private String address;
}
