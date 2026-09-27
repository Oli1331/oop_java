package nsu.lab.blackjack.cardsLogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PileTest {
    @Test
    void testGetCard() {
        Pile p1 = new Pile(0);
        assertEquals(null, p1.getCard());
        p1.shuffle();
        assertEquals(null, p1.getCard());
        Pile p2 = new Pile(1);
        p2.fill(1);
        assertNotEquals(null, p2.getCard());

    }
}
