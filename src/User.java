
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

   public abstract String getOccupation();

   public abstract boolean getMedicationEditingAuthorization();

   public abstract boolean getMedicationInsightAuthorization();

   public abstract BillingData getListOfBillingsForOccupation();

   protected User(int id, String name) {
      this.userID = id;
      this.userName = name;
   }
}
