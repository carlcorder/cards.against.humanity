import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import domain.Game;
import org.jfree.ui.RefineryUtilities;
import param.Parameters;
import plot.DataPlot;
import util.PlotUtils;

import java.util.List;
import java.util.concurrent.*;

public class CardsAgainstHumanity {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String windowTitle = "Simulation of " + Parameters.NUMBER_OF_GAMES + " games";

        ExecutorService executorService = Executors.newFixedThreadPool(Parameters.NUMBER_OF_THREADS);
        List<Future<List<Double>>> futures = Lists.newArrayList();
        List<Double> aggregateResults = Lists.newArrayList();

        for(int i = 0; i < Parameters.NUMBER_OF_THREADS; i++) {
            futures.add(executorService.submit(new Callable<List<Double>>() {
                List<Double> rounds = Lists.newArrayList();
                @Override
                public List<Double> call() throws Exception {
                    for(int j = 0; j < Parameters.GAMES_PER_THREAD; j++) {
                        Game game = new Game(Parameters.NUMBER_OF_PLAYERS);
                        game.run();
                        rounds.add((double) game.getRoundsPlayed());
                    }
                    return rounds;
                }
            }));
        }

        // recombine thread results
        for(int i = 0; i < Parameters.NUMBER_OF_THREADS; i++) {
            aggregateResults.addAll(futures.get(i).get());
        }

        final DataPlot dataPlot = new DataPlot(windowTitle,
                PlotUtils.preparePlot(Doubles.toArray(aggregateResults)));

        dataPlot.pack();
        RefineryUtilities.centerFrameOnScreen(dataPlot);
        dataPlot.setVisible(true);
    }

}
