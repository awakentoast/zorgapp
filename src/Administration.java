import java.time.LocalDate;
import java.util.*;

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
   static final int STOP = 1;
   static final int VIEW = 2;
   static final int CHANGE_CURRENT_PATIENT_INFORMATION = 3;
   static final int ADD_PATIENT = 4;
   static final int SWAP_PATIENT = 5;
   static final int DELETE_PATIENTS = 6;
   static final int ADD_USER = 7;
   static final int ADD_BILL = 8;
   static final int DISPLAY_BILLING_HISTORY = 9;
   static final int DISPLAY_BMI_CHART = 10;
   static final int DISPLAY_LUNG_CAPACITY_CHART = 11;

   static final int ADD_MEDICATION = 2;
   static final int CHANGE_MEDICATION = 3;
   static final int DELETE_MEDICATION = 4;


   //choices for the patient data, change the max number in change patient and add patient when adding a new field that the user inputs
   static final int RETURN = 0;
   static final int FIRSTNAME = 1;
   static final int SURNAME = 2;
   static final int DATEOFBIRTH = 3;
   static final int WEIGHT = 4;
   static final int LENGTH = 5;
   static final int LUNG_CAPACITY = 6;


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

      allPatients.addPatient(new Patient(1, "White", "Walter", LocalDate.of(1958, 9, 7),93, 191, 6.0));
      allPatients.addPatient(new Patient(2, "Baldy", "Caped", LocalDate.of(1997, 6, 9),10, 175, 6.0));
      allPatients.addPatient(new Patient(3, "Duck", "Donald", LocalDate.of(1934, 6, 9),10, 130, 6.0));
      allPatients.addPatient(new Patient(4, "Henkema", "Henk", LocalDate.of(1999, 9, 9),50, 160, 6.0));
      allPatients.addPatient(new Patient(5, "Hat", "Straw", LocalDate.of(2004, 5, 5),64, 174, 6.0));

      currentPatient = allPatients.getPatient(1);
      currentUser = allUsers.getUser(1);

      sortPatients();
      flushScreen();
      chooseUser();
      menu();
   }

   public void flushScreen() {
      for (int i = 0; i < 32; i++) {
         System.out.println();
      }
   }

   public boolean userTypesYesOrNo() {
      System.out.println("\nType the corresponding number");
      System.out.println("1: Yes");
      System.out.println("2: No");
      int answer = bScan.nextInt(1, 2);
      return answer == 1;
   }

   private void chooseUser() {
      System.out.println("\nWhich user are you?\n");

      allUsers.printUsers();
      System.out.print("\nEnter #choice: ");


      int userToSwitchTo = bScan.nextInt(1, allUsers.getAmountOfUsers());
      currentUser = allUsers.getUser(userToSwitchTo);

      System.out.println();
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

         System.out.println("1: Return");

         allPatients.printPatients();

         System.out.print("\nEnter choice: ");

         int patientToSwitchTo = bScan.nextInt(1, allPatients.getAmountOfPatients() + 1);
         patientToSwitchTo -= 1;
         if (patientToSwitchTo == 0) {
            System.out.println("You have chosen to not change the patient");
         }
         else {
            currentPatient = allPatients.getPatient(patientToSwitchTo);
         }
         System.out.println();
      }
   }


   private void addUser() {
      System.out.println("How many users do you want to add?");

      int amountOfAddedUsers = bScan.nextInt(0, 1000000000);

      // prevents multiple calls being made when more than 1 user is added
      int size = allPatients.getAmountOfPatients();
      System.out.format("\nAre you sure you want to add %d amount of users\n", amountOfAddedUsers);

      if (!userTypesYesOrNo() || amountOfAddedUsers == 0) {
         System.out.println("You have chosen not to add any users\n");
      }
      else {
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

         if (!userTypesYesOrNo()) {
            System.out.println("You have chosen not to change the current user\n");
         } else {
            currentUser = allUsers.getUser(allUsers.getAmountOfUsers());
         }
      }
   }


   private void changePatientData() {

      System.out.println("\nType the corresponding number(s) of the data you would like to change; if there are multiple numbers, divide them with a comma ex. 4,5,6 (you cant choose 0 and 6 with other digits):\n");
      System.out.format("%d:  Return\n", 1);
      System.out.format("%d:  Surname\n", 2);
      System.out.format("%d:  First Name\n", 3);
      System.out.format("%d:  Date of Birth\n", 4);
      System.out.format("%d:  Weight\n", 5);
      System.out.format("%d:  Length\n", 6);
      System.out.format("%d:  Lung capacity\n", 7);

      System.out.format("%d:  Everything\n", 8);
      System.out.print("\nEnter #choice: ");

      //prevents for anything but integers and 1,2 integers being passed along
      int[] fieldsToBeChanged = bScan.nextInts(2, LUNG_CAPACITY + 1);

      //done with a boolean, so you don't change the mbi twice if the patients length and weight get changed at once
      boolean changeInBMI = false;
      for (int fieldToBeChanged : fieldsToBeChanged) {
         fieldToBeChanged -= 1;
         if (fieldToBeChanged == LENGTH || fieldToBeChanged == WEIGHT)
         {
            changeInBMI = true;
         }
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
               } while (weight < 1 || weight > 636);
               currentPatient.setWeight(weight);
            }

            case LENGTH -> {
               double length;
               do {
                  System.out.println("What do you want the length of the new patient to be? (cm)");
                  length = bScan.nextDouble(1, 273,"The length must be between 1 cm and 273 cm");
               } while (length < 1 || length > 273);
               currentPatient.setLength(length);
            }

            case LUNG_CAPACITY -> {
               double capacity;
               do {
                  System.out.println("What do you want the new lung capacity of the patient to be (L)");
                  capacity = bScan.nextDouble(1, 273,"The capacity must be between 0.1L and 10L");
               } while (capacity < 0.1 || capacity > 10);
               currentPatient.setLungCapacity(capacity);
            }

            default -> System.out.format("you made an oopsie :3 [%d]\n", fieldToBeChanged);
         }
      }

      if (changeInBMI)
      {
         currentPatient.addBMI();
      }
      System.out.println();
   }


   private void addPatient() {
      System.out.println("How many patients do you want to add?");
      int amountOfAddedPatients = bScan.nextInt(0, 100000000);
      int size = allPatients.getAmountOfPatients();

      System.out.format("\nAre you sure you want to add %d amount of users\n", amountOfAddedPatients);

      if (!userTypesYesOrNo() || amountOfAddedPatients == 0) {
         System.out.println("\nYou have chosen not to add any patients\n");
      }
      else {
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
            } while (weight < 1 || weight > 636);

            double length;
            do {
               System.out.println("What do you want the length of the new patient to be? (cm)");
               length = bScan.nextDouble(1, 273,  "The length must be between 1 cm and 273 cm, new length:");
            } while (length < 1 || length > 273);


            double capacity;
            do {
               System.out.println("What do you want the new lung capacity of the patient to be (cm)");
               capacity = bScan.nextDouble(1, 273,"The capacity must be between 0.1L and 10L");
            } while (capacity < 0.1 || capacity > 10);

            // adding the patient here so because the medicationList is an object that can always be altered
            allPatients.addPatient(new Patient(size + i, surname, firstname, born, weight, length, capacity));
            if (!currentUser.getMedicationEditingAuthorization()) {
               System.out.println("You are not authorized to add any medication");
            } else {
               System.out.println("Does the patient use any medication?");

               currentPatient = allPatients.getPatient(allPatients.getAmountOfPatients());
               if (userTypesYesOrNo()) {
                  addMedication();
               }
               else {
                  System.out.println("The patient doesn't use any medication");
               }
            }
         }

         System.out.println("Do you want the newly added patient to be the current patient?");

         if (!userTypesYesOrNo()) {
            System.out.println("You have chosen not to change the current patient\n");
         } else {
            currentPatient = allPatients.getPatient(allPatients.getAmountOfPatients());
         }
      }
   }


   private void deletePatients() {

      System.out.println("Type the corresponding number(s) of the patients you would like to delete; if there are multiple , divide them with a comma ex. 1,2,3:\n");

      System.out.println("1: Return");

      allPatients.printPatients();

      System.out.format("%d: All patients\n", allPatients.getAmountOfPatients() + 2);
      System.out.print("\nEnter #choice:");

      int[] usersToBeDeleted = bScan.nextInts(2, allPatients.getAmountOfPatients() + 1);

      // 0 is return, so it circumvents the deletion
      if (usersToBeDeleted[0] == 1) {
         System.out.println("You have chosen not to delete any patients");
      } else {
         //makes sure the user doesn't haphazardly delete patients
         System.out.println("\nAre you sure if you want to delete the patient(s)?");

         if (!userTypesYesOrNo()) {
            System.out.println("you didn't type yes so the patient(s) won't be deleted");
         } else {
            //reverses the numbers, so we don't get index errors when deleting multiple patients at once
            for (int j = 0; j < usersToBeDeleted.length / 2; j++) {
               int temp = usersToBeDeleted[j];
               usersToBeDeleted[j] = usersToBeDeleted[usersToBeDeleted.length - j - 1];
               usersToBeDeleted[usersToBeDeleted.length - j - 1] = temp;
            }

            for (int userToBeDeleted : usersToBeDeleted) {
               userToBeDeleted -= 1;
               allPatients.deletePatient(userToBeDeleted);
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

         System.out.println("1: Add nothing and return");
         int i = 1;
         for (Medication medication : allMedications.getAllMedicationData()) {
            i++;
            System.out.format("%d: %s (%s)\n", i, medication.getSubstance(), medication.getType());
         }
         System.out.format("%d: Add all of the medication\n", allMedications.getAmountOfMedication() + 2);

         System.out.print("\nEnter Choice: ");
         int[] medicationsToBeAdded = bScan.nextInts(2, allMedications.getAmountOfMedication() + 1);

         System.out.println();

         if (medicationsToBeAdded[0] != 1) {
            for (int medicationToBeAdded : medicationsToBeAdded) {
               medicationToBeAdded -= 1;
               boolean add = true;
               for (Medication medication : currentPatient.getMedicationsPatient()) {
                  if (Objects.equals(medication.getSubstance(), allMedications.getMedication(medicationToBeAdded).getSubstance())) {
                     add = false;
                     System.out.println("You have already prescribed this medicine to the patient, you can alter the dose for the medicine.\n");
                     break;
                  }
               }

               if (add) {
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
   }


   private void changeMedicationDosage() {
      if (!currentUser.getMedicationEditingAuthorization()) {
         System.out.println("You are not authorized to change or edit medication");
      }
      else {
         if (currentPatient.getAmountOfMedicationPatient() == 0) {
            System.out.println("\nThe patient doesn't currently have any medication administered to them, first administer medication to them\n");
         }
         else if (currentPatient.getAmountOfMedicationPatient() == 1) {
            System.out.format("\nWhat do you want the new dosage to be of %s?\n", currentPatient.getMedicationList().getMedication(1).getSubstance());
            currentPatient.getMedicationList().changeMedicationDosage(1, bScan.nextLine());
         }
         else {
            System.out.println("Of which medication would you like to alter the dose?\n");

            System.out.println("1: Don't change anything\n");

            currentPatient.printMedicationPatient();

            System.out.format("%d: Change the dosage of all of them\n", currentPatient.getAmountOfMedicationPatient() + 2);

            System.out.print("\nEnter choice: ");

            int[] medicationDosagesToBeAltered = bScan.nextInts(2, currentPatient.getAmountOfMedicationPatient() + 1);

            if (medicationDosagesToBeAltered[0] != 1) {
               for (int j : medicationDosagesToBeAltered) {
                  j -= 1;
                  System.out.format("\nWhat do you want the new dosage to be of %s?\n", currentPatient.getMedicationList().getMedication(j).getSubstance());
                  currentPatient.getMedicationList().changeMedicationDosage(j, bScan.nextLine());
               }
            }
         }
      }
   }


   private void deleteMedication() {
      if (currentPatient.getAmountOfMedicationPatient() == 0) {
         System.out.println("\nThere is no medication to delete, first prescribe medication\n");
      } else if (currentPatient.getAmountOfMedicationPatient() == 1) {
         System.out.format("\nWould you like to delete %s?\n", currentPatient.getMedicationList().getMedication(1).getSubstance());
         if (userTypesYesOrNo()) {
            currentPatient.getMedicationList().deleteMedication(1);
         }
         else {
            System.out.println("You have chosen to not delete the medication");
         }
      }
      else {
         System.out.println("What medication would you like to delete? Type the corresponding number(s) of the medication you would like to change; if there are multiple numbers, divide them with a comma ex. 1,2,3 (you cant choose 0 and \"all of them\" with other digits):\n");

         System.out.println("1: Delete nothing and return");

         currentPatient.printMedicationPatient();

         System.out.format("%d: Delete all of the medication\n", currentPatient.getAmountOfMedicationPatient() + 2);

         System.out.print("\nEnter Choice: ");


         int[] medicationsToBeDeleted = bScan.nextInts(2, currentPatient.getAmountOfMedicationPatient() + 1);

         if (medicationsToBeDeleted[0] != 1) {
            System.out.println("Are you sure if you want to delete the medication(s)?");

            if (!userTypesYesOrNo()) {
               System.out.println("you didn't type yes so the medication(s) won't be deleted");
            } else {
               //reverses the numbers, so we don't get index errors when deleting multiple medications at once
               for (int j = 0; j < medicationsToBeDeleted.length / 2; j++) {
                  int temp = medicationsToBeDeleted[j];
                  medicationsToBeDeleted[j] = medicationsToBeDeleted[medicationsToBeDeleted.length - j - 1];
                  medicationsToBeDeleted[medicationsToBeDeleted.length - j - 1] = temp;
               }
               for (int medicationToBeDeleted : medicationsToBeDeleted) {
                  medicationToBeDeleted -= 1;
                  currentPatient.getMedicationList().deleteMedication(medicationToBeDeleted);
               }
            }
         }
      }
   }


   private void addBillToPatient() {

      System.out.println("\nWhich procedure(s) would you like to bill the patient Type the corresponding number(s) of the procedure you would like to bill; if there are multiple numbers, divide them with a comma ex. 1,2,3 (you cant choose 0 and \\\"all of them\\\" with other digits)?\n");

      System.out.println("1: Return");
      currentUser.getListOfBillingsForOccupation().printAllBillsWithIndexWithoutDate();
      System.out.format("%d: Bill all procedures\n", currentUser.getListOfBillingsForOccupation().getAmountOfBills() + 2);

      System.out.print("\nEnter choice: ");
      int[] billsToBeAdded =  bScan.nextInts(2, currentUser.getListOfBillingsForOccupation().getAmountOfBills() + 1);

      System.out.println("Are you sure you want to bill these bills to the patient");

      boolean yesOrNo = userTypesYesOrNo();
      if (billsToBeAdded[0] != 1 && yesOrNo) {
         for (int billToBeAdded : billsToBeAdded) {
            billToBeAdded -= 1;
            Bill bill = currentUser.getListOfBillingsForOccupation().getBill(billToBeAdded);
            Bill tempBill = null;
            switch (bill.occupation()) {
               case "Dentist" -> tempBill = Bill.dentistBill(bill.procedure(), bill.price(), LocalDate.from(java.time.LocalDateTime.now()));
               case "General Practitioner" -> tempBill = Bill.generalPractitionerBill(bill.procedure(), bill.price(), LocalDate.from(java.time.LocalDateTime.now()));
               case "Physical Therapist" -> tempBill = Bill.physicalTherapistBill(bill.procedure(), bill.price(), LocalDate.from(java.time.LocalDateTime.now()));
               default -> System.out.println("you did an oopsie in addBillToPatient :3");
            }
            currentPatient.addBill(tempBill);
         }
      }
      if (!yesOrNo) {
         System.out.println("You have chosen not to bill the patient");
      }
      System.out.println();
   }


   private void displayBillingHistory() {
      if (currentPatient.getBillingsPatient().getAmountOfBills() == 0) {
         System.out.println("The patient has no billing history\n");
      }
      else {
         System.out.println("\nBilling history:");
         currentPatient.printBillingPatient(currentUser.getOccupation());
         System.out.printf("Total: € %.2f\n\n", currentPatient.getBillingsPatient().getTotalCostOfBillings());
      }
   }


   private void sortPatients() {
      System.out.println("How do you want to sort the patients?\n");

      System.out.println("1: I don't mind how the list is sorted");
      System.out.println("2: Alphabetically (surname)");
      System.out.println("3: Alphabetically (firstname)");
      System.out.println("4: By date of birth");

      System.out.print("\nEnter choice: ");

      //sorteert niet op hele naam, kan wel als je de hele naam als functie zou meegeven
      switch (bScan.nextInt(1, 4)) {
         case 1 -> System.out.println();
         case 2 -> allPatients.getAllPatients().sort(Comparator.comparing(Patient::getSurname));
         case 3 -> allPatients.getAllPatients().sort(Comparator.comparing(Patient::getFirstName));
         case 4 -> allPatients.getAllPatients().sort(Comparator.comparing(Patient::getDateOfBirth));
         default -> System.out.println("you did an oopsie in sortPatient switch :3 %d");
      }
   }

   void medicineMenu() {
      System.out.println("What do you want to do?\n");
      System.out.format("%d:  Return\n", STOP);
      System.out.format("%d:  Add medication\n", ADD_MEDICATION);
      System.out.format("%d:  Change medication dosage\n", CHANGE_MEDICATION);
      System.out.format("%d:  Delete medication from patient\n", DELETE_MEDICATION);
      System.out.print("\nEnter #choice: ");

      int choice = bScan.nextInt(1, 4);
      flushScreen();
      switch (choice) {
         case STOP -> System.out.println("Returning to menu");

         case ADD_MEDICATION -> addMedication();

         case CHANGE_MEDICATION -> changeMedicationDosage();

         case DELETE_MEDICATION -> deleteMedication();

         default -> System.out.format("you did an oopsie in medicine Menu:3 [%d]\n", choice);
      }
   }


   void menu() {
      boolean programHasBeenStopped = false;
      while (!programHasBeenStopped) {
         flushScreen();
         System.out.format("%s\n\n", "-".repeat(60));
         System.out.format("Current user: [%d] %s (%s)\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getOccupation());
         System.out.format("Current patient: %s\n\n", currentPatient.fullName());

         //the choices the user has. If the user can edit medication, those options are shown, else not
         System.out.format("%d:  STOP\n", STOP);
         System.out.format("%d:  View patient data\n", VIEW);
         System.out.format("%d:  Change current patient data\n", CHANGE_CURRENT_PATIENT_INFORMATION);
         System.out.format("%d:  Add a patient/patients\n", ADD_PATIENT);
         System.out.format("%d:  Change patient\n", SWAP_PATIENT);
         System.out.format("%d:  Remove a patient/patients\n", DELETE_PATIENTS);
         System.out.format("%d:  Add a user/users\n", ADD_USER);
         System.out.format("%d:  Add bill\n", ADD_BILL);
         System.out.format("%d:  Display billing history\n", DISPLAY_BILLING_HISTORY);
         System.out.format("%d:  Display BMI chart\n", DISPLAY_BMI_CHART);
         System.out.format("%d: Display Long Capacity chart\n", DISPLAY_LUNG_CAPACITY_CHART);

         int upperBound = DISPLAY_LUNG_CAPACITY_CHART;
         if (currentUser.getMedicationEditingAuthorization()) {
            System.out.format("%d: Medication menu\n", 12);
            upperBound = 12;
         }

         System.out.print("\nEnter #choice: ");

         int choice = bScan.nextInt(1, upperBound);
         flushScreen();
         switch (choice) {
            case STOP -> programHasBeenStopped = true; // interrupts the loop

            case VIEW -> currentPatient.viewData(currentUser);

            case SWAP_PATIENT -> swapPatient();

            case ADD_PATIENT -> addPatient();

            case CHANGE_CURRENT_PATIENT_INFORMATION -> changePatientData();

            case DELETE_PATIENTS -> deletePatients();

            case ADD_USER -> addUser();

            case ADD_BILL -> addBillToPatient();

            case DISPLAY_BILLING_HISTORY -> displayBillingHistory();

            case DISPLAY_BMI_CHART -> currentPatient.printBMIGraph();

            case DISPLAY_LUNG_CAPACITY_CHART -> currentPatient.printLungCapacityGraph();

            case 12 -> medicineMenu();

            default -> System.out.format("you did an oopsie in menu:3 [%d]\n", choice);
         }
      }
   }
}
