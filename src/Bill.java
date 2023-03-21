public record Bill(String occupation, String procedure, double price) {
    public static Bill dentistBill(String procedure, double price) {
        return new Bill("Dentist", procedure, price);
    }

    public static Bill physicalTherapistBill(String procedure, double price) {
        return new Bill("Physical Therapist", procedure, price);
    }

    public static Bill generalPractitionerBill(String procedure, double price) {
        return new Bill("General Practitioner", procedure, price);
    }
}
