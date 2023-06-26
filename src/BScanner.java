import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

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

   public int nextInt(int lowerBound, int upperBound)
   {
      int r = -1;

      while (true)
      {
         try
         {
            r = scanner.nextInt();
         }
         catch (Exception e)
         {
            System.out.println( "please enter integer" );
         }
         if (r >= lowerBound && r <= upperBound) {
            return r;
         }
         System.out.format("Please enter valid input between %d and %d\n", lowerBound, upperBound);
         scanner.nextLine();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////

   public double nextDouble(int lowerBound, int upperBound, String errorMessage)
   {
      double r = -1;

      while (true) {
         try {
            r = scanner.nextDouble();
         } catch (Exception e) {
            System.out.println("please enter a floating point number");
         }
         if (r >= lowerBound && r <= upperBound) {
            return r;
         } else {
            if (r > upperBound) {
               System.out.println("contact the guinness world book of records because you have a world record, if you can show us the record, we will accepts this input");
            }
            System.out.println(errorMessage);
         scanner.nextLine();
         }
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////

   public int[] nextInts(int lowerBound, int upperBound) {
      //lower bound is 1 and not return, upperBound is the highest choice not 'add all numbers'
      while (true) {
         String r = nextLine();

         //Set<String> set = ImmutableSet.of("a", "b", "c"); google guava way, don't wanna do it now
         Set<Character> check = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ',');

         //checks if the given digits are in the good format (1,2,...)
         while(true) {
            boolean goodString = true;
            for (int i = 0; i < r.length(); i++) {
               if (!(check.contains(r.charAt(i)))) {
                  System.out.println("Please enter a valid digit/valid digits ex. [1,2,3]:");
                  r = nextLine();
                  goodString = false;
                  break;
               }
            }

            //if the user types ",1" or "1,", it breaks when splitting you get ""
            if (r.charAt(0) == ',' || r.charAt(r.length() - 1) == ',') {
               System.out.println("Please enter a valid digit/valid digits ex. [1,2,3]:");
               r = nextLine();
               goodString = false;
            }
            if (goodString)  break;
         }

         //converts from the digits in the string into an int[]
         String[] numbersString = r.split(",");
         int[] numbers = new int[numbersString.length];
         for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
         }

         //if there is only 1 number, we can just check if it is in bounds
         if (numbers.length == 1 && numbers[0] >= lowerBound && numbers[0] <= upperBound) {
            return numbers;
         }

         Arrays.sort(numbers);

         //if upperbound + 1 is chosen (all values) we need to populate our array with all the digits
         if (numbers[0] == upperBound + 1 && numbers.length == 1) {
            int[] intArray = new int[upperBound - 1];
            for (int i = 0; i <= upperBound - lowerBound; i++) {
               intArray[i] = i + 2;
            }
            numbers = Arrays.copyOf(intArray, upperBound - 1);
            return numbers;
         }

         //now we check if the numbers in the array all fall in bounds, if its 0(return) or upperbound + 1(add all numbers) they cant be given with other digits
         int correct = 0;
         for (int number : numbers) {
            if (number >= lowerBound && number <= upperBound) {
               correct++;
            }
         }

         if (correct == numbers.length) {
            return numbers;
         } else {
            System.out.printf("Please enter a valid digit/valid digits ex. [1,2,3] between %d and %d", lowerBound - 1 , upperBound + 1);
         }
      }
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
     //DateTimeFormatter formatter = DateTimeFormatter.ofPattern( fmt ).withResolverStyle( STRICT );

      while (true)
      {
         try
         {
            r = LocalDate.parse( fmt );
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
