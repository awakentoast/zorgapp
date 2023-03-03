class User
{
   private final String userName;
   private final int    userID;
   private final String occupation;
   private final boolean medicationEditing;
   private final boolean medicationInsight;


   ///////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////
   public String getUserName()
   {
      return userName;
   }

   public int getUserID()
   {
      return userID;
   }

   public String getOccupation() {
      return occupation;
   }

   public boolean getMedicationEditingAuthorization() {
      return medicationEditing;
   }

   public boolean getMedicationInsightAuthorization() {
      return medicationInsight;
   }

   public User(int id, String name, String occupation, boolean medicationEditing, boolean medicationInsight) {
      this.userID = id;
      this.userName = name;
      this.occupation = occupation;
      this.medicationEditing = medicationEditing;
      this.medicationInsight = medicationInsight;
   }
}
