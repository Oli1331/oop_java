package nsu.lab.blackjack.cards;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/** Tests for card pile logic. */
public class PileTest {
    /** Verifies card retrieval process. */
    @Test
    void testGetCard() {
        Pile p1 = new Pile(0);
        assertNull(p1.getCard());
        p1.shuffle();
        assertNull(p1.getCard());
        Pile p2 = new Pile(1);
        p2.fill(1);
        assertNotEquals(null, p2.getCard());
    }
}
