import org.jfree.ui.RefineryUtilities;
import plot.DataPlot;
import service.GamePlayingService;
import util.PlotUtils;

import java.util.concurrent.ExecutionException;

public class CardsAgainstHumanity {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        GamePlayingService gamePlayingService = new GamePlayingService();

        final DataPlot dataPlot = new DataPlot(PlotUtils.getWindowTitle(),
                PlotUtils.preparePlot(gamePlayingService.fetchResults()));

        dataPlot.pack();
        RefineryUtilities.centerFrameOnScreen(dataPlot);
        dataPlot.setVisible(true);
    }

}
