package model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private String specialty;
    private List<String> availableSlots;

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
        this.availableSlots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> slots) {
        this.availableSlots = slots;
    }

    public void addSlot(String slot) {
        this.availableSlots.add(slot);
    }
}
