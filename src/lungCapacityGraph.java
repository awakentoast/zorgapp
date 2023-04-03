import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lungCapacityGraph extends Graph
{

    public lungCapacityGraph(String label, String xAxis, String yAxis, List<Double> values) {
        super(label, xAxis, yAxis, values);
    }

    public String[] getColourForLines() {
        //codes to make a string a certain color when printing to console,
        //                      [red,        yellow,     green,
        String[] colourCodes = {"\033[31m", "\033[33m", "\033[32m"};
        String[] lineColour = new String[24];
        for (int i = 0; i < 24; i++) {
            //maps i ot lung capacity
            double lungCapacity = 1.8 + i * 0.2;
            if (lungCapacity > 5.0) {
                lineColour[i] = colourCodes[2];
            } else if (lungCapacity > 3.0) {
                lineColour[i] = colourCodes[1];
            } else {
                lineColour[i] = colourCodes[0];
            }
        }
        return lineColour;
    }

    @Override
    public List<StringBuilder> makeYAxis(String[] colourLines) {
        //size of 24, screen is 30 high. 1 for enter to exit and 1 line below it for cursor. 2 for title graph and newline. 1 for the x-axis and 1 for the label of x-axis, rest for y-axis and graph
        ArrayList<StringBuilder> graph = new ArrayList<>(24);

        double yValue = lowerY;

        //0 is x-axis, 23 is the highest value y-axis
        for (int i = 0; i < 24; i++) {
            //rounds the yValue to x.x then to x.2x
            yValue = Math.round(yValue * 10) / 10.0;
            //yValue = Math.floor(yValue * 5) / 5.0;

            StringBuilder line = new StringBuilder();
            line.append(yValue).append("|").append(colourLines[i]).append("=".repeat(210)).append(RESET);
            graph.add(line);
            yValue += yIncrement;
        }
        return graph;
    }

    @Override
    public List<StringBuilder> addPointsOnGraph(List<StringBuilder> graph, String[] lineColours) {
        Arrays.fill(amountLineAltered, 0);

        System.out.println(upperY + " " + lowerY + " " +  yIncrement);
        int offset = -1;
        for (int i = Math.min(sizeList, 10); i > 0 ; i--) {
            double value = Math.round(values.get(sizeList - i));

            if (value > upperY) {
                value = upperY;
            }
            if (value < lowerY) {
                value = lowerY;
            }

            offset += 21;
            System.out.println(value);
            int rowToBeAltered = (int) Math.round((value - lowerY) / yIncrement);

            graph.get(rowToBeAltered).replace(offset - 1 + 9 * amountLineAltered[rowToBeAltered], offset + 2 + 9  * amountLineAltered[rowToBeAltered], RESET + " X " + lineColours[rowToBeAltered]);
            amountLineAltered[rowToBeAltered] += 1;
        }
        return graph;
    }
}
