package nsu.lab.blackjack.core;

import nsu.lab.blackjack.cards.Card;
import nsu.lab.blackjack.cards.Pile;
import nsu.lab.blackjack.entity.Dealer;
import nsu.lab.blackjack.entity.Player;

/** Main game engine orchestrating rounds and turns. */
public class GameEngine {

    private final GameStat stat;
    private final Pile gamePile;
    private final Pile discardPile;
    private final Player player;
    private final Dealer dealer;
    private final ConsoleIo console = new ConsoleIo();

    /**
     * Initializes engine with deck count.
     *
     * @param countDeck amount of decks.
     */
    public GameEngine(int countDeck) {
        player = new Player();
        dealer = new Dealer();
        gamePile = new Pile(countDeck);
        discardPile = new Pile(countDeck);
        gamePile.fill(countDeck);
        stat = new GameStat();
    }

    /** Deals starting cards to player and dealer. */
    public void dealCard() {
        player.takeCard(getCard());
        player.takeCard(getCard());
        dealer.takeCard(getCard());
        dealer.takeCardAsSecret(getCard());
        console.messageDealCard();
        player.printCard();
        dealer.printCard();
    }

    /**
     * Draws card from pile reshuffling discard pile if needed.
     *
     * @return drawn card.
     */
    public Card getCard() {
        if (gamePile.empty()) {
            mixCard();
        }
        return gamePile.getCard();
    }

    /** Moves all cards from discard pile back to game pile. */
    public void mixCard() {
        while (discardPile.getCountCard() > 0) {
            gamePile.addCard(discardPile.getCard());
        }
    }

    /** Executes interactive player turn loop. */
    public void playerTurn() {
        if (player.haveBlackjack()) {
            console.messageBlackjack();
            return;
        }
        console.messagePlayerTurn();
        player.printCard();
        dealer.printCard();
        while (player.getSumCard() < 21 && console.userAgree()) {
            Card c = getCard();
            player.takeCard(c);
            console.messageOpenCard(c);
            player.printCard();
        }
    }

    /** Executes automated dealer turn logic. */
    public void dealerTurn() {
        console.messageDealerTurn();
        dealer.openSecretCard();
        dealer.printCard();
        if (player.haveBlackjack() || dealer.haveBlackjack() || player.getSumCard() > 21) {
            return;
        }
        while (dealer.getSumCard() < 17) {
            Card c = getCard();
            dealer.takeCard(c);
            console.messageOpenCard(c);
            dealer.printCard();
        }
    }

    /**
     * Prepares and resets hands for a new round.
     *
     * @return true to continue, false to exit.
     */
    public boolean startRound() {
        player.reset(discardPile);
        dealer.reset(discardPile);
        stat.incNumRound();
        console.messageCountPoints(stat.getPlayerPoints(), stat.getDealerPoints());
        console.messageRoundStart(stat.getNumRound());
        console.messageAboutExit();
        return !console.userAgree();
    }

    /** Compares hands and calculates scores. */
    public void checkResults() {
        if (player.haveBlackjack()) {
            console.messagePlayerWin();
            stat.incPlayerPoints();
        } else if (player.getSumCard() > 21) {
            console.messagePlayerMore21();
            console.messagePlayerLose();
            stat.incDealerPoints();
        } else if (dealer.haveBlackjack()) {
            console.messagePlayerLose();
            stat.incDealerPoints();
        } else if (dealer.getSumCard() > 21) {
            console.messageDealerMore21();
            console.messagePlayerWin();
            stat.incPlayerPoints();
        } else if (dealer.getSumCard() > player.getSumCard()) {
            console.messageDealerMorePlayer();
            stat.incDealerPoints();
        } else if (player.getSumCard() > dealer.getSumCard()) {
            console.messagePlayerMoreDealer();
            stat.incPlayerPoints();
        } else {
            console.messageTie();
        }
    }

    /**
     * Returns the current round counter.
     *
     * @return current round number.
     */
    public int getNumRound() {
        return stat.getNumRound();
    }
}
