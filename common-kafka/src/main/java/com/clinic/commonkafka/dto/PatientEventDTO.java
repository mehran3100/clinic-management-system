package com.clinic.commonkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientEventDTO {
    private Long id;
    private String firstName;
    private String lastName;

}
