import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class printGraph {
    private String label;
    private String xAxis;
    private String yAxis;

    private int lowerY;
    private int upperY;

    private int sizeList;
    private final List<Double> values;


    public printGraph(String label, String xAxis, String yAxis, int lowerY, int upperY, List<Double> values) {
        this.label = label;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.lowerY = lowerY;
        this.upperY = upperY;
        this.values = values;
        this.sizeList = values.size();
    }

    public ArrayList<StringBuilder> makeYAxis() {
        ArrayList<StringBuilder> lines = new ArrayList<>(24);

        double incrementY = (upperY - lowerY) / 23.0;
        incrementY = Math.round(incrementY * 10) / 10.0;

        //0 is x-axis, 23 is highest value y-axis
        for (int i = 0; i < 24; i++) {
            StringBuilder line = new StringBuilder();
            lines.add(new StringBuilder())
        }
    }

    public static void changeLineGraph() {

    }


    public void print() {
        printGraph print = new printGraph(label, xAxis, yAxis, lowerY, upperY, values);

        //size of 24, screen is 30 high. 2 for enter to exit and a line below it for cursor. 2 for title graph and newline. 1 for the x-axis and 1 for the label of x-axis, rest for y-axis and graph
        ArrayList<StringBuilder> graph = print.makeYAxis();

    }



}
