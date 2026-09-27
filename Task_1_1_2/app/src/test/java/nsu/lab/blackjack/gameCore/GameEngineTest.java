package nsu.lab.blackjack.gameCore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class GameEngineTest {

    GameEngine game = new GameEngine(1);

    @Test
    void PileTest() {
        game.dealCard(); //crutch

        for (int i = 0; i < 52-4; i++) {
            assertNotNull(game.getCard());
        }
        assertNull(game.getCard());
        
    }

    @Test
    void StatTest() {
        assertEquals(0, game.getNumRound());
        game.checkResults();
        assertEquals(1, game.getNumRound());
        

    }
}