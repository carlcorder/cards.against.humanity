package util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import param.Parameters;

import java.text.DecimalFormat;

public class PlotUtils {

    public static JFreeChart preparePlot(double[] data) {

        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        dataset.addSeries("cards", data, data.length/100);

        String title = "Cards Against Humanity";
        String xAxis = "Rounds Played";
        String yAxis = "Relative Frequency";
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        return ChartFactory
                .createHistogram(title, xAxis, yAxis, dataset, orientation, false, false, false);
    }

    public static String getWindowTitle() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return "Simulation of " + formatter.format(Parameters.NUMBER_OF_GAMES) + " games";
    }

}
