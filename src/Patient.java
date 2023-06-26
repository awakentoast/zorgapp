import java.time.LocalDate;
import java.time.Period;
import java.util.*;


class Patient {
   private final int id;
   private String surname;
   private String firstName;
   private LocalDate dateOfBirth;
   private double weight;
   private double length;
   private double lungCapacity;
   private final MedicationData medicationList = new MedicationData();
   private final BillingData billings = new BillingData();
   private final List<Double> bmiList = new ArrayList<>(List.of(26.4, 24.6, 22.5, 20.5, 18.3, 18.5, 19.8, 20.9, 22.4, 23.9));
   private final List<Double> lungCapacityList = new ArrayList<>(List.of(6.4, 6.7, 6.5, 5.4, 5.4, 5.3, 6.1, 6.4, 6.7, 6.4));

   public int calcAge(LocalDate born) {
      return Period.between(born, LocalDate.from(java.time.LocalDateTime.now())).getYears();
   }

   private double calcBMI(double weight, double length) {
      return (weight / ((length / 100) * (length / 100)));
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Constructor
   ////////////////////////////////////////////////////////////////////////////////

   Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double length, double lungCapacity) {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
      this.weight = weight;
      this.length = length;
      this.lungCapacity = lungCapacity;
      bmiList.add(calcBMI(weight, length));
      lungCapacityList.add(lungCapacity);
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
      System.out.format("%-17s %s L\n", "Lung capacity:", lungCapacity);

      if (!(user instanceof Dentist)) {
         System.out.format("%-17s %.1f kg\n", "Weight: ", weight);
         System.out.format("%-17s %.1f cm\n", "Length:", length);
         System.out.format("%-17s %.1f\n", "bmi:", calcBMI(weight, length));
      }

      System.out.println("\nMedications:");
      if (!user.getMedicationInsightAuthorization()) {
         System.out.println("You don't have the authorization to view medication data\n");
      } else {
         if (medicationList.getAmountOfMedication() == 0) {
            System.out.println("no current medication administered\n");
         } else {
            medicationList.printMedications();
            System.out.println();
         }
      }

      System.out.println("Press enter to continue the program...");
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
   }


   public int getId() {return id;}

   public String getSurname() {return surname;}

   public String getFirstName() {return firstName;}


   public String fullName() {
      return String.format("%s %s [%s]", firstName, surname, dateOfBirth.toString());
   }

   public void setSurname(String surname) {this.surname = surname;}

   public void setLength(double length) {this.length = length;}

   public void setWeight(double weight) {this.weight = weight;}

   public void setDateOfBirth(LocalDate born) {this.dateOfBirth = born;}

   public void setFirstName(String firstname) {this.firstName = firstname;}

   public void setLungCapacity(double lungCapacity) {
      this.lungCapacity = lungCapacity;
      lungCapacityList.add(lungCapacity);
   }

   public void addBMI() {bmiList.add(calcBMI(weight, length));}

   public void addMedication(Medication medication) {medicationList.addMedication(medication);}

   public List<Medication> getMedicationsPatient() {return medicationList.getAllMedicationData();}

   public int getAmountOfMedicationPatient() {return medicationList.getAmountOfMedication();}

   public MedicationData getMedicationList() {return medicationList;}

   public BillingData getBillingsPatient() {return billings;}

   public LocalDate getDateOfBirth() {return dateOfBirth;}

   public void addBill(Bill bill) {billings.addBill(bill);}

   public void printBillingHistory(User user) {
      String occupation = user.getOccupation();
      double total = 0;

      for (Bill bill : billings.getBillings()) {
         if (Objects.equals(bill.occupation(), occupation)) {
            System.out.printf("%s: %f.2", bill.procedure(), bill.price());
            total += bill.price();
         }
      }
      System.out.printf("\ntotal: %f.2", total);
   }

   public void printMedicationPatient() {medicationList.printMedications();}

   public void printBillingPatient(String occupation) {billings.printBillingHistory(occupation);}

   public void printBMIGraph() {
      Graph bmiGraph = new BmiGraph("bmi", "date", "bmi", bmiList);
      System.out.println("\n".repeat(5));
      System.out.printf("BMI chart %s (only last 10 entered are shown):\n\n", fullName());

      bmiGraph.printGraph();
   }

   public void printLungCapacityGraph() {
      Graph lungCapacityGraph = new LungCapacityGraph("lung capacity", "date", "lung capacity", lungCapacityList);
      System.out.println("\n".repeat(5));
      System.out.printf("Lung capacity chart %s (only last 10 entered are shown):\n\n", fullName());

      lungCapacityGraph.printGraph();
   }
}
