package nsu.lab;

import org.junit.jupiter.api.Test;

import nsu.lab.blackjack.Main;

/**
 * Tests application main entry.
 */
public class MainTest {
    /**
     * Verifies main loop termination.
     */
    @Test
    void loopOutTest() {
        Main.main(new String[] { "0" });
    }
}
