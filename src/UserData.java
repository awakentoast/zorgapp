import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luukb
 */
public class UserData {
    private int amountOfUsers = 0;

    private final ArrayList<User> allUsers = new ArrayList<>();
    private final String[] allOccupations = {"General Practitioner", "Dentist", "Physical Therapist"};


    public UserData() {
        // TODO document why this constructor is empty
    }

    public void addUser(User user) {
        allUsers.add(user);
        amountOfUsers++;
    }

    public int getAmountOfOccupations() {
        return allOccupations.length;
    }

    public String[] getAllOccupations() {
        return allOccupations;
    }

    public User getUser(int i) {return allUsers.get(i - 1);}

    public int getAmountOfUsers() {return amountOfUsers;}

    public void printUsers() {
        int i = 0;
        for (User user : allUsers) {
            i++;
            System.out.format("%d: %s [%s]\n", i, user.getUserName(), user.getOccupation());
        }
    }
}
