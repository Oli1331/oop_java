package nsu.lab;

import org.junit.jupiter.api.Test;

import nsu.lab.blackjack.Main;

public class MainTest {
    @Test
    void LoopOutTest() {
        Main.main(new String[] { "0" });
    }
}
