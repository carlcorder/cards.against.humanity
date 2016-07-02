package domain;

import com.google.common.collect.Lists;
import param.Parameters;
import util.GameUtils;

import java.util.List;

public class Game {

    private List<Player> players;
    private Player cardCzar;
    private int currentRound;
    private int roundsPlayed;

    public Game(int n) {
        // initialize the players
        initializePlayers(n);

        // initialize the game with a random card czar
        cardCzar = players.get(GameUtils.selectRandomPlayer(players));
        cardCzar.setCardCzar(true);
    }

    private void initializePlayers(int n) {
        players = Lists.newArrayList();
        // set player ids
        for(int i = 0; i < n; i++) {
            players.add(new Player(i));
        }
    }

    private void playRound() {
        // elect a new czar and reward one point
        Player newCzar = selectRoundWinner(this.players);
        // demote the current czar
        players.get(cardCzar.getId()).setCardCzar(false);
        //update the czar & increment the round count
        cardCzar = newCzar; currentRound++;
    }

    private Player selectRoundWinner(List<Player> players) {
        // choose a random player who is not the czar
        Player player = players.get(GameUtils.selectRandomPlayer(players));
        player.setCardCzar(true);
        player.addPoint();

        return player;
    }

    private Player getWinner() {
        for(Player player : players) {
            if(player.getPoints() >= Parameters.POINTS_TO_WIN) {
                this.roundsPlayed = currentRound;
                return player;
            }
        }
        return null;
    }

    public void run() {
        // keep playing until we have a winner
        while(getWinner() == null) {
            playRound();
        }
    }

    public int getRoundsPlayed() { return roundsPlayed; }

}
