import java.time.LocalDate;
import java.time.Period;


class Patient
{
   private final int id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private double    weight;
   private double    length;

   private final MedicationData medicationList;

   public int calcAge(LocalDate born) {
      return Period.between(born, LocalDate.from(java.time.LocalDateTime.now())).getYears();
   }

   private double calcBMI(double weight, double length) {
      return (weight / ((length / 100) * (length / 100)));
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Constructor
   ////////////////////////////////////////////////////////////////////////////////
   Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double length, MedicationData medicationList) {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
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
      System.out.format("%-17s %s\n", "Age:", calcAge(dateOfBirth));
      System.out.format("%-17s %.1f\n", "Weight: ", weight);
      System.out.format("%-17s %.1f\n", "Length:", length);
      System.out.format("%-17s %.1f\n", "bmi:", calcBMI(weight, length));

      System.out.println("\nMedications:");
      if (!Insight) {
         System.out.println("You don't have the authorization to view medication data\n");
      }
      else{
         if (medicationList.getAmountOfMedication() == 0) {
            System.out.println("no current medication administered\n");
         } else {
            for (Medication medication : medicationList.getAllMedicationData()) {
               System.out.println("  - " + medication.getSubstance());
               System.out.println("     -" + medication.getDose());
            }
            System.out.println();
         }
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
   }

   public void setFirstName(String firstname) {this.firstName = firstname;}

   public void addMedication(Medication medication) {
      medicationList.addMedication(medication);
   }

   public MedicationData getMedicationList() {
      return medicationList;
   }
}
