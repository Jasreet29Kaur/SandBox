package model;

public class Appointment {
    private final Patient patient;
    private final Doctor doctor;
    private final String slot;
    private final int bookingId;

    public Appointment(Patient patient, Doctor doctor, String slot, int bookingId) {
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getSlot() {
        return slot;
    }
}
