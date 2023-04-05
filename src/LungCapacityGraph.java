import java.util.List;

public class LungCapacityGraph extends Graph
{
    public LungCapacityGraph(String label, String xAxis, String yAxis, List<Double> values) {
        super(label, xAxis, yAxis, values);
    }

    public void getColourForLines() {
        //codes to make a string a certain color when printing to console,
        //                      [red,        yellow,     green,
        String[] colourCodes = {"\033[31m", "\033[33m", "\033[32m"};
        for (int i = 0; i < 24; i++) {
            //maps i ot lung capacity
            double lungCapacity = 1.8 + i * 0.2;
            if (lungCapacity > 5.0) {
                colourForLines[i] = colourCodes[2];
            } else if (lungCapacity > 3.0) {
                colourForLines[i] = colourCodes[1];
            } else {
                colourForLines[i] = colourCodes[0];
            }
        }
    }
}
