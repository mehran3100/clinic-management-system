package com.clinic.commonkafka.dto;

import lombok.Data;

@Data
public class PatientEventDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public PatientEventDTO() {}

    public PatientEventDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
