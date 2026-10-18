package nsu.lab.blackjack.core;

import java.util.Scanner;
import nsu.lab.blackjack.cards.Card;

/** Handles terminal I/O and user prompts. */
public class ConsoleIo {
    private final Scanner scan;

    /** Initializes console scanner. */
    public ConsoleIo() {
        scan = new Scanner(System.in);
    }

    /** Prints welcome message and deck prompt. */
    public void messageGameStart() {
        System.out.print("Welcome to Blackjack! \n How many decks will be in the game? ");
    }

    /** Prompts user about game exit. */
    public void messageAboutExit() {
        System.out.print("Exit the game? ");
    }

    /**
     * Displays current round scores.
     *
     * @param playerPoints player score.
     * @param dealerPoints dealer score.
     */
    public void messageCountPoints(int playerPoints, int dealerPoints) {
        System.out.println("Score: Dealer=" + dealerPoints + "\tPlayer=" + playerPoints);
    }

    /**
     * Prints round header banner.
     *
     * @param numRound round number.
     */
    public void messageRoundStart(int numRound) {
        System.out.println("Round #" + numRound);
    }

    /** Announces blackjack. */
    public void messageBlackjack() {
        System.out.println("Blackjack!");
    }

    /** Prints player turn banner. */
    public void messagePlayerTurn() {
        System.out.println("\n\n***********\n*YOUR TURN*\n***********\nTake a card?\n");
    }

    /** Prints dealer turn banner. */
    public void messageDealerTurn() {
        System.out.println(
                "\n\n***************\n*DEALER'S TURN*\n***************\n"
                        + "Dealer opens the hidden card ");
    }

    /** Announces cards deal. */
    public void messageDealCard() {
        System.out.println("Dealer dealt the cards");
    }

    /**
     * Prints newly opened card.
     *
     * @param c card drawn.
     */
    public void messageOpenCard(Card c) {
        System.out.println("Opened card " + c);
    }

    /** Announces player victory. */
    public void messagePlayerWin() {
        System.out.println("You won!");
    }

    /** Announces player defeat. */
    public void messagePlayerLose() {
        System.out.println("You lost.");
    }

    /** Announces player bust. */
    public void messagePlayerMore21() {
        System.out.println("Your card sum is greater than 21.");
    }

    /** Announces dealer bust. */
    public void messageDealerMore21() {
        System.out.println("Dealer's card sum is greater than 21.");
    }

    /** Announces dealer higher score. */
    public void messageDealerMorePlayer() {
        System.out.println("Dealer's card sum is greater.");
    }

    /** Announces player higher score. */
    public void messagePlayerMoreDealer() {
        System.out.println("Your card sum is greater.");
    }

    /** Announces tie game. */
    public void messageTie() {
        System.out.println("Tie");
    }

    /**
     * Reads user confirmation Y/N.
     *
     * @return true if accepted, false otherwise.
     */
    public boolean userAgree() {
        System.out.println("Confirm? (Y,n) ");
        while (true) {
            String str = scan.nextLine().trim();
            if (str.isEmpty()) {
                return true;
            }
            if (str.length() > 1) {
                System.out.println("Enter one character ");
                continue;
            }
            char symbol = Character.toUpperCase(str.charAt(0));
            if (symbol == 'Y') {
                return true;
            } else if (symbol == 'N') {
                return false;
            } else {
                System.out.println("Enter y or n ");
                continue;
            }
        }
    }
}
