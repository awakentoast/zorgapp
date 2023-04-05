import java.util.*;

public abstract class Graph {
    protected final String label;
    protected final String xAxis;
    protected final String yAxis;

    protected final double lowerY;
    protected final double upperY;
    protected int amountOfYLines;

    protected final List<Double> values;
    protected final int sizeList;
    protected final double yIncrement;

    protected String[] colourForLines;
    protected double[] valuesYGraph;
    protected int[] amountLineAltered;
    //size of 24, screen is 30 high. 1 for enter to exit and 1 line below it for cursor. 2 for title graph and newline. 1 for the x-axis and 1 for the label of x-axis, rest for y-axis and graph
    private final ArrayList<StringBuilder> graphLines = new ArrayList<>(amountOfYLines);

    protected static final String RESET = "\033[0m";


    protected Graph(String label, String xAxis, String yAxis, List<Double> values) {
        this.label = label;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.lowerY = Math.floor(Collections.min(values));
        this.upperY = Math.ceil(Collections.max(values));
        this.values = values;

        this.sizeList = values.size();
        double tempYIncrement = (upperY - lowerY + 1) / 24.0;
        this.yIncrement = Math.round(tempYIncrement * 10) / 10.0;
        this.amountOfYLines = 24;

        colourForLines = new String[amountOfYLines];
        valuesYGraph = new double[amountOfYLines];
        amountLineAltered = new int[amountOfYLines];

        createYGraphValues();
        getColourForLines();
        makeYAxis();
        addPointsOnGraph();
    }


    public void createYGraphValues() {
        double yValue = lowerY;
        yValue = Math.round(yValue * 10) / 10.0;
        valuesYGraph[0] = yValue;

        for (int i = 1; i < 24; i++) {
            //rounds the yValue to x.x then to x.2x
            yValue += yIncrement;
            yValue = Math.round(yValue * 10) / 10.0;
            valuesYGraph[i] = yValue;
        }
    }


    public abstract void getColourForLines();


    public void makeYAxis() {
        //0 is the lowest on y-axis, 23 is the highest value y-axis
        for (int i = 0; i < 24; i++) {
            StringBuilder line = new StringBuilder();
            line.append(valuesYGraph[i]).append("|").append(colourForLines[i]).append("=".repeat(210)).append(RESET);
            graphLines.add(line);
        }
    }


    public void addPointsOnGraph() {
        Arrays.fill(amountLineAltered, 0);

        int offset = -1;
        for (int i = Math.min(sizeList, 10); i > 0 ; i--) {
            double value = values.get(sizeList - i);
            System.out.println(value);
            if (value > upperY) {
                value = upperY;
            }
            if (value < lowerY) {
                value = lowerY;
            }
            System.out.println(value);
            offset += 21;
            int rowToBeAltered = (int) Math.floor((value - lowerY) / yIncrement);
            graphLines.get(rowToBeAltered).replace(offset - 1 + 9 * amountLineAltered[rowToBeAltered], offset + 2 + 9  * amountLineAltered[rowToBeAltered], RESET + " X " + colourForLines[rowToBeAltered]);
            amountLineAltered[rowToBeAltered] += 1;
        }
    }


    public void printGraph() {
        //prints the graph in reverse because the top of the graph is at index 23, but needs to be printed first
        for (int i = graphLines.size() - 1; i >= 0; i--) {
            System.out.println(graphLines.get(i).toString());
        }

        System.out.println(" ".repeat(5) + "-".repeat(210));
        //prints the numbers on the x-axis
        StringBuilder line = new StringBuilder();
        line.append(" ".repeat(5));
        for (int i = 1; i <= Math.min(sizeList, 10); i++) {
            line.append(" ".repeat(10)).append(i).append(" ".repeat(10));
        }
        System.out.println(line);

        System.out.println("Press enter to continue the program...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
