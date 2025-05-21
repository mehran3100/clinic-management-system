package com.clinic.patientservice.controller;

import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService patientService) {
        this.service = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO dto) {
        return ResponseEntity.ok(service.createPatient(dto));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<PatientDTO>> createBatch(@Valid @RequestBody List<PatientDTO> dtos) {
        return ResponseEntity.ok(service.createBatch(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPatientById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(service.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDTO dto) {
        return ResponseEntity.ok(service.updatePatient(id, dto));
    }

    @PutMapping("/batch")
    public ResponseEntity<List<PatientDTO>> updateBatch(@Valid @RequestBody List<PatientDTO> dtos) {
        return ResponseEntity.ok(service.updateBatch(dtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteBatch(@RequestBody List<Long> ids) {
        service.deleteBatch(ids);
        return ResponseEntity.noContent().build();
    }
}
