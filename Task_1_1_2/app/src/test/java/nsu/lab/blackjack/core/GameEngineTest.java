package nsu.lab.blackjack.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/** Tests for the game engine logic. */
public class GameEngineTest {

    GameEngine game = new GameEngine(1);

    /** Tests card pile operations. */
    @Test
    void pileTest() {
        game.dealCard(); // crutch

        for (int i = 0; i < 52 - 4; i++) {
            assertNotNull(game.getCard());
        }
        assertNull(game.getCard());
    }

    /** Tests game statistics updates. */
    @Test
    void statTest() {
        assertEquals(0, game.getNumRound());
    }
}
