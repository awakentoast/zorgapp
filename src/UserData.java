import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luukb
 */
public class UserData {
    private int amountOfUsers = 0;
    private final String[] occupations = {"General Practitioner", "Dentist", "Physical Therapist"};

    //map with the corresponding medical editing at index 0 and medical insight at index 1 of the different occupations
    Map<String, List<Boolean>> medicalAuthorization = Map.of(
            //                               editing|insight
                "General Practitioner", List.of(true, true),
            "Dentist",              List.of(false, true),
            "Physical Therapist",   List.of(false, false)
    );


    private final ArrayList<User> allUsers = new ArrayList<>();


    public UserData() {
        // TODO document why this constructor is empty
    }

    public void addUser(User user) {
        allUsers.add(user);
        amountOfUsers++;
    }

    public boolean getAllowedMedicationEditing(String occupation) {
        return medicalAuthorization.get(occupation).get(0);
    }

    public boolean getAllowedMedicationInsight(String occupation) {
        return medicalAuthorization.get(occupation).get(1);
    }
    public User getUser(int i) {return allUsers.get(i - 1);}
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
