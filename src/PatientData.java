import java.util.ArrayList;

/**
 * @author luukb
 */
public class PatientData {

    private final ArrayList<Patient> allPatients = new ArrayList<>();
    private int amountOfPatients = 0;


    public PatientData() {
        // TODO document why this constructor is empty
    }

    public void addPatient(Patient patient) {
        allPatients.add(patient);
        amountOfPatients++;
    }

    public Patient getPatient(int i) {
        return allPatients.get(i - 1);
    }

    public int getAmountOfPatients() {
        return amountOfPatients;
    }

    public void deletePatient(int index) {
        allPatients.remove(index - 1);
        amountOfPatients--;
    }

    public void printPatients() {
        int i = 0;
        for (Patient patient : allPatients) {
            i++;
            System.out.format("%d: %s\n", i, patient.fullName());
        }
    }
}
