import java.util.ArrayList;

public class PatientData {

    private static ArrayList<Patient> allPatients = new ArrayList<>();
    private int amountOfPatients = 0;


    public PatientData() {
        // TODO document why this constructor is empty
    }

    public void addPatient(Patient patient) {
        allPatients.add(patient);
        amountOfPatients++;
    }

    public Patient getPatient(int i) {
        return allPatients.get(i);
    }
    public ArrayList<Patient> getPatientData() {
        return allPatients;
    }

    public int getAmountOfPatients() {
        return amountOfPatients;
    }

    public void deletePatient(int index) {
        allPatients.remove(index - 1);
        amountOfPatients--;
    }
}
