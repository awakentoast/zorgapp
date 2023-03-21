import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;


class Patient
{
   private final int id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private double    weight;
   private double    length;
   private final MedicationData medicationList;
   private final BillingData billings;

   public int calcAge(LocalDate born) {
      return Period.between(born, LocalDate.from(java.time.LocalDateTime.now())).getYears();
   }

   private double calcBMI(double weight, double length) {
      return (weight / ((length / 100) * (length / 100)));
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Constructor
   ////////////////////////////////////////////////////////////////////////////////
   Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double length, MedicationData medicationList, BillingData billings) {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
      this.weight = weight;
      this.length = length;
      this.medicationList = medicationList;
      this.billings = billings;
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Display patient data.
   ////////////////////////////////////////////////////////////////////////////////
   void viewData(User user) {
      System.out.println();
      System.out.format("======== Patient id=%d ========\n", id);
      System.out.format("%-17s %s\n", "Surname:", surname);
      System.out.format("%-17s %s\n", "firstName:", firstName);
      System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth);
      System.out.format("%-17s %s\n", "Age:", calcAge(dateOfBirth));

      if (!Objects.equals(user.getOccupation(), "Tandarts")) {
         System.out.format("%-17s %.1f kg\n", "Weight: ", weight);
         System.out.format("%-17s %.1f cm\n", "Length:", length);
         System.out.format("%-17s %.1f\n", "bmi:", calcBMI(weight, length));
      }

      System.out.println("\nMedications:");
      if (!user.getMedicationInsightAuthorization()) {
         System.out.println("You don't have the authorization to view medication data\n");
      }
      else {
         if (medicationList.getAmountOfMedication() == 0) {
            System.out.println("no current medication administered\n");
         } else {
            medicationList.printMedications();
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

   public BillingData getBillingsPatient() {
      return billings;
   }

   public void addBill(Bill bill) {
      billings.addBill(bill);
   }


   public void printBillingHistory(User user) {
      String occupation = user.getOccupation();
      double total = 0;

      for (Bill bill : billings.getBillings()) {
         if (Objects.equals(bill.occupation(), occupation)) {
            System.out.printf("%s: %f.2",bill.procedure(), bill.price());
            total += bill.price();
         }
      }
      System.out.printf("\ntotal: %f.2", total);
   }

   public void printMedicationPatient() {
      medicationList.printMedications();
   }

   public void printBillingPatient(String occupation) {
      billings.printBillingHistory(occupation);
   }
}
