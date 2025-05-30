package com.clinic.appointmentservice.controller;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.appointmentservice.service.AppointmentService;
import com.clinic.commoncore.dto.AppointmentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Appointments", description = "Appointment CRUD operations")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AppointmentDTO>> createBatch(@Valid @RequestBody List<AppointmentDTO> dtos) {
        return ResponseEntity.ok(service.createBatch(dtos));
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public Page<AppointmentDTO> getAllAppointments(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return service.getAllAppointments(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteBatch(@RequestBody List<Long> ids) {
        service.deleteBatch(ids);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PutMapping("/batch")
    public ResponseEntity<List<AppointmentDTO>> updateBatch(@Valid @RequestBody List<AppointmentDTO> dtos) {
        return ResponseEntity.ok(service.updateBatch(dtos));
    }

    @GetMapping("/{id}/with-patient")
    public ResponseEntity<AppointmentResponse> getWithPatient(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAppointmentWithPatient(id));
    }



}
