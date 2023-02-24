import java.time.LocalDate;
import java.util.Objects;

   ///////////////////////////////////////////////////////////////////////////
   // class Administration represents the core of the application by showing
   // the main menu, from where all other functionality is accessible, either
   // directly or via sub-menus.
   //
   // An Administration instance needs a User as input, which is passed via the
   // constructor to the data member 'currentUser'.
   // The patient data is available via the data member currentPatient.
   /////////////////////////////////////////////////////////////////
class Administration {

   private Administration() {
         throw new IllegalStateException("Utility class");
      }

   static final int STOP = 0;
   static final int VIEW = 1;
   static final int SWAP_USER = 2;
   static final int SWAP_PATIENT = 3;
   static final int CHANGE_CURRENT_PATIENT_INFORMATION = 4;
   static final int ADD_USER = 5;
   static final int ADD_PATIENT = 6;
   static final int REMOVE_PATIENTS = 7;

   static final int RETURN      = 0;
   static final int FIRSTNAME   = 1;
   static final int SURNAME     = 2;
   static final int DATEOFBIRTH = 3;
   static final int WEIGHT      = 4;
   static final int LENGTH      = 5;


   private static User currentUser = UserData.getUserData().get(0);
   private static Patient currentPatient = PatientData.getPatientData().get(0);
   private static final BScanner bScan = new BScanner();


   private static void swapUser() {
      if (UserData.getAmountOfUsers() != 0) {

         System.out.println();
         System.out.println("What user do you want to switch to?");
         System.out.println("0: Return");

         int i = 1;
         for (User user : UserData.getUserData()) {
            System.out.printf("%d: %s \n", i, user.getUserName());
            i++;
         }
         System.out.println();
         System.out.print("Enter #choice: ");

         int userToSwitchTo = HandyMethods.correctInput(1, UserData.getAmountOfUsers(), bScan.nextInt());
         if (userToSwitchTo != 0) {
            currentUser = UserData.getUserData().get(userToSwitchTo - 1);
         }

         System.out.println();
      }
      else {System.out.println("There are no users, first add more users");}
      }

   private static void swapPatient() {
      if (PatientData.getAmountOfPatients() != 0) {
         System.out.println();
         System.out.println("What patient do you want to switch to?\n");

         System.out.println("0: Return");
         int i = 1;
         for (Patient patient : PatientData.getPatientData()) {
            System.out.printf("%d: %s \n", i, patient.fullName());
            i++;
         }
         System.out.println();
         System.out.print("Enter choice:");

         int patientToSwitchTo = HandyMethods.correctInput(1, PatientData.getAmountOfPatients(), bScan.nextInt());

         if (patientToSwitchTo != 0) {
            currentPatient = PatientData.getPatientData().get(patientToSwitchTo - 1);
         }
         System.out.println();
      }
      else {System.out.println("There are no patients, first add more patients");}
   }

   private static void changePatientData() {

      //prints the choices for the user
      PrintToScreen.printSwapPatient();

      //prevents for anything but integers and 1,2 integers being passed along
      String str = HandyMethods.validPhrase(bScan.nextLine());

      int[] fieldsToBeChanged = HandyMethods.stringToNumbers(str, 0, LENGTH);

      for (int fieldToBeChanged : fieldsToBeChanged) {
         switch (fieldToBeChanged) {
            case RETURN -> System.out.println("You have chosen to not change the data");
            case FIRSTNAME -> {
               System.out.println("What do you want to change the first name to?");
               currentPatient.setFirstName(bScan.nextLine());
            }
            case SURNAME -> {
               System.out.println("What do you want to change the surname to?");
               currentPatient.setSurname(bScan.nextLine());
            }
            case DATEOFBIRTH -> {
               System.out.println("What do you want the new date of birth to be? (YYYY-MM-DD)");
               currentPatient.setDateOfBirth(bScan.nextDate(bScan.nextLine()));
            }
            case WEIGHT -> {
               System.out.println("What do you want the weight to be? (kg)");
               currentPatient.setWeight(bScan.nextDouble());
            }
            case LENGTH -> {
               System.out.println("What do you want the new length to be? (cm)");
               currentPatient.setLength(bScan.nextDouble());
            }
            default -> {
               System.out.println(fieldToBeChanged);
               System.out.println("you made an oopsie :3");
            }
         }
      }
   }

   private static void addUser() {
      System.out.println("How many users do you want to add?");
      int amountOfAddedUsers = bScan.nextInt();
      int size = PatientData.getPatientData().size(); // prevents multiple calls being made when more than 1 user is added
      for (int i = 1; i <= amountOfAddedUsers; i++) {
         System.out.println();
         System.out.format("New User [%d]:\n", size / 2 + i + 1);
         System.out.println("What do you want the first name of the user to be?");
         String firstname = bScan.nextLine();
         System.out.println("what do you want the surname of the user to be?");
         String surname = bScan.nextLine();
         UserData.addUser(new User(size + i, firstname + " " +  surname));
      }
      System.out.println(UserData.getUserData());
   }

   private static void addPatient() {
   System.out.println("How many patients do you want to add?");
   int amountOfAddedPatients = bScan.nextInt();
   int size = PatientData.getPatientData().size(); //prevents multiple calls being made when more than 1 patient is added
   for (int i = 1; i <= amountOfAddedPatients; i++) {
      System.out.println();
      System.out.format("New Patient [%d]:\n", size + i);
      System.out.println("What do you want the first name to be?");
      String firstname = bScan.nextLine();
      System.out.println("What do you want the surname of the patient to be?");
      String surname = bScan.nextLine();
      System.out.println("What do you want the birthdate of the patient to be? [YYYY-MM-DD]");
      LocalDate born = bScan.nextDate(bScan.nextLine());
      System.out.println("What do you want the weight of the new patient to be? (kg)");
      double weight = bScan.nextDouble();
      System.out.println("What do you want the length of the new patient to be? (cm)");
      double length = bScan.nextDouble();
      PatientData.addPatient(new Patient(size + i, surname, firstname, born, HandyMethods.calcAge(born), weight, length));
   }
   }

   private static void deletePatients() {

      System.out.println("Type the corresponding number(s) of the data you would like to change; if there are multiple numbers, divide them with a comma ex. 1,2,3:\n");

      System.out.println("0: Return");
      int x = 1;
      for (Patient patient : PatientData.getPatientData()) {
         System.out.printf("%d: %s \n", x, patient.fullName());
         x++;
      }
      System.out.format("%d: All patients\n\n", PatientData.getAmountOfPatients() + 1);
      System.out.print("Enter #choice:");

      String str = HandyMethods.validPhrase(bScan.nextLine());
      if (!Objects.equals(str, "0")) {
         int[] usersToBeDeleted = HandyMethods.stringToNumbers(str, 0, PatientData.getAmountOfPatients());

         for( int i = 0; i < usersToBeDeleted.length/2; ++i ) {
            int temp = usersToBeDeleted[i];
            usersToBeDeleted[i] = usersToBeDeleted[usersToBeDeleted.length - i - 1];
            usersToBeDeleted[usersToBeDeleted.length - i - 1] = temp;
         }

         for (int k : usersToBeDeleted) {
            PatientData.deletePatient(k);
         }
      }
   }

   static void menu() {
      boolean nextCycle = true;
      while (nextCycle) {
         System.out.format("Current user: [%d] %s%n", currentUser.getUserID(), currentUser.getUserName());
         System.out.format( "%s\n", "-".repeat( 35 ) );
         System.out.format("Current patient: %s\n", currentPatient.fullName());

         //the choices the user has
         PrintToScreen.printStart();

         int choice = bScan.nextInt();
         switch (choice) {
            case STOP -> nextCycle = false; // interrupt the loop

            case VIEW -> currentPatient.viewData();

            case SWAP_USER -> swapUser();

            case SWAP_PATIENT -> swapPatient();

            case CHANGE_CURRENT_PATIENT_INFORMATION -> changePatientData();

            case ADD_USER -> addUser();

            case ADD_PATIENT -> addPatient();

            case REMOVE_PATIENTS -> deletePatients();

            default -> System.out.println("you did an oopsie :3");
         }
      }
   }
}
