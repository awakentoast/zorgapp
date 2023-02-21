/**
 * @author luukb
 */
public class PrintToScreen {

    private PrintToScreen() {throw new IllegalStateException("Utility class");} //good practice according to sonarlint
    public static void printStart() {
        System.out.println();
        System.out.format("%d:  STOP\n", 0);
        System.out.format("%d:  View Patient Data\n", 1);
        System.out.format("%d:  Change User\n", 2);
        System.out.format("%d:  Change Patient\n", 3);
        System.out.format("%d:  Change current Patient Data\n", 4);
        System.out.format("%d:  Add an user\\users\n", 5);
        System.out.format("%d:  Add a patient\\patients\n", 6);
        System.out.format("%d:  Remove a patient\n\n", 7);
        System.out.print("enter #choice: ");
        System.out.println("gitgitgitgit");
    }

    public static void printSwapPatient() {
        System.out.println();
        System.out.println("Type the corresponding number(s) of the data you would like to change; if there are multiple numbers, divide them with a comma ex. 4,5,6 (you cant choose 0 and 6 with other digits):");
        System.out.format("%d:  Return\n", 0);
        System.out.format("%d:  Surname\n", 1);
        System.out.format("%d:  First Name\n", 2);
        System.out.format("%d:  Date of Birth\n", 3);
        System.out.format("%d:  Weight\n", 4);
        System.out.format("%d:  Length\n", 5);
        System.out.format("%d:  Everything\n\n", 6);
        System.out.print("enter #choice: ");
    }
}
