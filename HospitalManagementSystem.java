import java.util.Scanner;

class Patient {
    String name;
    int age;
    String gender;
    String condition;

    Patient(String name, int age, String gender, String condition) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.condition = condition;
    }
}

public class HospitalManagementSystem {
    private static final int MAX_PATIENTS = 100;
    private static final Patient[] patients = new Patient[MAX_PATIENTS];
    private static int patientCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        if (patientCount >= MAX_PATIENTS) {
            System.out.println("Hospital is full. Cannot add more patients.");
            return;
        }

        System.out.println("\nEnter patient details:");
        scanner.nextLine(); // Consume newline
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Gender: ");
        String gender = scanner.next();
        System.out.print("Condition: ");
        String condition = scanner.next();

        patients[patientCount++] = new Patient(name, age, gender, condition);
        System.out.println("Patient added successfully.");
    }

    private static void viewPatients() {
        if (patientCount == 0) {
            System.out.println("No patients in the hospital.");
            return;
        }

        System.out.println("\nPatients in the hospital:");
        System.out.println("Name\tAge\tGender\tCondition");
        for (int i = 0; i < patientCount; i++) {
            System.out.println(patients[i].name + "\t" + patients[i].age + "\t" + patients[i].gender + "\t"
                    + patients[i].condition);
        }
    }
}
