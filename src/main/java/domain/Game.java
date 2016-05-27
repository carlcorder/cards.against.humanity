package domain;


import util.GameUtils;

import java.util.List;

public class Game {

    public List<Player> players;
    Player cardCzar;

    int currentRound;
    int roundsPlayed;

    public static final int POINTS_TO_WIN = 5;

    public Game(List<Player> players) {
        this.players = players;

        // initialize the game with a random card czar
        cardCzar = players.get(GameUtils.getRandomPlayerId(players));
        cardCzar.setCardCzar(true);
    }

    public int getRoundsPlayed() { return roundsPlayed; }

    public void playRound() {
        // elect a new czar and reward one point
        Player newCzar = selectRoundWinner(this.players);
        // demote the current czar
        players.get(cardCzar.getId()).setCardCzar(false);
        //update the czar & increment the round count
        cardCzar = newCzar; currentRound++;
    }

    public Player selectRoundWinner(List<Player> players) {
        // choose a random player who is not the czar
        Player player = players.get(GameUtils.getRandomPlayerId(players));
        player.setCardCzar(true);
        player.addPoint();

        return player;
    }

    public Player getWinner() {
        for(Player player : players) {
            if(player.getPoints() >= POINTS_TO_WIN) {
                this.roundsPlayed = currentRound;
                return player;
            }
        }
        return null;
    }

}
