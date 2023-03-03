import java.time.LocalDate;
import java.util.Arrays;
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

   //choices for the menu switch
   static final int STOP = 0;
   static final int VIEW = 1;
   static final int SWAP_USER = 2;
   static final int SWAP_PATIENT = 3;
   static final int CHANGE_CURRENT_PATIENT_INFORMATION = 4;
   static final int ADD_USER = 5;
   static final int ADD_PATIENT = 6;
   static final int REMOVE_PATIENTS = 7;
   static final int ADD_MEDICATION = 8;
   static final int CHANGE_MEDICATION = 9;

   //choices for the patient data
   static final int RETURN = 0;
   static final int FIRSTNAME = 1;
   static final int SURNAME = 2;
   static final int DATEOFBIRTH = 3;
   static final int WEIGHT = 4;
   static final int LENGTH = 5;


   private Patient currentPatient;
   private User currentUser;
   private final PatientData allPatients = new PatientData();
   private final UserData allUsers = new UserData();
   private final MedicationData allMedications = new MedicationData();


   private final BScanner bScan = new BScanner();



   public Administration()
   {
      allMedications.addMedication(new Medication("meth", "focus enhancer", "one shard a day"));
      allMedications.addMedication(new Medication("lean", "a short lived euphoria", "a glass a day"));
      allMedications.addMedication(new Medication("xanax", "black out", "not with alcohol"));
      allMedications.addMedication(new Medication("xtc", "tabs", "1 tab a month"));
      allMedications.addMedication(new Medication("lsd", "tabs", "1 tab a month"));

      allUsers.addUser(new User(1, "El Chapo", allUsers.getOccupation(1), allUsers.getAllowedMedicationEditing(1), allUsers.getAllowedMedicationInsight(1) ));
      allUsers.addUser(new User(2, "Barry Batsbak", allUsers.getOccupation(2), allUsers.getAllowedMedicationEditing(1), allUsers.getAllowedMedicationInsight(2)));
      allUsers.addUser(new User(3, "Kenzo Tenma", allUsers.getOccupation(3), allUsers.getAllowedMedicationEditing(3), allUsers.getAllowedMedicationInsight(3)));

      MedicationData medicationPatient1 = new MedicationData();
      allPatients.addPatient(new Patient(1, "Duck", "Donald", LocalDate.of(1934, 6, 9), HandyMethods.calcAge(LocalDate.of(1934, 6, 9)), 30, 130, medicationPatient1));
      MedicationData medicationPatient2 = new MedicationData();
      allPatients.addPatient(new Patient(2, "Baldy", "Caped", LocalDate.of(1997, 6, 9), HandyMethods.calcAge(LocalDate.of(1997, 6, 9)), 75, 175, medicationPatient2));
      MedicationData medicationPatient3 = new MedicationData();
      allPatients.addPatient(new Patient(3, "Henkema", "Henk", LocalDate.of(1999, 9, 9), HandyMethods.calcAge(LocalDate.of(1999, 9, 9)), 50, 160, medicationPatient3));
      MedicationData medicationPatient4 = new MedicationData();
      allPatients.addPatient(new Patient(4, "White", "Walter", LocalDate.of(1958, 9, 7), HandyMethods.calcAge(LocalDate.of(1958, 9, 7)), 98, 191, medicationPatient4));
      MedicationData medicationPatient5 = new MedicationData();
      allPatients.addPatient(new Patient(5, "Hat", "Straw", LocalDate.of(2004, 5, 5), HandyMethods.calcAge(LocalDate.of(2004, 5, 5)), 64, 174, medicationPatient5));

      currentPatient = allPatients.getPatientData().get(0);
      currentUser = allUsers.getUserData().get(0);
   }

   private void swapUser() {
      if (allUsers.getAmountOfUsers() != 0) {

         System.out.println();
         System.out.println("What user do you want to switch to?");
         System.out.println("0: Return");

         int i = 1;
         for (User user : allUsers.getUserData()) {
            System.out.format("%d: %s %s\n", i, user.getUserName(), user.getOccupation());
            i++;
         }
         System.out.println();
         System.out.print("Enter #choice: ");

         int userToSwitchTo = HandyMethods.correctInput(1, allUsers.getAmountOfUsers(), bScan.nextInt());
         if (userToSwitchTo != 0) {
            currentUser = allUsers.getUserData().get(userToSwitchTo - 1);
         }

         System.out.println();
      } else {
         System.out.println("There are no users, first add more users");
      }
   }

   private void swapPatient() {
      if (allPatients.getAmountOfPatients() != 0) {
         System.out.println();
         System.out.println("What patient do you want to switch to?\n");

         System.out.println("0: Return");
         int i = 1;
         for (Patient patient : allPatients.getPatientData()) {
            System.out.printf("%d: %s \n", i, patient.fullName());
            i++;
         }
         System.out.println();
         System.out.print("Enter choice: ");

         int patientToSwitchTo = HandyMethods.correctInput(1, allPatients.getAmountOfPatients(), bScan.nextInt());

         if (patientToSwitchTo != 0) {
            currentPatient = allPatients.getPatientData().get(patientToSwitchTo - 1);
         }
         System.out.println();
      } else {
         System.out.println("There are no patients, first add more patients");
      }
   }

   private void changePatientData() {

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
               double weight = 0;
               boolean validWeight = true;
               String askForWeight = "What do you want the new weight of the patient to be? (kg)";
               while (validWeight) {
                  System.out.println(askForWeight);
                  weight = bScan.nextDouble();
                  if (weight > 0) {
                     validWeight = false;
                     askForWeight += " weight can't be 0 or less!";
                  }
               } currentPatient.setWeight(weight);
            }

            case LENGTH -> {
               double length = 0;
               boolean validLength = true;
               String askForLength = "What do you want the length of the new patient to be? (cm)";
               while (validLength) {
                  System.out.println(askForLength);
                  length = bScan.nextDouble();
                  if (length > 0) {
                     validLength = false;
                     askForLength += " length can't be 0 or less!";
                  }
               } currentPatient.setLength(length);
            }

            default -> {
               System.out.println(fieldToBeChanged);
               System.out.println("you made an oopsie :3");
            }
         }
      }
   }

   private void addUser() {
      System.out.println("How many users do you want to add?");
      int amountOfAddedUsers = bScan.nextInt();
      amountOfAddedUsers = HandyMethods.correctInput(0, 1000000000, amountOfAddedUsers);
      int size = allPatients.getPatientData().size();// prevents multiple calls being made when more than 1 user is added
      System.out.format("You sure you want to add %d amount of users\n", amountOfAddedUsers);
      String yesOrNo = bScan.nextLine();
      if (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES")) {
         if (amountOfAddedUsers != 0) {
            for (int i = 1; i <= amountOfAddedUsers; i++) {
               System.out.println();
               System.out.format("New User [%d]:\n", size / 2 + i + 1);
               System.out.println("What do you want the first name of the user to be?");
               String firstname = bScan.nextLine();
               System.out.println("what do you want the surname of the user to be?");
               String surname = bScan.nextLine();
               System.out.println("What occupation does the user have, choose 1 from below:\n");
               int occupationCount = 0;
               for (String occupation : allUsers.getOccupations()) {
                  occupationCount++;
                  System.out.format("%d: %s", occupationCount, occupation);
               }


               int occupationIndex = HandyMethods.correctInput(1, allUsers.getAmountOfOccupations(), bScan.nextInt()) + 1;


               allUsers.addUser(new User(size / 2 + i + 1, firstname + " " + surname, allUsers.getOccupation(occupationIndex), allUsers.getAllowedMedicationEditing(occupationIndex), allUsers.getAllowedMedicationInsight(occupationIndex)));
            }

            System.out.println();
            System.out.println("Do you want the newly added user to be the current user?");
            System.out.println("yes/no");
            yesOrNo = bScan.nextLine();
            System.out.println();

            if (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES")) {
               currentUser = allUsers.getUserData().get(allUsers.getAmountOfUsers() - 1);
            } else {
               System.out.println("You have chosen not to change the current user\n");
            }
         } else {
            System.out.println("you have chosen not to add any users\n");
         }
      } else {
         System.out.println("You have choses not to add any users\n");
      }
   }

   private void addPatient() {
      System.out.println("How many patients do you want to add?");
      int amountOfAddedPatients = bScan.nextInt();
      amountOfAddedPatients = HandyMethods.correctInput(0, 100000000, amountOfAddedPatients);

      //prevents multiple calls being made when more than 1 patient is added
      int size = allPatients.getPatientData().size();

      System.out.format("You sure you want to add %d amount of users\n", amountOfAddedPatients);
      String yesOrNo = bScan.nextLine();
      if (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES")) {

         if (amountOfAddedPatients != 0) {
            for (int i = 1; i <= amountOfAddedPatients; i++) {
               System.out.println();
               System.out.format("New Patient [%d]:\n", size + i);

               System.out.println("What do you want the first name to be?");
               String firstname = bScan.nextLine();

               System.out.println("What do you want the surname of the patient to be?");
               String surname = bScan.nextLine();

               System.out.println("What do you want the birthdate of the patient to be? [YYYY-MM-DD]");
               LocalDate born = bScan.nextDate(bScan.nextLine());

               double weight = 0;
               boolean validWeight = true;
               String askForWeight = "What do you want the weight of the new patient to be? (kg)";
               while (validWeight) {
                  System.out.println(askForWeight);
                  weight = bScan.nextDouble();
                  if (weight > 0) {
                     validWeight = false;
                     askForWeight += " weight can't be 0 or less!";
                  }
               }

               double length = 0;
               boolean validLength = true;
               String askForLength = "What do you want the length of the new patient to be? (cm)";
               while (validLength) {
                  System.out.println(askForLength);
                  length = bScan.nextDouble();
                  if (length > 0) {
                     validLength = false;
                     askForLength += " length can't be 0 or less!";
                  }
               }

               System.out.println("Does the patient use any medication?");
               System.out.println("yes/no");
               yesOrNo = bScan.nextLine();
               MedicationData medicationList = new MedicationData();
               System.out.println();

               if (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES")) {
                  System.out.println("What medication would you like to add? Type the corresponding number(s) of the medication you would like to change; if there are multiple numbers, divide them with a comma ex. 4,5,6 (you cant choose 0 and \"all of them\" with other digits):\n");

                  System.out.println("0: return and add nothing");
                  int j = 0;
                  for (Medication medication : allMedications.getAllMedicationData()) {
                     j++;
                     System.out.format("%d: %s\n", j, medication.getSubstance());
                  }
                  System.out.println("6: add all medications\n");
                  System.out.println("Enter choice:");

                  String str = HandyMethods.validPhrase(bScan.nextLine());

                  int[] medicationsToBeAdded = HandyMethods.stringToNumbers(str, 0, allMedications.getAmountOfMedication());

                  System.out.println();

                  for (int medicationToBeAdded : medicationsToBeAdded) {
                     if (medicationToBeAdded != 0) {
                        medicationList.addMedication(allMedications.getMedication(medicationToBeAdded - 1));
                     }
                  }
                  allPatients.addPatient(new Patient(size + i, surname, firstname, born, HandyMethods.calcAge(born), weight, length, medicationList));
               }
            }
            System.out.println("Do you want the newly added patient to be the current patient?");
            System.out.println("yes/no");
            yesOrNo = bScan.nextLine();
            if (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES")) {
               currentPatient = allPatients.getPatient(allPatients.getAmountOfPatients() - 1);
            } else {
               System.out.println("You have chosen not to change the current patient\n");
            }
         } else {
            System.out.println("You have chosen to not add any patients\n");
         }
      }else {
         System.out.println("You have chosen not to add any patients\n");
      }
   }

   private void deletePatients() {

      System.out.println("Type the corresponding number(s) of the data you would like to change; if there are multiple numbers, divide them with a comma ex. 1,2,3:\n");

      System.out.println("0: Return");
      int x = 1;
      for (Patient patient : allPatients.getPatientData()) {
         System.out.printf("%d: %s \n", x, patient.fullName());
         x++;
      }
      System.out.format("%d: All patients\n\n", allPatients.getAmountOfPatients() + 1);
      System.out.print("Enter #choice:");

      String str = HandyMethods.validPhrase(bScan.nextLine());

      //make sure the user doesn't haphazardly delete patients
      System.out.println("Are you sure if you want to delete patients? yes/no");
      String yesOrNo = bScan.nextLine();
      if (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES")) {
         // 0 is return, so it passes the deletion
         if (!Objects.equals(str, "0")) {
            int[] usersToBeDeleted = HandyMethods.stringToNumbers(str, 0, allPatients.getAmountOfPatients());

            for (int i = 0; i < usersToBeDeleted.length / 2; ++i) {
               int temp = usersToBeDeleted[i];
               usersToBeDeleted[i] = usersToBeDeleted[usersToBeDeleted.length - i - 1];
               usersToBeDeleted[usersToBeDeleted.length - i - 1] = temp;
            }

            for (int k : usersToBeDeleted) {
               allPatients.deletePatient(k);
            }
         }
      } else {
         System.out.println("you didn't type yes so patients won't be deleted");
      }
   }

   private void addMedication() {
      if (!currentUser.getMedicationEditingAuthorization()) {
         System.out.println("You are not authorized to add medication\n");
      } else {
         System.out.println("What medication would you like to add? Type the corresponding number(s) of the medication you would like to change; if there are multiple numbers, divide them with a comma ex. 4,5,6 (you cant choose 0 and \"all of them\" with other digits):\n");

         System.out.println("0: Add nothing and return");
         int i = 0;
         for (Medication medication : allMedications.getAllMedicationData()) {
            i++;
            System.out.format("%d: %s\n", i, medication.getSubstance());
         }
         System.out.println("6: Add all of the medication\n");
         System.out.print("Enter Choice: ");

         String str = HandyMethods.validPhrase(bScan.nextLine());

         int[] medicationsToBeAdded = HandyMethods.stringToNumbers(str, 0, allMedications.getAmountOfMedication());

         System.out.println();

         for (int medicationToBeAdded : medicationsToBeAdded) {
            if (medicationToBeAdded != 0) {
               currentPatient.addMedication(allMedications.getMedication(medicationToBeAdded - 1));
            }
         }
      }
   }

   private void changeMedicationDosage() {
      if (!currentUser.getMedicationEditingAuthorization()){
         System.out.println("You are not authorized to change or edit medication");
      }
      else {
         if (currentPatient.getMedicationList().getAmountOfMedication() != 0) {
            System.out.println("Of which medication would you like to alter the dose?\n");

            System.out.println("0: Don't change anything");
            int i = 0;
            for (Medication medication : currentPatient.getMedicationList().getAllMedicationData()) {
               i++;
               System.out.format("%d: Substance: %s, type: %s, dosage: %s\n", i, medication.getSubstance(), medication.getType(), medication.getDose());
            }

            System.out.print("\nEnter choice: ");
            System.out.println();

            int medicationToBeChanged = bScan.nextInt();
            medicationToBeChanged = HandyMethods.correctInput(0, currentPatient.getMedicationList().getAmountOfMedication(), medicationToBeChanged);

            System.out.println("\nWhat do you want the new dosage to be?");
            String newDosage = bScan.nextLine();
            currentPatient.getMedicationList().changeMedicationDosage(medicationToBeChanged, newDosage);
         } else {
            System.out.println("\nThe patient doesn't currently have any medication administered to them, first administer medication to them\n");
         }
      }
   }



   void menu() {
      boolean nextCycle = true;
      while (nextCycle) {
         System.out.format("Current user: [%d] %s %s\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getOccupation());
         System.out.format("%s\n", "-".repeat(35));
         System.out.format("Current patient: %s\n", currentPatient.fullName());

         //the choices the user has
         PrintToScreen.printStart(currentUser.getMedicationEditingAuthorization());

         int choice = bScan.nextInt();
         switch (choice) {
            case STOP -> nextCycle = false; // interrupt the loop

            case VIEW -> currentPatient.viewData(currentUser.getMedicationInsightAuthorization());

            case SWAP_USER -> swapUser();

            case SWAP_PATIENT -> swapPatient();

            case CHANGE_CURRENT_PATIENT_INFORMATION -> changePatientData();

            case ADD_USER -> addUser();

            case ADD_PATIENT -> addPatient();

            case REMOVE_PATIENTS -> deletePatients();

            case ADD_MEDICATION -> addMedication();

            case CHANGE_MEDICATION -> changeMedicationDosage();

            default -> System.out.println("you did an oopsie :3");
         }
      }
   }
}
