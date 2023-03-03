import java.time.LocalDate;


class Patient
{
   /*
   not used
   static final int RETURN      = 0;
   static final int SURNAME     = 1;
   static final int FIRSTNAME   = 2;
   static final int DATEOFBIRTH = 3;
   static final int AGE         = 4;
   static final int WEIGHT      = 5;
   static final int LENGTH      = 6;
*/

   private final int id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private int       age;
   private double    weight;
   private double    length;
   private MedicationData medicationList;
   // private MedicationData medicationList = new MedicationData();

   MedicationData medicationData = new MedicationData();


   ////////////////////////////////////////////////////////////////////////////////
   // Constructor
   ////////////////////////////////////////////////////////////////////////////////
   Patient(int id, String surname, String firstName, LocalDate dateOfBirth, int age, double weight, double length, MedicationData medicationList) {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
      this.age = age;
      this.weight = weight;
      this.length = length;
      this.medicationList = medicationList;
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Display patient data.
   ////////////////////////////////////////////////////////////////////////////////
   void viewData(boolean Insight) {
      System.out.println();
      System.out.format("======== Patient id=%d ========\n", id);
      System.out.format("%-17s %s\n", "Surname:", surname);
      System.out.format("%-17s %s\n", "firstName:", firstName);
      System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth);
      System.out.format("%-17s %s\n", "Age:", age);
      System.out.format("%-17s %.1f\n", "Weight: ", weight);
      System.out.format("%-17s %.1f\n", "Length:", length);
      System.out.format("%-17s %.1f\n", "bmi:", weight / ((length / 100) * (length / 100)));

      if (!Insight) {
         System.out.println("Medications:\nYou don't have authorization the to view medication data\n");
      }
      else{
         System.out.println("\nMedications:");
         if (medicationList.getAmountOfMedication() == 0) {
            System.out.println("no current medication administered");
         } else {
            for (Medication medication : medicationList.getAllMedicationData()) {
               System.out.println("  - " + medication.getSubstance());
               System.out.println("     -" + medication.getDose());
            }
         }
         System.out.println();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Shorthand for a Patient's full name
   ////////////////////////////////////////////////////////////////////////////////

   public String fullName() {return String.format("%s %s [%s]",  firstName, surname, dateOfBirth.toString());}

   public void setSurname(String surname) { this.surname = surname; }
   public void setLength(double length) {
      this.length = length;
   }
   public void setWeight(double weight) {this.weight = weight;}
   public void setDateOfBirth(LocalDate born) {
      this.dateOfBirth = born;
      this.age = HandyMethods.calcAge(dateOfBirth);
   }
   public void setFirstName(String firstname) {this.firstName = firstname;}

   public void addMedication(Medication medication) {
      //medicationList.addMedicine(medicine);
      medicationList.addMedication(medication);
   }

   public MedicationData getMedicationList() {
      return medicationList;
   }
}
