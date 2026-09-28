package nsu.lab.blackjack.entity;

import nsu.lab.blackjack.cards.Card;
import nsu.lab.blackjack.cards.Pile;
import nsu.lab.blackjack.cards.RankCard;
import nsu.lab.blackjack.cards.SuitCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Tests player and dealer entities. */
public class PlayertTest {
    /** Player instance. */
    Player player = new Player();

    /** Dealer instance. */
    Dealer dealer = new Dealer();

    /** Verifies player card operations. */
    @Test
    void testUsingCard() {
        assertThrows(IndexOutOfBoundsException.class, () -> player.getCard(0));

        Card aceCard = new Card(RankCard.ACE, SuitCard.HEARTS);
        player.takeCard(aceCard);
        assertEquals(player.getCountCard(), 1);
        assertEquals(player.getSumCard(), aceCard.getValue());
        assertEquals(aceCard, player.getCard(0));

        Card jackCard = new Card(RankCard.JACK, SuitCard.HEARTS);
        assertFalse(player.haveBlackjack());
        player.takeCard(jackCard);
        assertTrue(player.haveBlackjack());

        Pile discardPile = new Pile(1);
        player.reset(discardPile);
        assertEquals(0, player.getCountCard());
        assertEquals(2, discardPile.getCountCard());

        player.takeCard(aceCard);
        player.takeCard(aceCard);
        player.takeCard(aceCard);
        assertEquals(13, player.getSumCard());
        player.printCard(); // crutch
    }

    /** Verifies dealer logic rules. */
    @Test
    void testDealerLogic() {
        Card aceCard = new Card(RankCard.ACE, SuitCard.HEARTS);

        dealer.takeCardAsSecret(aceCard);
        assertEquals(-1, dealer.getSumCard());
        dealer.openSecretCard();
        assertEquals(aceCard.getValue(), dealer.getSumCard());
        dealer.printCard(); // crutch
    }
}
