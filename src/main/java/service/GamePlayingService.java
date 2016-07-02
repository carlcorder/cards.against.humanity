package service;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import domain.Game;
import param.Parameters;

import java.util.List;
import java.util.concurrent.*;

public class GamePlayingService {

    private ExecutorService executorService = Executors.newFixedThreadPool(Parameters.NUMBER_OF_THREADS);
    private List<Future<List<Double>>> futures = Lists.newArrayList();
    private List<Double> aggregateResults = Lists.newArrayList();

    private List<Future<List<Double>>> threadedSimulator() {
        for (int i = 0; i < Parameters.NUMBER_OF_THREADS; i++) {
            futures.add(executorService.submit(new Callable<List<Double>>() {
                List<Double> rounds = Lists.newArrayList();
                public List<Double> call() throws Exception {
                    for (int j = 0; j < Parameters.GAMES_PER_THREAD; j++) {
                        Game game = new Game(Parameters.NUMBER_OF_PLAYERS);
                        game.run();
                        rounds.add((double) game.getRoundsPlayed());
                    }
                    return rounds;
                }
            }));
        }
        return futures;
    }

    public double[] fetchResults() throws ExecutionException, InterruptedException {
        // recombine thread results
        for (int i = 0; i < Parameters.NUMBER_OF_THREADS; i++) {
            aggregateResults.addAll(threadedSimulator().get(i).get());
        }
        return Doubles.toArray(aggregateResults);
    }

}
