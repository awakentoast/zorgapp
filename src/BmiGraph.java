import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BmiGraph extends Graph
{
    public BmiGraph(String label, String xAxis, String yAxis, List<Double> values) {
        super(label, xAxis, yAxis, values);
        getColourForLines();
    }

    public void getColourForLines() {
        //codes to make a string a certain color when printing to console,
        //                      [red,        yellow,     green,
        String[] colourCodes = {"\033[31m", "\033[33m", "\033[32m"};
        colourForLines = new String[amountOfYLines];
        for (int i = 0; i < amountOfYLines; i++) {
            //maps the line to bmi
            double bmi = valuesYGraph[i];
            if (bmi > 30) {
                colourForLines[i] = colourCodes[0];
            } else if (bmi > 25) {
                colourForLines[i] = colourCodes[1];
            } else if (bmi > 20) {
                colourForLines[i] = colourCodes[2];
            } else if (bmi > 15) {
                colourForLines[i] = colourCodes[1];
            } else {
                colourForLines[i] = colourCodes[0];
            }
        }
    }
}
