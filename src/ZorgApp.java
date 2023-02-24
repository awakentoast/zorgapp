import java.lang.reflect.AnnotatedArrayType;

class ZorgApp
{   public static void main( String[] args )
   {
      //initialize users and patients
      UserData.initialUsers();
      PatientData.initialPatients();

      //hier loopt de hele loop van het programma
      Administration.menu();
   }
}
