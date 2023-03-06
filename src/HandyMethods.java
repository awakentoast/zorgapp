import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class HandyMethods {

    private HandyMethods() {throw new IllegalStateException("Utility class");} // can't make an object from HandyMethods, as it's a utility class

    private static final BScanner bScan = new BScanner();

    public static int calcAge(LocalDate born) {
        return Period.between(born, LocalDate.from(java.time.LocalDateTime.now())).getYears();
    }

    public static boolean usersTypesYesOrNo(String yesOrNo) {
        return (Objects.equals(yesOrNo, "yes") || Objects.equals(yesOrNo, "Yes") || Objects.equals(yesOrNo, "YES"));
    }

    // function to make sure there are only integers and commas so the other functions don't run into errors when handed something else
    public static String validPhrase(String str) {
        //Set<String> set = ImmutableSet.of("a", "b", "c"); google guava way, don't wanna do it now
        Set<Character> check = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ',');
        while (true) {
            boolean goodString = true;
            for (int i = 0; i < str.length(); i++) {
                if (!(check.contains(str.charAt(i)))) {
                    System.out.println("Please enter a valid digit/valid digits ex. [1,2,3]:");
                    str = bScan.nextLine();
                    goodString = false;
                    break;
                }
            } if (goodString) return str;
        }
    }

    //zet een string met nummers gedeeld door komma's in een int array, de string moet altijd eerst door validPhrase zodat je deze functie altijd werkt
    public static int[] convertStringNumbersToArray(String str) {
        String[] numbersString = str.split(",");
        int[] numbers = new int[numbersString.length];
        for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
        } return numbers;
    }

    //checks if al the numbers in the array are in the given range, if not make them re-enter them.
    //also check if 0 and upper range from swap patient data are in an array with multiple digits, and makes them re-enter digits
    public static int[] correctInputs(int lowerBound, int higherBound, int[] numbers) {
        while (true) {
            if (numbers.length == 1 && numbers[0] <= higherBound + 1 && numbers[0] >= lowerBound) {
                return numbers;
            }
            int correct = 0;
            for (int number : numbers) {
                if (number <= higherBound && number >= lowerBound) {
                    correct++;
                }
            }
            //checks for upperbound and lowerbound in array
            if (Arrays.stream(numbers).anyMatch(i -> i == higherBound + 1) || Arrays.stream(numbers).anyMatch(i -> i == 0) && numbers.length != 1) {
                System.out.println("You can't choose return and change everything with other options/digits");
                System.out.format("please choose a new digits/new digits not containing 0 or %d in combination with other digits\n", higherBound + 1);
            }
            else if (correct == numbers.length) {return numbers;}
            else {
                System.out.println("please enter valid digit/digits");
            }
            numbers = convertStringNumbersToArray(validPhrase((bScan.nextLine())));
        }
    }

    //check if it falls in between bounds
    public static int correctIntInput(int lowerBound, int higherBound, int number) {
        while (true) {
            if (number >= lowerBound && number <= higherBound) return number;
            else {
                System.out.println("choose a *valid* digit!");
                number = bScan.nextInt();
            }
        }
    }

    public static double correctDoubleInput(int lowerBound, int higherBound, double number, String errorMessage) {
        while (true) {
            if (number >= lowerBound && number <= higherBound) return number;
            else {
                if (number > higherBound){
                    System.out.println("contact the guinness world book of records because you have a world record");
                }
                System.out.println(errorMessage);
                number = bScan.nextInt();
            }
        }
    }

    //this method takes the input, and applies all the functions to it
    public static int[] stringToNumbers(String str, int lowerbound, int highestValue) {

        int lengthStr = str.length();
        int[] intArray = new int[lengthStr / 2 + 1];

        //if change all patient data or delete all patients has been chosen, fill the fieldsToBeChanged array with all the options
        if (lengthStr == 1 && Integer.parseInt(str) == highestValue + 1) {

            intArray[0] = Integer.parseInt(str);

            int[] tempArray = new int[highestValue];

            for (int i = 0; i < highestValue; i++) {
                tempArray[i] = i + 1;
            }

            intArray = Arrays.copyOf(tempArray, highestValue);

        }
        else {
            intArray = HandyMethods.convertStringNumbersToArray(str);
            intArray = HandyMethods.correctInputs(lowerbound, highestValue, intArray);
        }
        return  intArray;
    }
}
