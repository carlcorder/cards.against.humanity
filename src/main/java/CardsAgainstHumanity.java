import com.google.common.collect.Lists;
import domain.Game;
import domain.Player;
import org.jfree.ui.RefineryUtilities;
import plot.DataPlot;
import util.PlotUtils;

import java.util.List;

public class CardsAgainstHumanity {

    static List<Player> players;

    public static void main(String[] args) {

        // simulation parameters
        int replay = 1000000;
        int numPlayers = 13;
        int roundSum = 0;
        int roundMin = Integer.MIN_VALUE;
        int roundMax = Integer.MAX_VALUE;
        double[] rounds = new double[replay];
        Game game = null;

        // replay the game
        for(int i = 0; i < replay; i++) {
            CardsAgainstHumanity cardsAgainstHumanity = new CardsAgainstHumanity();
            cardsAgainstHumanity.choosePlayers(numPlayers);
            game = new Game(players);
            cardsAgainstHumanity.run(game);
            // track all of the rounds played
            rounds[i] = (double) game.getRoundsPlayed();
        }

        final DataPlot dataPlot = new DataPlot("cards", PlotUtils.preparePlot(rounds, PlotUtils.getBins(game)));
        dataPlot.pack();
        RefineryUtilities.centerFrameOnScreen(dataPlot);
        dataPlot.setVisible(true);
    }

    private void choosePlayers(int n) {
        players = Lists.newArrayList();
        // set player ids
        for(int i = 0; i < n; i++) {
            players.add(new Player(i));
        }
    }

    public void run(Game game) {
        // keep playing until we have a winner
        while(game.getWinner() == null) {
            game.playRound();
        }
    }

}
