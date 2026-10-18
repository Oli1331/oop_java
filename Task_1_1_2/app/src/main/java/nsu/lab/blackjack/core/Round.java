package nsu.lab.blackjack.core;

import nsu.lab.blackjack.cards.Card;
import nsu.lab.blackjack.cards.Pile;
import nsu.lab.blackjack.entity.Dealer;
import nsu.lab.blackjack.entity.Player;

/** Plays a single round of blackjack. */
public class Round {
    private final Player player;
    private final Dealer dealer;
    private final Pile deck;
    private final ConsoleIo console; // Позже можно заменить интерфейсом

    /**
     * Creates a round.
     *
     * @param player the player
     * @param dealer the dealer
     * @param deck the deck to draw cards from
     * @param console console used for input and output
     */
    public Round(Player player, Dealer dealer, Pile deck, ConsoleIo console) {
        this.player = player;
        this.dealer = dealer;
        this.deck = deck;
        this.console = console;
    }

    /**
     * Deals the cards and plays the player and dealer turns.
     *
     * @return the result of the round
     */
    public RoundResult play() {
        dealInitialCards();

        if (player.haveBlackjack()) {
            return dealer.haveBlackjack() ? RoundResult.TIE : RoundResult.PLAYER_BLACKJACK;
        }

        playPlayerTurn();
        if (player.getSumCard() > 21) {
            return RoundResult.PLAYER_BUST;
        }

        playDealerTurn();
        return evaluateWinner();
    }

    private void dealInitialCards() {
        player.takeCard(deck.getCard());
        player.takeCard(deck.getCard());
        dealer.takeCard(deck.getCard());
        dealer.takeCardAsSecret(deck.getCard());

        console.messageDealCard();
        player.printCard();
        dealer.printCard();
    }

    private void playPlayerTurn() {
        console.messagePlayerTurn();
        while (player.getSumCard() < 21 && console.userAgree()) {
            Card c = deck.getCard();
            player.takeCard(c);
            console.messageOpenCard(c);
            player.printCard();
        }
    }

    private void playDealerTurn() {
        console.messageDealerTurn();
        dealer.openSecretCard();
        dealer.printCard();

        while (dealer.getSumCard() < 17) {
            Card c = deck.getCard();
            dealer.takeCard(c);
            console.messageOpenCard(c);
            dealer.printCard();
        }
    }

    /**
     * Compares the final sums and decides the winner.
     *
     * @return the result of the round
     */
    public RoundResult evaluateWinner() {
        int playerSum = player.getSumCard();
        int dealerSum = dealer.getSumCard();

        if (playerSum > 21) {
            return RoundResult.PLAYER_BUST;
        }
        if (dealerSum > 21) {
            return RoundResult.DEALER_BUST;
        }
        if (playerSum > dealerSum) {
            return RoundResult.PLAYER_WIN;
        }
        if (dealerSum > playerSum) {
            return RoundResult.DEALER_WIN;
        }
        return RoundResult.TIE;
    }
}
