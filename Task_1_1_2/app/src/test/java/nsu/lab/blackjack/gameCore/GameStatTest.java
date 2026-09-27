package nsu.lab.blackjack.gameCore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameStatTest {
    GameStat stat = new GameStat();

    @Test
    void IncrimentTest() {
        assertEquals(0, stat.getDealerPoints());
        assertEquals(0, stat.getNumRound());
        assertEquals(0, stat.getPlayerPoints());

        stat.incDealerPoints();
        stat.incNumRound();
        stat.incPlayerPoints();

        assertEquals(1, stat.getDealerPoints());
        assertEquals(1, stat.getNumRound());
        assertEquals(1, stat.getPlayerPoints());
    }
}
