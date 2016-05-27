package util;

import domain.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameUtils {

    public static int getRandomPlayerId(List<Player> players) {
        Random random = new Random();

        List<Integer> serfIds = players
                .stream()
                .filter(player -> !player.isCardCzar())
                .map(Player::getId)
                .collect(Collectors.toList());

        return serfIds.get(random.nextInt(serfIds.size()));
    }
}
