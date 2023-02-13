import java.time.LocalDate;
import java.util.Scanner;

///////////////////////////////////////////////////////////////////////////
// class Administration represents the core of the application by showing
// the main menu, from where all other functionality is accessible, either
// directly or via sub-menus.
//
// An Adminiatration instance needs a User as input, which is passed via the
// constructor to the data member 'çurrentUser'.
// The patient data is available via the data member çurrentPatient.
/////////////////////////////////////////////////////////////////
class Administration
{
   static final int STOP = 0;
   static final int VIEW = 1;

   Patient currentPatient;            // The currently selected patient
   User    currentUser;               // the current user of the program.

   /////////////////////////////////////////////////////////////////
   // Constructor
   /////////////////////////////////////////////////////////////////
   Administration( User user )
   {
      currentUser    = user;
      currentPatient = new Patient( 1, "Van Puffelen", "Pierre", LocalDate.of( 2000, 2, 29 ) );

      System.out.format( "Current user: [%d] %s\n", user.getUserID(), user.getUserName() );
   }

   /////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////
   void menu()
   {
      var scanner = new Scanner( System.in );  // User input via this scanner.

      boolean nextCycle = true;
      while (nextCycle)
      {
         System.out.format( "%s\n", "=".repeat( 80 ) );
         System.out.format( "Current patient: %s\n", currentPatient.fullName() );

         ////////////////////////a
         // Print menu on screen
         ////////////////////////
         System.out.format( "%d:  STOP\n", STOP );
         System.out.format( "%d:  View patient data\n", VIEW );
         ////////////////////////

         System.out.print( "enter #choice: " );
         int choice = scanner.nextInt();
         switch (choice)
         {
            case STOP: // interrupt the loop
               nextCycle = false;
               break;

            case VIEW:
               currentPatient.viewData();
               break;

            default:
               System.out.println( "Please enter a *valid* digit" );
               break;
         }
      }
   }
}
