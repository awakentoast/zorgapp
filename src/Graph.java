import java.util.*;

public abstract class Graph {
    protected final String label;
    protected final String xAxis;
    protected final String yAxis;

    protected final double lowerY;
    protected final double upperY;

    protected final List<Double> values;
    protected final int sizeList;
    protected final double yIncrement;

    protected final String RESET = "\033[0m";
    protected final int[] amountLineAltered = new int[23];


    protected Graph(String label, String xAxis, String yAxis, List<Double> values) {
        this.label = label;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.lowerY = Math.floor(Collections.min(values));
        this.upperY = Math.ceil(Collections.max(values));
        this.values = values;
        this.sizeList = values.size();
        double tempYIncrement = (upperY - lowerY) / 23.0;
        this.yIncrement = Math.round(tempYIncrement * 10) / 10.0;
    }

    public abstract String[] getColourForLines();
    public abstract List<StringBuilder> makeYAxis(String[] colourLines);

    public abstract List<StringBuilder> addPointsOnGraph(List<StringBuilder> graph, String[] lineColours);

    public void printGraph(List<StringBuilder> graph) {
        //prints the graph in reverse because the top of the graph is at index 23, but needs to be printed first
        for (int i = graph.size() - 1; i >= 0; i--) {
            System.out.println(graph.get(i).toString());
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
