package com.clinic.appointmentservice.feignclient;

import com.clinic.commoncore.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", configuration = FeignClientConfig.class)
public interface PatientClient {

    @GetMapping("/patients/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);
}
