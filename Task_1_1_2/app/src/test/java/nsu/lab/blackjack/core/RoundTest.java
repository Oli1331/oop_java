package nsu.lab.blackjack.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nsu.lab.blackjack.cards.Card;
import nsu.lab.blackjack.cards.RankCard;
import nsu.lab.blackjack.cards.SuitCard;
import nsu.lab.blackjack.entity.Dealer;
import nsu.lab.blackjack.entity.Player;
import org.junit.jupiter.api.Test;

/** Tests the winner evaluation of a round. */
public class RoundTest {
    /** Checks every branch of the winner evaluation. */
    @Test
    void PlayTest() {
        Player player = new Player();
        Dealer dealer = new Dealer();

        Card ace = new Card(RankCard.ACE, SuitCard.HEARTS);
        Card jack = new Card(RankCard.JACK, SuitCard.HEARTS);
        Round round = new Round(player, dealer, null, null);
        player.takeCard(ace);
        player.takeCard(jack);
        assertEquals(RoundResult.PLAYER_WIN, round.evaluateWinner());
        dealer.takeCard(ace);
        dealer.takeCard(jack);
        assertEquals(RoundResult.TIE, round.evaluateWinner());
        player.takeCard(new Card(RankCard.FOUR, SuitCard.HEARTS));
        dealer.takeCard(new Card(RankCard.FIVE, SuitCard.HEARTS));
        assertEquals(RoundResult.DEALER_WIN, round.evaluateWinner());
        dealer.takeCard(new Card(RankCard.QUEEN, SuitCard.HEARTS));
        dealer.takeCard(new Card(RankCard.QUEEN, SuitCard.HEARTS));
        assertEquals(RoundResult.DEALER_BUST, round.evaluateWinner());
        player.takeCard(new Card(RankCard.QUEEN, SuitCard.HEARTS));
        player.takeCard(new Card(RankCard.QUEEN, SuitCard.HEARTS));
        assertEquals(RoundResult.PLAYER_BUST, round.evaluateWinner());


    }
}
