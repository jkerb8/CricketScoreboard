package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;


import java.util.ArrayList;

public class Player {
    private int[] scores;
    private int[] states;
    private int totalScore;
    public String name;

    Player() {
        scores = new int[7];
        states = new int[7];
        totalScore = 0;
        name = "";
    }

    public void calculateTotal() {
        for (int i=0; i<7; i++) {
            totalScore += scores[i];
        }
    }

    public int hit(int number) {
        if (states[number] < 3) {
            return states[number]++;
        }
        else {
            return 4;
        }
    }

    public int takeAway(int number) {
        if (states[number] > 0) {
            states[number]--;
        }
        return states[number];
    }

    public void addScore(int number) {
        int add = getNumber(number);
        scores[number] += add;
        totalScore += add;
    }

    public void minusScore(int number) {
        int minus = getNumber(number);
        scores[number] -= minus;
        totalScore -= minus;
    }

    private int getNumber(int number) {
        switch (number) {
            case 0:
                return 20;
            case 1:
                return 19;
            case 2:
                return 18;
            case 3:
                return 17;
            case 4:
                return 16;
            case 5:
                return 15;
            case 6:
                return 25;
            default:
                return 0;
        }
    }

    public void reset() {
        for (int i=0; i<scores.length; i++) {
            scores[i] = 0;
            states[i] = 0;
        }
        totalScore = 0;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public int[] getStates() {
        return states;
    }

    public void setStates(int[] states) {
        this.states = states;
    }
}
