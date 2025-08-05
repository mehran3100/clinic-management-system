package com.clinic.appointmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AppointmentDTO {

    private Long id;

    @NotNull(message = "patientId is required")
    private Long patientId;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in the format YYYY-MM-DD")
    private String appointmentDate;

    @NotNull(message = "timeSlot is required")
    private String timeSlot;

    private String notes;

    public AppointmentDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull(message = "patientId is required") Long getPatientId() {
        return this.patientId;
    }

    public @NotBlank @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in the format YYYY-MM-DD") String getAppointmentDate() {
        return this.appointmentDate;
    }

    public @NotNull(message = "timeSlot is required") String getTimeSlot() {
        return this.timeSlot;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientId(@NotNull(message = "patientId is required") Long patientId) {
        this.patientId = patientId;
    }

    public void setAppointmentDate(@NotBlank @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in the format YYYY-MM-DD") String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setTimeSlot(@NotNull(message = "timeSlot is required") String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AppointmentDTO)) return false;
        final AppointmentDTO other = (AppointmentDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$patientId = this.getPatientId();
        final Object other$patientId = other.getPatientId();
        if (this$patientId == null ? other$patientId != null : !this$patientId.equals(other$patientId)) return false;
        final Object this$appointmentDate = this.getAppointmentDate();
        final Object other$appointmentDate = other.getAppointmentDate();
        if (this$appointmentDate == null ? other$appointmentDate != null : !this$appointmentDate.equals(other$appointmentDate))
            return false;
        final Object this$timeSlot = this.getTimeSlot();
        final Object other$timeSlot = other.getTimeSlot();
        if (this$timeSlot == null ? other$timeSlot != null : !this$timeSlot.equals(other$timeSlot)) return false;
        final Object this$notes = this.getNotes();
        final Object other$notes = other.getNotes();
        if (this$notes == null ? other$notes != null : !this$notes.equals(other$notes)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AppointmentDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $patientId = this.getPatientId();
        result = result * PRIME + ($patientId == null ? 43 : $patientId.hashCode());
        final Object $appointmentDate = this.getAppointmentDate();
        result = result * PRIME + ($appointmentDate == null ? 43 : $appointmentDate.hashCode());
        final Object $timeSlot = this.getTimeSlot();
        result = result * PRIME + ($timeSlot == null ? 43 : $timeSlot.hashCode());
        final Object $notes = this.getNotes();
        result = result * PRIME + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    public String toString() {
        return "AppointmentDTO(id=" + this.getId() + ", patientId=" + this.getPatientId() + ", appointmentDate=" + this.getAppointmentDate() + ", timeSlot=" + this.getTimeSlot() + ", notes=" + this.getNotes() + ")";
    }
}
