class User
{
   private final String userName;
   private final int    userID;

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


   public User( int id, String name )
   {
      this.userID   = id;
      this.userName = name;
      UserData.incrementAmountOfUsers();
   }
}
