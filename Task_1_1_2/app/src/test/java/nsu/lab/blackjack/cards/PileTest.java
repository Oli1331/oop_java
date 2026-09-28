package nsu.lab.blackjack.cards;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests for card pile logic.
 */
public class PileTest {
    /**
     * Verifies card retrieval process.
     */
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
