package com.clinic.appointmentservice.repository;

import com.clinic.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
