package nsu.lab.blackjack.gameCore;

import nsu.lab.blackjack.cardsLogic.Card;
import nsu.lab.blackjack.cardsLogic.Pile;
import nsu.lab.blackjack.entity.Dealer;
import nsu.lab.blackjack.entity.Player;

public class GameEngine {

    private GameStat stat;

    private Pile gamePile;
    private Pile discardPile;

    private Player player;
    private Dealer dealer;

    private ConsoleIO console = new ConsoleIO();

    public GameEngine(int countDeck) {
        player = new Player();
        dealer = new Dealer();
        gamePile = new Pile(countDeck);
        discardPile = new Pile(countDeck);
        gamePile.fill(countDeck);
        stat = new GameStat();
    }

    public void dealCard() {
        player.takeCard(getCard());
        player.takeCard(getCard());
        dealer.takeCard(getCard());
        dealer.takeCardAsSecret(getCard());
        console.messageDealCard();
        player.printCard();
        dealer.printCard();
    }

    public Card getCard() {
        if (gamePile.empty()) {
            mixCard();
        }
        return gamePile.getCard();

    }

    public void mixCard() {
        while (discardPile.getCountCard() > 0) {
            gamePile.addCard(discardPile.getCard());
        }
    }

    public void playerTurn() {
        if (player.haveBlackjack()) {
            console.messageBlackjack();
            return;
        }
        console.messagePlayerTurn();
        while (player.getSumCard() < 21 && console.userAgree()) {
            Card c = getCard();
            player.takeCard(c);
            console.messageOpenCard(c);
            player.printCard();
        }
    }

    public void dealerTurn() {
        console.messageDealerTurn();
        dealer.openSecretCard();
        dealer.printCard();
        if (player.haveBlackjack() || dealer.haveBlackjack() || player.getSumCard() > 21) {
            return;
        }

        console.messageDealerTurn();
        while (dealer.getSumCard() < 17) {
            Card c = getCard();
            dealer.takeCard(c);
            console.messageOpenCard(c);
            dealer.printCard();
        }
    }

    public boolean startRound() {
        player.reset(discardPile);
        dealer.reset(discardPile);
        stat.incNumRound();
        console.messageCountPoints(stat.getPlayerPoints(), stat.getDealerPoints());
        console.messageRoundStart(stat.getNumRound());
        console.messageAboutExit();
        return !console.userAgree();
    }

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
        }
        else if(player.getSumCard()>dealer.getSumCard()){
            console.messagePlayerMoreDealer();
            stat.incPlayerPoints();
        }
        else{
            console.messageTie();
        }
        stat.incNumRound();
    }

    public int getNumRound() {
        return stat.getNumRound();
    }

}
