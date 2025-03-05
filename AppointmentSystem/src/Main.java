import controller.AppointmentController;
import model.Doctor;
import model.Patient;
import service.AppointmentService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static AppointmentService appointmentService = new AppointmentService();
    private static AppointmentController controller = new AppointmentController(appointmentService);
    private static boolean sessionActive = true;
    private static Object currentUser = null; // Can be either Patient or Doctor

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined Use Case - Uncomment the following lines to test with hardcoded data

        // Default I/O Part
        // AppointmentService appointmentService = new AppointmentService();
        // AppointmentController appointmentController = new AppointmentController(appointmentService);

        // Example for registering doctors and patients
        // Doctor doctor1 = new Doctor("Dr. Curious", "Cardiologist");
        // Doctor doctor2 = new Doctor("Dr. Dreadful", "Dermatologist");

        // Register doctors
        // appointmentController.registerDoctor(doctor1);
        // appointmentController.registerDoctor(doctor2);

        // Set doctor's availability
        // appointmentController.markDoctorAvailability("Dr. Curious", "9:00-10:00", "12:00-13:00", "16:00-17:00");
        // appointmentController.markDoctorAvailability("Dr. Dreadful", "11:00-12:00", "14:00-15:00");

        // Register patients
        // Patient patientA = new Patient("PatientA");
        // appointmentController.registerPatient(patientA);

        // Show available slots for a specialty
        // appointmentController.showAvailableSlots("Cardiologist");

        // Book appointments for patients
        // appointmentController.bookAppointment("PatientA", "Dr. Curious", "12:00");

        // Show appointments for a patient
        // appointmentController.showAppointmentsBooked("PatientA");

        // Cancel an appointment
        // appointmentController.cancelBooking(1234); // Example booking ID

        // Show available slots after canceling
        // appointmentController.showAvailableSlots("Cardiologist");

        // Register another patient and doctor
        // Patient patientB = new Patient("PatientB");
        // appointmentController.registerPatient(patientB);
        // Doctor doctor3 = new Doctor("Dr. Daring", "Dermatologist");
        // appointmentController.registerDoctor(doctor3);

        // Mark doctor's availability
        // appointmentController.markDoctorAvailability("Dr. Daring", "13:00-14:00");

        // Book appointments with this new doctor
        // appointmentController.bookAppointment("PatientB", "Dr. Daring", "13:00");

        // Show available slots for dermatologists
        // appointmentController.showAvailableSlots("Dermatologist");

        // Custom I/O Part - Uncomment below to test with CLI

        // Default I/O Part
        AppointmentService appointmentService = new AppointmentService();
        AppointmentController controller = new AppointmentController(appointmentService);

        while (true) {
            System.out.println("Welcome to the Appointment System");
            System.out.println("1. Register Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Show Available Doctors by Specialty");
            System.out.println("4. Book Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Register Doctor and directly ask for available slots
                    System.out.print("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter Specialty: ");
                    String specialty = scanner.nextLine();
                    System.out.print("Enter Available Slots (comma-separated): ");
                    String slots = scanner.nextLine();
                    List<String> slotList = Arrays.asList(slots.split(","));
                    controller.registerDoctorWithAvailability(new Doctor(doctorName, specialty), slotList.toArray(new String[0]));
                    break;

                case 2:
                    // Register a new patient
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    controller.registerPatient(new Patient(patientName));
                    break;

                case 3:
                    // Show doctors by specialty
                    System.out.print("Enter Specialty: ");
                    specialty = scanner.nextLine();
                    controller.showAvailableSlots(specialty);
                    break;

                case 4:
                    // Book Appointment
                    System.out.print("Enter Patient Name: ");
                    patientName = scanner.nextLine();
                    // Check if patient exists, if not, create new patient
                    Patient patient = controller.findOrCreatePatient(patientName);

                    System.out.print("Enter Doctor Specialty: ");
                    specialty = scanner.nextLine();

                    // Show doctors under the specialty
                    List<Doctor> doctors = controller.getDoctorsBySpecialty(specialty);
                    if (doctors.isEmpty()) {
                        System.out.println("No doctors available under this specialty.");
                        break;
                    }
                    System.out.println("Available doctors:");
                    for (int i = 0; i < doctors.size(); i++) {
                        System.out.println((i + 1) + ". " + doctors.get(i).getName());
                    }

                    System.out.print("Select Doctor (enter number): ");
                    int doctorChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (doctorChoice < 1 || doctorChoice > doctors.size()) {
                        System.out.println("Invalid doctor selection. Please try again.");
                        break;
                    }

                    Doctor selectedDoctor = doctors.get(doctorChoice - 1);
                    controller.showDoctorSlots(selectedDoctor.getName());

                    // Now, ask the user to select a time slot
                    int slotChoice = -1;
                    while (slotChoice == -1) {
                        try {
                            System.out.print("Select a Slot (enter number): ");
                            slotChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (slotChoice < 1 || slotChoice > selectedDoctor.getAvailableSlots().size()) {
                                System.out.println("Invalid slot number. Please enter a valid number.");
                                slotChoice = -1;
                            } else {
                                String selectedSlot = selectedDoctor.getAvailableSlots().get(slotChoice - 1);
                                controller.bookAppointment(patient, selectedDoctor, selectedSlot);
                                System.out.println("Appointment booked successfully.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Consume invalid input
                        }
                    }
                    break;

                case 5:
                    // Cancel Appointment
                    System.out.print("Enter Doctor Name: ");
                    doctorName = scanner.nextLine();
                    System.out.print("Enter Booking ID: ");
                    int bookingId = scanner.nextInt();
                    controller.cancelBooking(doctorName, bookingId);
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting... Have a nice day! Stay happy and healthy.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Adding space after each iteration to make output clearer
            System.out.println("\n----------------------------\n");
        }
    }
}
