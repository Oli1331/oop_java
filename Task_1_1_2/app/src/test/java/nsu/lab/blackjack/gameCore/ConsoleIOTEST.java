package nsu.lab.blackjack.gameCore;

import org.junit.jupiter.api.Test;

import nsu.lab.blackjack.cardsLogic.Card;
import nsu.lab.blackjack.cardsLogic.RankCard;
import nsu.lab.blackjack.cardsLogic.SuitCard;

public class ConsoleIOTEST {
    ConsoleIO console = new ConsoleIO();

    @Test
    void CrutchTestForScore() {
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
