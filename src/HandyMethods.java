import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Set;

public class HandyMethods {
    private HandyMethods() {throw new IllegalStateException("Utility class");} // can't make an object from HandyMethods, as it's an utility class

    private static final BScanner bScan = new BScanner();

    public static int calcAge(LocalDate born) {
        return Period.between(born, LocalDate.from(java.time.LocalDateTime.now())).getYears();
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

    public static int[] convertNumbersToArray(String str) {
        String[] numbersString = str.split(",");
        int[] numbers = new int[numbersString.length];
        for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
        } return numbers;
    }

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
            if (Arrays.stream(numbers).anyMatch(i -> i == 6) || Arrays.stream(numbers).anyMatch(i -> i == 0)) {
                System.out.println("You can't choose return and change everything with other options/digits");
                System.out.format("please choose a new digits/new digits not containing 0 or %d in combination with other digits\n", higherBound + 1);
            }
            else if (correct == numbers.length) {return numbers;}
            else {
                System.out.println("please enter valid digit/digits");
            }
            numbers = convertNumbersToArray(validPhrase((bScan.nextLine())));
        }
    }

    public static int correctInput(int lowerBound, int higherBound, int number) {
        while (true) {
            if (number >= lowerBound && number <= higherBound) return number;
            else {
                System.out.println("choose a *valid* digit!");
                number = bScan.nextInt();
            }
        }
    }
}
