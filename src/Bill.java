import java.time.LocalDate;

public record Bill(String occupation, String procedure, double price, LocalDate date) {
    public static Bill dentistBill(String procedure, double price, LocalDate date) {
        return new Bill("Dentist", procedure, price, date);
    }

    public static Bill physicalTherapistBill(String procedure, double price, LocalDate date) {
        return new Bill("Physical Therapist", procedure, price, date);
    }

    public static Bill generalPractitionerBill(String procedure, double price, LocalDate date) {
        return new Bill("General Practitioner", procedure, price, date);
    }
}
