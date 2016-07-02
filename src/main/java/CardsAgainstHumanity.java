import domain.Game;
import org.jfree.ui.RefineryUtilities;
import param.Parameters;
import plot.DataPlot;
import util.PlotUtils;

public class CardsAgainstHumanity {

    public static void main(String[] args) {

        double[] rounds = new double[Parameters.NUMBER_OF_GAMES];

        // replay the game
        for(int i = 0; i < Parameters.NUMBER_OF_GAMES; i++) {
            Game game = new Game(Parameters.NUMBER_OF_PLAYERS);
            game.run();
            // track all of the rounds played
            rounds[i] = (double) game.getRoundsPlayed();
        }

        final DataPlot dataPlot = new DataPlot("Simulation of " + Parameters.NUMBER_OF_GAMES + " games",
                PlotUtils.preparePlot(rounds, PlotUtils.getBinSize()));

        dataPlot.pack();
        RefineryUtilities.centerFrameOnScreen(dataPlot);
        dataPlot.setVisible(true);
    }

}
