package param;

public class Parameters {

    // simulation parameters
    public static final int POINTS_TO_WIN = 5;
    public static final int NUMBER_OF_PLAYERS = 8;
    public static final int NUMBER_OF_GAMES = 100000;

    // thread parameters
    public static final int NUMBER_OF_THREADS = 10;
    public static final int GAMES_PER_THREAD = (NUMBER_OF_GAMES/NUMBER_OF_THREADS);

}
