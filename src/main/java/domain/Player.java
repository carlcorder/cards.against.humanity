package domain;

public class Player {

    private int id;
    private int points;
    private boolean cardCzar = false;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoint() { this.points++; }

    public boolean isCardCzar() {
        return cardCzar;
    }

    public void setCardCzar(boolean cardCzar) {
        this.cardCzar = cardCzar;
    }

}
