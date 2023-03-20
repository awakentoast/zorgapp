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

   //choices for the menu switch
   static final int STOP = 0;
   static final int VIEW = 1;
   static final int SWAP_USER = 2;
   static final int SWAP_PATIENT = 3;
   static final int CHANGE_CURRENT_PATIENT_INFORMATION = 4;
   static final int ADD_USER = 5;
   static final int ADD_PATIENT = 6;
   static final int DELETE_PATIENTS = 7;
   static final int ADD_BILL = 8;
   static final int DISPLAY_BILLING_HISTORY = 9;
   static final int ADD_MEDICATION = 10;
   static final int CHANGE_MEDICATION = 11;
   static final int DELETE_MEDICATION = 12;

   //choices for the patient data, change the max number in change patient and add patient when adding a new field that the user inputs
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


   public Administration() {
      allMedications.addMedication(new Medication("paracetamol", "pijnstiller", "maximaal 4000 mg per dag"));
      allMedications.addMedication(new Medication("aspirine", "pijnstiller", "maximaal 4000 mg per dag"));
      allMedications.addMedication(new Medication("xanax", "angstverlichting", "not with alcohol"));
      allMedications.addMedication(new Medication("xtc", "tabs", "1 tab a month"));
      allMedications.addMedication(new Medication("lsd", "tabs", "1 tab a month"));

      allUsers.addUser(new GeneralPractitioner(1, "El Chapo"));
      allUsers.addUser(new Dentist(2, "Barry Batsbak"));
      allUsers.addUser(new PhysicalTherapist(3, "Kenzo Tenma"));

      MedicationData medicationPatient1 = new MedicationData();
      BillingData billingsPatient1 = new BillingData();
      allPatients.addPatient(new Patient(1, "Duck", "Donald", LocalDate.of(1934, 6, 9),30, 130, medicationPatient1, billingsPatient1));
      MedicationData medicationPatient2 = new MedicationData();
      BillingData billingsPatient2 = new BillingData();
      allPatients.addPatient(new Patient(2, "Baldy", "Caped", LocalDate.of(1997, 6, 9),75, 175, medicationPatient2, billingsPatient2));
      MedicationData medicationPatient3 = new MedicationData();
      BillingData billingsPatient3 = new BillingData();
      allPatients.addPatient(new Patient(3, "Henkema", "Henk", LocalDate.of(1999, 9, 9),50, 160, medicationPatient3, billingsPatient3));
      MedicationData medicationPatient4 = new MedicationData();
      BillingData billingsPatient4 = new BillingData();
      allPatients.addPatient(new Patient(4, "White", "Walter", LocalDate.of(1958, 9, 7),98, 191, medicationPatient4,billingsPatient4));
      MedicationData medicationPatient5 = new MedicationData();
      BillingData billingsPatient5 = new BillingData();
      allPatients.addPatient(new Patient(5, "Hat", "Straw", LocalDate.of(2004, 5, 5),64, 174, medicationPatient5, billingsPatient5));

      currentPatient = allPatients.getPatient(1);
      currentUser = allUsers.getUser(1);
   }



   public static boolean userTypesYesOrNo(String yesOrNo) {
      return (Objects.equals(yesOrNo.toLowerCase(), "yes"));
   }

   private void swapUser() {
      if (allUsers.getAmountOfUsers() == 0) {
         System.out.println("There are no users, first add more users\n");
      }
      else if (allUsers.getAmountOfUsers() == 1) {
            System.out.println("You are the only registered user in the system, please add more users if you would like to switch user\n");
         }
      else {
         System.out.println("\nWhat user do you want to switch to?");

         System.out.println("\n0: Return");

         allUsers.printUsers();

         System.out.print("\nEnter #choice: ");

         int userToSwitchTo = bScan.nextInt(1, allUsers.getAmountOfUsers());
         if (userToSwitchTo != 0) {
            currentUser = allUsers.getUser(userToSwitchTo);
         }
         System.out.println();
      }
   }


   private void swapPatient() {
      if (allPatients.getAmountOfPatients() == 0) {
         System.out.println("There are no patients, first add more patients\n");
      }
      else if (allUsers.getAmountOfUsers() == 1) {
         System.out.println("The current patient is the only registered patient, first add more patients if you would like to switch to a different patient\n");
      }
      else {
         System.out.println("\nWhat patient do you want to switch to?\n");

         System.out.println("0: Return");

         allPatients.printPatients();

         System.out.print("\nEnter choice: ");

         int patientToSwitchTo = bScan.nextInt(1, allPatients.getAmountOfPatients());
         currentPatient = allPatients.getPatient(patientToSwitchTo);

         System.out.println();
      }
   }


   private void addUser() {
      System.out.println("How many users do you want to add?");

      int amountOfAddedUsers = bScan.nextInt(1, 1000000000);

      // prevents multiple calls being made when more than 1 user is added
      int size = allPatients.getAmountOfPatients();
      System.out.format("\nAre you sure you want to add %d amount of users\n", amountOfAddedUsers);
      System.out.println("yes/no");
      if (!userTypesYesOrNo(bScan.nextLine()) || amountOfAddedUsers == 0) {
         System.out.println("You have chosen not to add any users\n");
      } else {
         for (int i = 1; i <= amountOfAddedUsers; i++) {
            System.out.format("\nNew User [%d]:\n", size / 2 + i + 1);

            System.out.println("What do you want the first name of the user to be?");
            String firstname = bScan.nextLine();

            System.out.println("what do you want the surname of the user to be?");

            String surname = bScan.nextLine();
            System.out.println("What occupation does the user have, choose 1 from below:\n");

            int occupationCount = 0;
            for (String occupation : allUsers.getAllOccupations()) {
               occupationCount++;
               System.out.format("%d: %s\n", occupationCount, occupation);
            }

            int occupationIndex = bScan.nextInt(1, allUsers.getAmountOfOccupations());

            switch (occupationIndex) {
               case 1 -> allUsers.addUser(new GeneralPractitioner(size / 2 + i + 1, firstname + " " + surname));
               case 2 -> allUsers.addUser(new Dentist(size / 2 + i + 1, firstname + " " + surname));
               case 3 -> allUsers.addUser(new PhysicalTherapist(size / 2 + i + 1, firstname + " " + surname));
               default -> System.out.format("You did an oopsie in adduser switch case, number [%d] :3\n", occupationIndex);
            }
         }

         System.out.println("\nDo you want the newly added user to be the current user?");
         System.out.println("yes/no");

         if (!userTypesYesOrNo(bScan.nextLine())) {
            System.out.println("You have chosen not to change the current user\n");
         } else {
            currentUser = allUsers.getUser(allUsers.getAmountOfUsers());
         }
      }
   }


   private void changePatientData() {

      //prints the choices for the user
      PrintToScreen.printPatientParameters();

      //prevents for anything but integers and 1,2 integers being passed along
      int[] fieldsToBeChanged = bScan.nextInts(1, LENGTH);


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
               double weight;
               do {
                  System.out.println("What do you want the weight of the new patient to be? (kg)");
                  weight = bScan.nextDouble(1, 636,  "The weight must be between 1 kg and 636 kg");
               } while (weight >= 1 && weight <= 636);
               currentPatient.setWeight(weight);
            }

            case LENGTH -> {
               double length;
               do {
                  System.out.println("What do you want the length of the new patient to be? (cm)");
                  length = bScan.nextDouble(1, 273,"The length must be between 1 cm and 273 cm");
               } while (length >= 1 && length <= 273);
               currentPatient.setLength(length);
            }
            
            default -> System.out.format("you made an oopsie :3 [%d]\n", fieldToBeChanged);
         }
      }
      System.out.println();
   }


   private void addPatient() {
      System.out.println("How many patients do you want to add?");
      int amountOfAddedPatients = bScan.nextInt(0, 100000000);
      int size = allPatients.getAmountOfPatients();

      System.out.format("\nAre you sure you want to add %d amount of users\n", amountOfAddedPatients);
      System.out.println("yes/no");
      if (userTypesYesOrNo(bScan.nextLine()) || amountOfAddedPatients == 0) {
         System.out.println("You have chosen not to add any patients\n");
      } else {
         for (int i = 1; i <= amountOfAddedPatients; i++) {
            System.out.format("\nNew Patient [%d]:\n", size + i);

            System.out.println("What do you want the first name to be?");
            String firstname = bScan.nextLine();

            System.out.println("What do you want the surname of the patient to be?");
            String surname = bScan.nextLine();

            System.out.println("What do you want the birthdate of the patient to be? [YYYY-MM-DD]");
            LocalDate born = bScan.nextDate(bScan.nextLine());

            double weight;
            do {
               System.out.println("What do you want the weight of the new patient to be? (kg)");
               weight = bScan.nextDouble(1, 636, "The weight must be between 1 kg and 636 kg, new weight:");
            } while (weight >= 1 && weight <= 636);

            double length;
            do {
               System.out.println("What do you want the length of the new patient to be? (cm)");
               length = bScan.nextDouble(1, 273,  "The length must be between 1 cm and 273 cm, new length:");
            } while (length >= 1 && length <= 273);

            MedicationData medicationList = new MedicationData();
            BillingData billings = new BillingData();

            // adding the patient here so because the medicationList is an object that can always be altered
            allPatients.addPatient(new Patient(size + i, surname, firstname, born, weight, length, medicationList, billings));
            if (!currentUser.getMedicationEditingAuthorization()) {
               System.out.println("You are not authorized to add any medication");
            } else {
               System.out.println("Does the patient use any medication?");
               System.out.println("yes/no");
               currentPatient = allPatients.getPatient(allPatients.getAmountOfPatients());
               if (userTypesYesOrNo(bScan.nextLine())) {
                  addMedication();
               }
               else{
                  System.out.println("The patient doesn't use any medication");
               }
            }
         }

         System.out.println("Do you want the newly added patient to be the current patient?");
         System.out.println("yes/no");

         if (!userTypesYesOrNo(bScan.nextLine())) {
            System.out.println("You have chosen not to change the current patient\n");
         } else {
            currentPatient = allPatients.getPatient(allPatients.getAmountOfPatients());
         }
      }
   }


   private void deletePatients() {

      System.out.println("Type the corresponding number(s) of the patients you would like to delete; if there are multiple , divide them with a comma ex. 1,2,3:\n");

      System.out.println("0: Return");

      allPatients.printPatients();

      System.out.format("%d: All patients\n", allPatients.getAmountOfPatients() + 1);
      System.out.print("\nEnter #choice:");

      int[] usersToBeDeleted = bScan.nextInts(1, allPatients.getAmountOfPatients());

      // 0 is return, so it circumvents the deletion
      if (usersToBeDeleted[0] == 0) {
         System.out.println("You have chosen not to delete any patients");
      } else {
         //makes sure the user doesn't haphazardly delete patients
         System.out.println("\nAre you sure if you want to delete the patient(s)?");
         System.out.println("yes/no");

         if (!userTypesYesOrNo(bScan.nextLine())) {
            System.out.println("you didn't type yes so patients won't be deleted");
         } else {
            //reverses the numbers, so we don't get index errors when deleting multiple patients at once
            for (int j = 0; j < usersToBeDeleted.length / 2; j++) {
               int temp = usersToBeDeleted[j];
               usersToBeDeleted[j] = usersToBeDeleted[usersToBeDeleted.length - j - 1];
               usersToBeDeleted[usersToBeDeleted.length - j - 1] = temp;
            }
            for (int k : usersToBeDeleted) {
               allPatients.deletePatient(k);
            }
         }
      }

      if (allPatients.getAmountOfPatients() == 0) {
         System.out.println("You have deleted all the patients, first add a patient to proceed\n");
         addPatient();
      }
   }


   private void addMedication() {
      if (!currentUser.getMedicationEditingAuthorization()) {
         System.out.println("You are not authorized to add medication\n");
      } else {
         System.out.println("What medication would you like to add? Type the corresponding number(s) of the medication you would like to change; if there are multiple numbers, divide them with a comma ex. 1,2,3 (you cant choose 0 and \"all of them\" with other digits):\n");

         System.out.println("0: Add nothing and return");
         int i = 0;
         for (Medication medication : allMedications.getAllMedicationData()) {
            i++;
            System.out.format("%d: %s (%s)\n", i, medication.getSubstance(), medication.getType());
         }
         System.out.format("%d: Add all of the medication\n", allMedications.getAmountOfMedication() + 1);

         System.out.print("\nEnter Choice: ");
         int[] medicationsToBeAdded = bScan.nextInts(1,allMedications.getAmountOfMedication());

         System.out.println();

         if (medicationsToBeAdded[0] != 0) {
            for (int medicationToBeAdded : medicationsToBeAdded) {
               System.out.format("What dosage would you like to prescribe for %s?\n", allMedications.getMedication(medicationToBeAdded).getSubstance());
               String medicationPrescription = bScan.nextLine();
               Medication medication = allMedications.getMedication(medicationToBeAdded);
               medication.setDose(medicationPrescription);
               currentPatient.addMedication(medication);
               System.out.println();
            }
         }
      }
   }


   private void changeMedicationDosage() {
      if (!currentUser.getMedicationEditingAuthorization()) {
         System.out.println("You are not authorized to change or edit medication");
      }
      else {
         if (currentPatient.getMedicationList().getAmountOfMedication() == 0) {
            System.out.println("\nThe patient doesn't currently have any medication administered to them, first administer medication to them\n");
         }
         else if (currentPatient.getMedicationList().getAmountOfMedication() == 1) {
            System.out.format("\nWhat do you want the new dosage to be of %s?\n", currentPatient.getMedicationList().getMedication(1).getSubstance());
            String newDosage = bScan.nextLine();
            currentPatient.getMedicationList().changeMedicationDosage(1, newDosage);
         }
         else {
            System.out.println("Of which medication would you like to alter the dose?\n");

            System.out.println("0: Don't change anything\n");

            currentPatient.getMedicationList().printMedications();

            System.out.format("%d: Change the dosage of all of them\n", currentPatient.getMedicationList().getAmountOfMedication() + 1);

            System.out.print("\nEnter choice: ");

            int[] medicationDosagesToBeAltered = bScan.nextInts(1, currentPatient.getMedicationList().getAmountOfMedication());

            if (medicationDosagesToBeAltered[0] != 0) {
               for (int j : medicationDosagesToBeAltered) {
                  System.out.format("\nWhat do you want the new dosage to be of %s?\n", currentPatient.getMedicationList().getMedication(j).getSubstance());
                  String newDosage = bScan.nextLine();
                  currentPatient.getMedicationList().changeMedicationDosage(j, newDosage);
               }
            }
         }
      }
   }


   private void deleteMedication() {
      if (currentPatient.getMedicationList().getAmountOfMedication() == 0) {
         System.out.println("\nThere is no medication to delete, first prescribe medication\n");
      } else if (currentPatient.getMedicationList().getAmountOfMedication() == 1) {
         System.out.format("\nWould you like to delete %s?\n", currentPatient.getMedicationList().getMedication(1).getSubstance());
         System.out.println("yes/no");
         if (userTypesYesOrNo(bScan.nextLine())) {
            currentPatient.getMedicationList().deleteMedication(1);
         }
         else{
            System.out.println("You have chosen not to delete the medication");
         }
      }
      else {
         System.out.println("What medication would you like to delete? Type the corresponding number(s) of the medication you would like to change; if there are multiple numbers, divide them with a comma ex. 1,2,3 (you cant choose 0 and \"all of them\" with other digits):\n");

         System.out.println("0: Delete nothing and return");

         currentPatient.getMedicationList().printMedications();

         System.out.format("%d: Delete all of the medication\n", currentPatient.getMedicationList().getAmountOfMedication() + 1);

         System.out.print("\nEnter Choice: ");


         int [] medicationsToBeDeleted = bScan.nextInts(1, currentPatient.getMedicationList().getAmountOfMedication());

         if (medicationsToBeDeleted[0] != 0) {
            System.out.println("Are you sure if you want to delete the medication(s)?");
            System.out.println("yes/no");

            if (!userTypesYesOrNo(bScan.nextLine())) {
               System.out.println("you didn't type yes so the medication(s) won't be deleted");
            } else {
               //reverses the numbers, so we don't get index errors when deleting multiple medications at once
               for (int j = 0; j < medicationsToBeDeleted.length / 2; j++) {
                  int temp = medicationsToBeDeleted[j];
                  medicationsToBeDeleted[j] = medicationsToBeDeleted[medicationsToBeDeleted.length - j - 1];
                  medicationsToBeDeleted[medicationsToBeDeleted.length - j - 1] = temp;
               }
               for (int k : medicationsToBeDeleted) {
                  currentPatient.getMedicationList().deleteMedication(k);
               }
            }
         }
      }
   }


   private void addBillToPatient() {
      System.out.println("\nWhich procedure(s) would you like to bill the patient Type the corresponding number(s) of the procedure you would like to bill; if there are multiple numbers, divide them with a comma ex. 1,2,3 (you cant choose 0 and \\\"all of them\\\" with other digits)?\n");

      System.out.println("0: Delete nothing and return");
      currentUser.getListOfBillings().printAllBillsWithIndex();
      System.out.format("%d: Bill all procedures\n", currentUser.getListOfBillings().getAmountOfBills() + 1);

      System.out.print("\nEnter choice: ");
      int[] billsToBeAdded =  bScan.nextInts(1, currentUser.getListOfBillings().getAmountOfBills());

      System.out.println("Are you sure you want to bill these bills to the patient");
      System.out.println("yes/no");

      String yesOrNo = bScan.nextLine();
      if (billsToBeAdded[0] != 0 && userTypesYesOrNo(yesOrNo)) {
         for (int billToBeAdded : billsToBeAdded) {
            currentPatient.addBill(currentUser.getListOfBillings().getBill(billToBeAdded));
         }
      }
      if (!userTypesYesOrNo(yesOrNo)) {
         System.out.println("You have chosen not to bill the patient");
      }
      System.out.println();
   }


   private void displayBillingHistory() {
      if (currentPatient.getBillings().getAmountOfBills() == 0) {
         System.out.println("The patient has no billing history");
      }
      else {
         System.out.println("\nBilling history:");
         currentPatient.getBillings().printBillingHistory(currentUser.getOccupation());
         System.out.printf("Total: € %.2f\n\n", currentPatient.getBillings().getTotalCostOfBillings());
      }
   }


   void menu() {
      boolean nextCycle = true;
      while (nextCycle) {
         System.out.format("%s\n\n", "-".repeat(60));
         System.out.format("Current user: [%d] %s (%s)\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getOccupation());
         System.out.format("Current patient: %s\n", currentPatient.fullName());

         //the choices the user has. If the user can edit medication, those options are shown, else not
         PrintToScreen.printStart(currentUser.getMedicationEditingAuthorization());

         int choice = bScan.nextInt(1, DELETE_MEDICATION);
         switch (choice) {
            case STOP -> nextCycle = false; // interrupts the loop

            case VIEW -> currentPatient.viewData(currentUser);

            case SWAP_USER -> swapUser();

            case SWAP_PATIENT -> swapPatient();

            case CHANGE_CURRENT_PATIENT_INFORMATION -> changePatientData();

            case ADD_USER -> addUser();

            case ADD_PATIENT -> addPatient();

            case DELETE_PATIENTS -> deletePatients();

            case ADD_MEDICATION -> addMedication();

            case CHANGE_MEDICATION -> changeMedicationDosage();

            case DELETE_MEDICATION -> deleteMedication();

            case ADD_BILL -> addBillToPatient();

            case DISPLAY_BILLING_HISTORY -> displayBillingHistory();

            default -> System.out.format("you did an oopsie :3 [%d]\n", choice);
         }
      }
   }
}
