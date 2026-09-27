package nsu.lab.blackjack.gameCore;

/**
 * Tracks score statistics and round counters.
 */
public class GameStat {
    private int playerPoints = 0;
    private int dealerPoints = 0;
    private int numRound = 0;

    /**
     * Increments player win count.
     */
    protected void incPlayerPoints() {
        playerPoints++;
    }

    /**
     * Increments dealer win count.
     */
    protected void incDealerPoints() {
        dealerPoints++;
    }

    /**
     * Increments current round number.
     */
    protected void incNumRound() {
        numRound++;
    }

    /**
     * Returns total dealer points.
     *
     * @return dealer score.
     */
    protected int getDealerPoints() {
        return dealerPoints;
    }

    /**
     * Returns total player points.
     *
     * @return player score.
     */
    protected int getPlayerPoints() {
        return playerPoints;
    }

    /**
     * Returns current round index.
     *
     * @return round number.
     */
    protected int getNumRound() {
        return numRound;
    }
}