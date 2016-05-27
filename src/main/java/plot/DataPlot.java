package plot;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

public class DataPlot extends ApplicationFrame {

    public DataPlot(final String title, JFreeChart chart) {
        super(title);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(525,300));
        setContentPane(chartPanel);
    }
}
