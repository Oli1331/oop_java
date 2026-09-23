package nsu.lab.blackjack.gameCore;

public class GameStat {
    private int playerPoints = 0;
    private int dealerPoints = 0;
    private int numRound = 0;

    protected void incPlayerPoints() {
        playerPoints++;
    }

    protected void incDealerPoints() {
        dealerPoints++;
    }

    protected void incNumRound() {
        numRound++;
    }

    protected int getDealerPoints() {
        return dealerPoints;
    }

    protected int getPlayerPoints() {
        return playerPoints;
    }

    protected int getNumRound() {
        return numRound;
    }

}
