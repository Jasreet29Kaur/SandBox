package service;

import model.Doctor;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    public void registerDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void registerPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient findOrCreatePatient(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        Patient newPatient = new Patient(name);
        patients.add(newPatient);
        return newPatient;
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialty().equals(specialty)) {
                availableDoctors.add(doctor);
            }
        }
        return availableDoctors;
    }

    public void markDoctorAvailability(String doctorName, String[] slots) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(doctorName)) {
                doctor.setAvailableSlots(List.of(slots));
            }
        }
    }

    public void showAvailableSlots(String specialty) {
        List<Doctor> availableDoctors = getDoctorsBySpecialty(specialty);
        if (availableDoctors.isEmpty()) {
            System.out.println("No doctors available for this specialty.");
            return;
        }
        for (Doctor doctor : availableDoctors) {
            System.out.println(doctor.getName());
            for (String slot : doctor.getAvailableSlots()) {
                System.out.println("  - " + slot);
            }
        }
    }

    public void bookAppointment(Patient patient, Doctor doctor, String slot) {
        // Logic to book the appointment
        System.out.println("Appointment booked for " + patient.getName() + " with Dr. " + doctor.getName() + " at " + slot);
    }

    public void bookAppointment(Patient patient, String doctorName, String slot) {
        Doctor doctor = findDoctorByName(doctorName);
        if (doctor != null) {
            bookAppointment(patient, doctor, slot);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void cancelBooking(String doctorName, int bookingId) {
        // Logic for canceling the appointment
        System.out.println("Booking " + bookingId + " canceled for Dr. " + doctorName);
    }

    // New method to show available slots for a particular doctor
    public void showDoctorSlots(String doctorName) {
        Doctor doctor = findDoctorByName(doctorName);
        if (doctor != null) {
            System.out.println("Available slots for " + doctorName + ":");
            for (String slot : doctor.getAvailableSlots()) {
                System.out.println("  - " + slot);
            }
        } else {
            System.out.println("Doctor not found.");
        }
    }

    // Helper method to find a doctor by name
    private Doctor findDoctorByName(String doctorName) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(doctorName)) {
                return doctor;
            }
        }
        return null;
    }
}
