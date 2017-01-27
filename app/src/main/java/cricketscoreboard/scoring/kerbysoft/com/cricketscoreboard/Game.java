package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;


public class Game {
    private Player player1, player2;
    boolean over;

    Game() {
        player1 = new Player();
        player2 = new Player();
        over = false;
    }

    public void hit(int player, int number) {
        int state = 0;
        if (player == 1) {
            state = player1.hit(number);
            if (state == 4 && player2.getStates()[number] < 3) {
                player1.addScore(number);
            }
        }
        else if (player == 2) {
            state = player2.hit(number);
            if (state == 4 && player1.getStates()[number] < 3) {
                player2.addScore(number);
            }
        }
    }

    public void minus(int player, int number) {
        if (player == 1) {
            if (player1.getScores()[number] > 0) {
                player1.minusScore(number);
            }
            else {
                player1.takeAway(number);
            }
        }
        else if (player == 2) {
            if (player2.getScores()[number] > 0) {
                player2.minusScore(number);
            }
            else {
                player2.takeAway(number);
            }
        }
    }

    public void reset() {
        player1.reset();
        player2.reset();
        over = false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
