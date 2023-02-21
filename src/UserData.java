import java.util.ArrayList;

public class UserData {

    private UserData() {throw new IllegalStateException("Utility class");} //cant make an object from userdata, as its an utility class

    private static ArrayList<User> allusers = new ArrayList<>();
    private static int amountOfUsers;

    // Deze functie mag maar één keer aangeroepen worden, momenteel in Administration
    public static ArrayList<User> initialUsers() {
        ArrayList<User> users = new ArrayList<>(3);
        users.add(new User(1, "El Chapo"));
        users.add(new User(2, "Barry Batsbak"));
        users.add(new User(3, "Kenzo Tenma"));
        allusers.addAll(users);
        return users;
    }
    public static void addUser(User user) { allusers.add(user); }
    public static void addUsers(ArrayList<User> users) { allusers.addAll(users); }
    public static ArrayList<User> getUserData() { return allusers; }

    public static int getAmountOfUsers() {return amountOfUsers;}

    public static void incrementAmountOfUsers() {amountOfUsers++;}

}
