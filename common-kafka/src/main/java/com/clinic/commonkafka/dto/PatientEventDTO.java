package com.clinic.commonkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientEventDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

}
