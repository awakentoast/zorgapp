import java.util.ArrayList;

public class UserData {

    private UserData() {throw new IllegalStateException("Utility class");} //cant make an object from userdata, as it's a utility class

    private static ArrayList<User> allUsers = new ArrayList<>();
    private static int amountOfUsers;

    // Deze functie mag maar één keer aangeroepen worden, momenteel in Administration
    public static void initialUsers() {
        ArrayList<User> users = new ArrayList<>(3);
        users.add(new User(1, "El Chapo"));
        users.add(new User(2, "Barry Batsbak"));
        users.add(new User(3, "Kenzo Tenma"));
        allUsers.addAll(users);
    }
    public static void addUser(User user) { allUsers.add(user); }
    public static ArrayList<User> getUserData() { return allUsers; }

    public static int getAmountOfUsers() {return amountOfUsers;}

    public static void incrementAmountOfUsers() {amountOfUsers++;}

}
