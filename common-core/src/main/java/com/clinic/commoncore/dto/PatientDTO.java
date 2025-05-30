package com.clinic.commoncore.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String address;
}
