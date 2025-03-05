package controller;

import model.Doctor;
import model.Patient;
import service.AppointmentService;

import java.util.List;

public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void registerDoctor(Doctor doctor) {
        appointmentService.registerDoctor(doctor);
    }

    public void registerPatient(Patient patient) {
        appointmentService.registerPatient(patient);
    }

    public Patient findOrCreatePatient(String name) {
        return appointmentService.findOrCreatePatient(name);
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return appointmentService.getDoctorsBySpecialty(specialty);
    }

    public void markDoctorAvailability(String doctorName, String[] slots) {
        appointmentService.markDoctorAvailability(doctorName, slots);
    }

    public void showAvailableSlots(String specialty) {
        appointmentService.showAvailableSlots(specialty);
    }

    public void bookAppointment(Patient patient, Doctor doctor, String slot) {
        appointmentService.bookAppointment(patient, doctor, slot);
    }

    public void cancelBooking(String doctorName, int bookingId) {
        appointmentService.cancelBooking(doctorName, bookingId);
    }
    
    // New method to handle booking appointment
    public void bookAppointmentWithDoctorAndSlot(Patient patient, String specialty, String doctorName, String slot) {
        appointmentService.bookAppointment(patient, doctorName, slot);
    }

    // New method for getting available slots of a doctor
    public void showDoctorSlots(String doctorName) {
        appointmentService.showDoctorSlots(doctorName);
    }

    // New method to get all doctors under a specific specialty
    public List<Doctor> getAllDoctorsUnderSpecialty(String specialty) {
        return appointmentService.getDoctorsBySpecialty(specialty);
    }

    // Method to register a doctor and mark their availability immediately
public void registerDoctorWithAvailability(Doctor doctor, String[] availableSlots) {
    // Register the doctor
    appointmentService.registerDoctor(doctor);
    
    // Mark the doctor's availability
    appointmentService.markDoctorAvailability(doctor.getName(), availableSlots);
}

}
