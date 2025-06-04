package com.clinic.appointmentservice.controller;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.appointmentservice.kafka.producer.AppointmentKafkaProducer;
import com.clinic.appointmentservice.service.AppointmentService;
import com.clinic.commoncore.dto.AppointmentResponse;
import com.clinic.commonkafka.dto.AppointmentEventDTO;
import com.clinic.commonkafka.event.AppointmentCreatedEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
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
    private final AppointmentKafkaProducer kafka;

    public AppointmentController(AppointmentService service, AppointmentKafkaProducer kafka) {
        this.service = service;
        this.kafka = kafka;
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AppointmentDTO>> createBatch(@Valid @RequestBody List<AppointmentDTO> dtos) {
        return ResponseEntity.ok(service.createBatch(dtos));
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@Valid @RequestBody AppointmentDTO dto) {
        AppointmentDTO saved = service.create(dto);
        AppointmentEventDTO eventDTO = new AppointmentEventDTO(
                saved.getId(),
                saved.getPatientId(),
                999L,
                saved.getAppointmentDate(),
                saved.getTimeSlot()
        );

        AppointmentCreatedEvent event = new AppointmentCreatedEvent(eventDTO);
        kafka.send(event);


        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(
            summary = "Get paginated appointments",
            description = "Use query parameters like ?page=0&size=10&sort=id,asc. Avoid sort=[\"string\"]"
    )
    @GetMapping
    public Page<AppointmentDTO> getAllAppointments(
            @ParameterObject
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return service.getAllAppointments(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/{id}/with-patient")
    public ResponseEntity<AppointmentResponse> getWithPatient(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAppointmentWithPatient(id));
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

}
