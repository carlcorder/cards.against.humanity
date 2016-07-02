import com.google.common.collect.Lists;
import domain.Game;
import org.jfree.ui.RefineryUtilities;
import param.Parameters;
import plot.DataPlot;
import util.PlotUtils;

import java.util.List;
import java.util.concurrent.*;

public class CardsAgainstHumanity {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(Parameters.NUMBER_OF_THREADS);
        List<Future<List<Double>>> futures = Lists.newArrayList();

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < Parameters.NUMBER_OF_THREADS; i++) {
            futures.add(executorService.submit(new Callable<List<Double>>() {

                List<Double> rounds = Lists.newArrayList();

                @Override
                public List<Double> call() throws Exception {
                    for(int j = 0; j < Parameters.GAMES_PER_THREAD; j++) {
                        Game game = new Game(Parameters.NUMBER_OF_PLAYERS);
                        game.run();
                        rounds.add(Double.valueOf(game.getRoundsPlayed()));
                    }
                    return rounds;
                }
            }));

        }

        List<Double> aggregateResults = Lists.newArrayList();

        for(int i = 0; i < Parameters.NUMBER_OF_THREADS; i++) {
            Future<List<Double>> future = futures.get(i);
            aggregateResults.addAll(future.get());
        }

        final DataPlot dataPlot = new DataPlot("Simulation of " + Parameters.NUMBER_OF_GAMES + " games",
                PlotUtils.preparePlot(PlotUtils.convertData(aggregateResults), PlotUtils.getBinSize()));

        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time : " + (endTime - startTime) + " ms");

        dataPlot.pack();
        RefineryUtilities.centerFrameOnScreen(dataPlot);
        dataPlot.setVisible(true);
    }

}
