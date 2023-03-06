import java.io.InputStream;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

///////////////////////////////////////////////////////////////////////////////////
// BScanner is a scanner class, wrapping the java.util.Scanner, providing a bit more
// robustness in cases where the given input does not match the required one.
///////////////////////////////////////////////////////////////////////////////////
class BScanner
{
   private final Scanner scanner;


   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public BScanner()
   {
      this( System.in );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public BScanner( InputStream inputStream )
   {
      scanner = new Scanner( inputStream );
      scanner.useLocale( Locale.US );    // E.g. decimal point. No comma!
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
//   public BScanner( String arg )
//   {
//      scanner = new Scanner( arg );
//      scanner.useLocale( Locale.US );
//   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public int nextInt()
   {
      int r;

      while (true)
      {
         try
         {
            r = scanner.nextInt();
            break;
         }
         catch (Exception e)
         {
            System.out.println( "please enter integer" );
         }
         scanner.nextLine();
      }

      return r;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public double nextDouble()
   {
      double r;

      while (true)
      {
         try
         {
            r = scanner.nextDouble();
            break;
         }
         catch (Exception e)
         {
            System.out.println( "please enter a floating point number" );
         }
         scanner.nextLine();
      }

      return r;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public String nextLine()
   {
      String r;

      while (true)
      {
         try
         {
            r = scanner.useDelimiter("\n").next();
            break;
         }
         catch (Exception e)
         {
            System.out.println("please enter valid input");
         }
         scanner.next();
      }

      return r;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Note: STRICT formatting does not work....?????
   ///       java.time.format.ResolverStyle.STRICT (see formatter, below)
   ////////////////////////////////////////////////////////////////////////////////
   public LocalDate nextDate( String fmt )
   {
      LocalDate r;
 //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern( fmt ).withResolverStyle( STRICT );

      while (true)
      {
         try
         {
            r     = LocalDate.parse( fmt );
            break;
         }
         catch (Exception e) {
            System.out.printf("%s is not a valid date \n", fmt);
            System.out.print("please enter a valid date (YYY-MM-DD): \n");
            fmt = scanner.next();
         }
      }

      return r;
   }

}
