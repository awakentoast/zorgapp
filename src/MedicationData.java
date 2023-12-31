import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MedicationData {

    private final List<Medication> medicationList = new ArrayList<>();
    private int amountOfMedication = 0;

    public MedicationData() {
        //TODO document why this constructor is empty
    }

    public List<Medication> getAllMedicationData() {
        return medicationList;
    }

    public void addMedication(Medication medication) {
        medicationList.add(medication);
        amountOfMedication++;
    }

    public Medication getMedication(int i) {
        Medication medication = medicationList.get(i - 1);
        return new Medication(medication.getSubstance(), medication.getType(), medication.getDose());
    }

    public int getAmountOfMedication() {
        return amountOfMedication;
    }

    public void changeMedicationDosage(int index, String newDosage) {
        index = index - 1;
        String substance = medicationList.get(index).getSubstance();
        String type = medicationList.get(index).getType();
        medicationList.remove(index);
        medicationList.add(index, new Medication(substance, type, newDosage));
    }

    public void deleteMedication(int index) {
        medicationList.remove(index - 1);
        amountOfMedication--;
    }

    public void printMedications() {
        int i = 1;
        for (Medication medication : medicationList) {
            i++;
            System.out.format("%d: Substance: %s\n", i, medication.getSubstance());
            System.out.format("   Type: %s\n", medication.getType());
            System.out.format("   Dosage: %s\n\n", medication.getDose());
        }
    }
}
