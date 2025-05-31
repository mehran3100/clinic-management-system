package com.clinic.patientservice.controller;

import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "Patient CRUD operations")
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

    @Operation(
            summary = "Get paginated patients",
            description = "Use query parameters like ?page=0&size=10&sort=id,asc. Avoid sort=[\"string\"]"
    )
    @GetMapping
    public Page<PatientDTO> getAllPatients(
            @ParameterObject
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return service.getAllPatients(pageable);
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
