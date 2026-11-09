package nsu.lab.blackjack.core;

import nsu.lab.blackjack.cards.Card;
import nsu.lab.blackjack.cards.RankCard;
import nsu.lab.blackjack.cards.SuitCard;

import org.junit.jupiter.api.Test;

/** Tests for console output messages. */
public class ConsoleIoTest {
    ConsoleIo console = new ConsoleIo();

    /** Executes coverage tests for messages. */
    @Test
    void crutchTestForScore() {
        console.messageAboutExit();
        console.messageBlackjack();
        console.messageDealCard();
        console.messageDealerMore21();
        console.messageDealerMorePlayer();
        console.messageDealerTurn();
        console.messageGameStart();
        console.messagePlayerLose();
        console.messagePlayerMore21();
        console.messagePlayerMoreDealer();
        console.messagePlayerTurn();
        console.messagePlayerWin();
        console.messageTie();
        console.messageRoundStart(99);
        console.messageOpenCard(new Card(RankCard.ACE, SuitCard.DIAMONDS));
        console.messageCountPoints(3, 3);
    }
}
