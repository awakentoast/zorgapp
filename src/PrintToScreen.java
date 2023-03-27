/**
 * @author luukb
 */
public class PrintToScreen {

    private PrintToScreen() {throw new IllegalStateException("Utility class");} //good practice according to sonarlint
    public static void printStart(boolean ChangeMedication) {
        System.out.println();
        System.out.format("%d:  STOP\n", 0);
        System.out.format("%d:  View patient data\n", 1);
        System.out.format("%d:  Change user\n", 2);
        System.out.format("%d:  Change patient\n", 3);
        System.out.format("%d:  Change current patient data\n", 4);
        System.out.format("%d:  Add a user/users\n", 5);
        System.out.format("%d:  Add a patient/patients\n", 6);
        System.out.format("%d:  Remove a patient/patients\n", 7);
        System.out.format("%d:  Add bill\n", 8);
        System.out.format("%d:  Display billing history\n", 9);
        System.out.format("%d:  Display BMI chart\n", 10);
        System.out.format("%d:  Display Long Capacity chart\n", 11);
        System.out.format("%d:  Sort patient list\n", 12);

        if (ChangeMedication) {
            System.out.format("%d:  Add medication\n", 13);
            System.out.format("%d:  Change medication dosage\n", 14);
            System.out.format("%d:  Delete medication from patient\n", 15);
        }

        System.out.print("\nEnter #choice: ");
    }

    public static void printPatientParameters() {
        System.out.println("\nType the corresponding number(s) of the data you would like to change; if there are multiple numbers, divide them with a comma ex. 4,5,6 (you cant choose 0 and 6 with other digits):\n");
        System.out.format("%d:  Return\n", 0);
        System.out.format("%d:  Surname\n", 1);
        System.out.format("%d:  First Name\n", 2);
        System.out.format("%d:  Date of Birth\n", 3);
        System.out.format("%d:  Weight\n", 4);
        System.out.format("%d:  Length\n", 5);
        System.out.format("%d:  Lung capacity\n", 6);

        System.out.format("%d:  Everything\n", 7);
        System.out.print("\nEnter #choice: ");
    }
}
