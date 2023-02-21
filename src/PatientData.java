import java.time.LocalDate;
import java.util.ArrayList;
public class PatientData {

    private PatientData() {throw new IllegalStateException("Utility class");} //cant make an object from patientdata, as its an utility class

    private static ArrayList<Patient> allpatients = new ArrayList<>();
    private static int amountOfPatients;

    //Deze functie mag maar één keer aangeroepen worden, momenteel in Administration
    public static ArrayList<Patient> initialPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1, "Duck", "Donald", LocalDate.of(1934, 6, 9), HandyMethods.calcAge(LocalDate.of(1934, 6, 9)), 30, 130));
        patients.add(new Patient(2, "Baldy", "Caped", LocalDate.of(1997, 6, 9), HandyMethods.calcAge(LocalDate.of(1997, 6, 9)), 75, 175));
        patients.add(new Patient(3, "Henkema", "Henk", LocalDate.of(1999, 9, 9), HandyMethods.calcAge(LocalDate.of(1999, 9, 9)), 50, 160));
        patients.add(new Patient(4, "White", "Walter", LocalDate.of(1958, 9, 7), HandyMethods.calcAge(LocalDate.of(1958, 9, 7)), 98, 191));
        patients.add(new Patient(5, "Hat", "Straw", LocalDate.of(2004, 5, 5), HandyMethods.calcAge(LocalDate.of(2004, 5, 5)), 64, 174));
        allpatients.addAll(patients);
        return patients;
    }

    public static void  addPatient(Patient patient) {allpatients.add(patient);}

    public static ArrayList<Patient> getPatientData() { return allpatients; }

    public static int getAmountOfPatients() {return amountOfPatients;}

    public static void incrementAmountOfPatients() {amountOfPatients++;}

    public static void deletePatient(int index) {allpatients.remove(index - 1);}
}
