import java.util.ArrayList;

abstract class User {
   protected final String userName;
   protected final int    userID;


   public String getUserName()
   {
      return userName;
   }

   public int getUserID()
   {
      return userID;
   }

   public String getOccupation() {
      return "User";
   }

   public boolean getMedicationEditingAuthorization() {
      return true;
   }

   public boolean getMedicationInsightAuthorization() {
      return true;
   }


   protected User(int id, String name) {
      this.userID = id;
      this.userName = name;
   }

   public abstract BillingData getListOfBillings();
}
