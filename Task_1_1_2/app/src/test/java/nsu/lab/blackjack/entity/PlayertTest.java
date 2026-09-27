package nsu.lab.blackjack.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nsu.lab.blackjack.cardsLogic.*;

public class PlayertTest {
    Player p = new Player();
    Dealer d = new Dealer();

    @Test
    void TestUsingCard() {
        assertThrows(IndexOutOfBoundsException.class, () -> p.getCard(0));

        Card AceCard = new Card(RankCard.ACE, SuitCard.HEARTS);
        p.takeCard(AceCard);
        assertEquals(p.getCountCard(), 1);
        assertEquals(p.getSumCard(), AceCard.getValue());
        assertEquals(AceCard, p.getCard(0));

        Card JackCard = new Card(RankCard.JACK, SuitCard.HEARTS);
        assertEquals(false, p.haveBlackjack());
        p.takeCard(JackCard);
        assertEquals(true, p.haveBlackjack());

        Pile discardPile = new Pile(1);
        p.reset(discardPile);
        assertEquals(0, p.getCountCard());
        assertEquals(2, discardPile.getCountCard());

        p.takeCard(AceCard);
        p.takeCard(AceCard);
        p.takeCard(AceCard);
        assertEquals(13, p.getSumCard());
        p.printCard();// crutch

    }

    @Test
    void TestDealerLogic() {
        Card AceCard = new Card(RankCard.ACE, SuitCard.HEARTS);

        d.takeCardAsSecret(AceCard);
        assertEquals(-1, d.getSumCard());
        d.openSecretCard();
        assertEquals(AceCard.getValue(), d.getSumCard());
        d.printCard(); // crutch
    }
}
