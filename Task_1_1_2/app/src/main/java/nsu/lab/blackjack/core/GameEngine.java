package nsu.lab.blackjack.core;


import nsu.lab.blackjack.cards.Pile;
import nsu.lab.blackjack.entity.Dealer;
import nsu.lab.blackjack.entity.Player;


/** Drives the blackjack game loop. */
public class GameEngine {
    private final GameStat stat = new GameStat();
    private final Pile gamePile;
    private final Pile discardPile;
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();
    private final ConsoleIo console = new ConsoleIo();

    /**
     * Creates an engine with the given number of decks.
     *
     * @param countDeck number of decks in the game pile
     */
    public GameEngine(int countDeck) {
        this.gamePile = new Pile(countDeck);
        this.discardPile = new Pile(countDeck);
        this.gamePile.fill(countDeck);
    }

    /** Plays rounds until the user decides to stop. */
    public void run() {
        while (true) {
            player.reset(discardPile);
            dealer.reset(discardPile);

            checkAndReshuffle();

            stat.incNumRound();
            console.messageRoundStart(stat.getNumRound());

            Round round = new Round(player, dealer, gamePile, console);
            RoundResult result = round.play();

            handleRoundResult(result);

            console.messageAboutExit();
            if (console.userAgree()) {
                break;
            }
        }
    }

    private void handleRoundResult(RoundResult result) {
        switch (result) {
            case PLAYER_BLACKJACK, PLAYER_WIN, DEALER_BUST -> {
                console.messagePlayerWin();
                stat.incPlayerPoints();
            }
            case DEALER_WIN, PLAYER_BUST -> {
                console.messagePlayerLose();
                stat.incDealerPoints();
            }
            case TIE -> console.messageTie();
        }
        console.messageCountPoints(stat.getPlayerPoints(), stat.getDealerPoints());
    }

    private void checkAndReshuffle() {
        if (gamePile.getCountCard() < 15) {
            while (discardPile.getCountCard() > 0) {
                gamePile.addCard(discardPile.getCard());
            }
        }
        gamePile.shuffle();
    }
}
