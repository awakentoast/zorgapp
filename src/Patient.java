import java.time.LocalDate;
import java.time.Period;

class Patient
{
   static final int RETURN      = 0;
   static final int SURNAME     = 1;
   static final int FIRSTNAME   = 2;
   static final int DATEOFBIRTH = 3;

   int       id;
   String    surname;
   String    firstName;
   LocalDate dateOfBirth;

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   String getSurname()
   {
      return surname;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   String getFirstName()
   {
      return firstName;
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Constructor
   ////////////////////////////////////////////////////////////////////////////////
   Patient( int id, String surname, String firstName, LocalDate dateOfBirth )
   {
      this.id          = id;
      this.surname     = surname;
      this.firstName   = firstName;
      this.dateOfBirth = dateOfBirth;
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Display patient data.
   ////////////////////////////////////////////////////////////////////////////////
   void viewData()
   {
      System.out.format( "===== Patient id=%d ==============================\n", id );
      System.out.format( "%-17s %s\n", "Surname:", surname );
      System.out.format( "%-17s %s\n", "firstName:", firstName );
      System.out.format( "%-17s %s\n", "Date of birth:", dateOfBirth );
   }

   ////////////////////////////////////////////////////////////////////////////////
   // Shorthand for a Patient's full name
   ////////////////////////////////////////////////////////////////////////////////
   String fullName()
   {
      return String.format( "%s %s [%s]", firstName, surname, dateOfBirth.toString() );
   }
}
