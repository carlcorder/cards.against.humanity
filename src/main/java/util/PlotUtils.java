package util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import param.Parameters;

public class PlotUtils {


    public static JFreeChart preparePlot(double[] data, int bins) {

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

    public static int getBinSize() {
        return (Parameters.NUMBER_OF_PLAYERS*(Parameters.POINTS_TO_WIN - 1) + 1) - Parameters.POINTS_TO_WIN;
    }
}
