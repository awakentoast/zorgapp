import java.util.ArrayList;

public class UserData {

    private ArrayList<User> allUsers = new ArrayList<>();
    private int amountOfUsers = 0;
    private final String[] occupations = {"General Practitioner", "Dentist", "Physical Therapist"};
    private final boolean[] allowedMedicationEditings = {true, false, false};
    private final boolean[] allowedMedicationInsights = {true, true, false};

    public boolean getAllowedMedicationEditing(int index) {
        return allowedMedicationEditings[index - 1];
    }

    public boolean getAllowedMedicationInsight(int index) {
        return allowedMedicationInsights[index - 1];
    }

    public UserData() {
        // TODO document why this constructor is empty
    }

    public void addUser(User user) {
        allUsers.add(user);
        amountOfUsers++;
    }

    public ArrayList<User> getUserData() {return allUsers;}

    public int getAmountOfUsers() {return amountOfUsers;}

    public String[] getOccupations() {
        return occupations;
    }

    public String getOccupation(int i) {
        return occupations[i - 1];
    }

    public int getAmountOfOccupations() {
        return occupations.length;
    }
}
