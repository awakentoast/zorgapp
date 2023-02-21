import java.time.LocalDate;

class Patient
{
   static final int RETURN      = 0;
   static final int SURNAME     = 1;
   static final int FIRSTNAME   = 2;
   static final int DATEOFBIRTH = 3;
   static final int AGE         = 4;
   static final int WEIGHT      = 5;
   static final int LENGTH      = 6;

   private final int id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private int       age;
   private double    weight;
   private double    length;


   ////////////////////////////////////////////////////////////////////////////////
   // Constructor
   ////////////////////////////////////////////////////////////////////////////////
   Patient(int id, String surname, String firstName, LocalDate dateOfBirth, int age, double weight, double length) {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
      this.age = age;
      this.weight = weight;
      this.length = length;
      PatientData.incrementAmountOfPatients();
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Display patient data.
   ////////////////////////////////////////////////////////////////////////////////
   void viewData()
   {
      System.out.println();
      System.out.format("======== Patient id=%d ========\n", id);
      System.out.format("%-17s %s\n", "Surname:", surname);
      System.out.format("%-17s %s\n", "firstName:", firstName);
      System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth);
      System.out.format("%-17s %s\n", "Age:", age);
      System.out.format("%-17s %.1f\n", "Weight: ", weight);
      System.out.format("%-17s %.1f\n", "Length:", length);
      System.out.format("%-17s %.1f\n", "bmi:", weight / ((length / 100) * (length / 100)));
      System.out.println();
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Shorthand for a Patient's full name
   ////////////////////////////////////////////////////////////////////////////////

   public String fullName() {return String.format("%s %s [%s]",  firstName, surname, dateOfBirth.toString());}

   public void setSurname(String surname) { this.surname = surname; }
   public void setLength(double length) {
      this.length = length;
   }
   public void setWeight(double w) {weight = w;}
   public void setDateOfBirth(LocalDate born) {
      dateOfBirth = born;
      age = HandyMethods.calcAge(dateOfBirth);
   }
   public void setFirstName(String firstname) {firstName = firstname;}
}
