import java.util.ArrayList;

public class MedicationData {

    private final ArrayList<Medication> medicationList = new ArrayList<>();
    private int amountOfMedication = 0;

    public MedicationData() {
        //TODO document why this constructor is empty
    }

    public ArrayList<Medication> getAllMedicationData() {
        return medicationList;
    }

    public void addMedication(Medication medication) {
        medicationList.add(medication);
        amountOfMedication++;
    }

    public Medication getMedication(int i) {
        return medicationList.get(i - 1);
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
}
