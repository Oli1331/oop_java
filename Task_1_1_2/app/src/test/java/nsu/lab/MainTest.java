package nsu.lab;

import nsu.lab.blackjack.Main;

import org.junit.jupiter.api.Test;

/** Tests application main entry. */
public class MainTest {
    /** Verifies main loop termination. */
    @Test
    void loopOutTest() {
        Main.main(new String[] {"0"});
    }
}
