package nsu.lab.blackjack.gameCore;

import java.util.Scanner;
import nsu.lab.blackjack.cardsLogic.Card;

/**
 * Handles terminal I/O and user prompts.
 */
public class ConsoleIO {
    private Scanner scan;

    /**
     * Initializes console scanner.
     */
    public ConsoleIO() {
        scan = new Scanner(System.in);
    }

    /**
     * Prints welcome message and deck prompt.
     */
    public void messageGameStart() {
        System.out.print("Добро пожаловать в Блэкджек! \n Сколько колод будут в игре? ");
    }

    /**
     * Prompts user about game exit.
     */
    public void messageAboutExit() {
        System.out.print("Выйти из игры? ");
    }

    /**
     * Displays current round scores.
     *
     * @param playerPoints player score.
     * @param dealerPoints dealer score.
     */
    public void messageCountPoints(int playerPoints, int dealerPoints) {
        System.out.println("Счёт: Дилер=" + dealerPoints + "\tИгрок=" + playerPoints);
    }

    /**
     * Prints round header banner.
     *
     * @param numRound round number.
     */
    public void messageRoundStart(int numRound) {
        System.out.println("Раунд №" + numRound);
    }

    /**
     * Announces blackjack.
     */
    public void messageBlackjack() {
        System.out.println("Блэкджек!");
    }

    /**
     * Prints player turn banner.
     */
    public void messagePlayerTurn() {
        System.out.println("\n\n*********\n*ВАШ ХОД*\n*********\nВзять карту?\n");
    }

    /**
     * Prints dealer turn banner.
     */
    public void messageDealerTurn() {
        System.out.println("\n\n************\n*ХОД ДИЛЕРА*\n************\n Дилер открывает закрытую карту ");
    }

    /**
     * Announces cards deal.
     */
    public void messageDealCard() {
        System.out.println("Дилер раздал карты");
    }

    /**
     * Prints newly opened card.
     *
     * @param c card drawn.
     */
    public void messageOpenCard(Card c) {
        System.out.println("Открыта карта " + c);
    }

    /**
     * Announces player victory.
     */
    public void messagePlayerWin() {
        System.out.println("Вы победили!");
    }

    /**
     * Announces player defeat.
     */
    public void messagePlayerLose() {
        System.out.println("Вы проиграли.");
    }

    /**
     * Announces player bust.
     */
    public void messagePlayerMore21() {
        System.out.println("У вас сумма карт больше 21.");
    }

    /**
     * Announces dealer bust.
     */
    public void messageDealerMore21() {
        System.out.println("У дилера сумма карт больше 21.");
    }

    /**
     * Announces dealer higher score.
     */
    public void messageDealerMorePlayer() {
        System.out.println("У дилера сумма карт больше.");
    }

    /**
     * Announces player higher score.
     */
    public void messagePlayerMoreDealer() {
        System.out.println("У вас сумма карт больше.");
    }

    /**
     * Announces tie game.
     */
    public void messageTie() {
        System.out.println("Ничья");
    }

    /**
     * Reads user confirmation Y/N.
     *
     * @return true if accepted, false otherwise.
     */
    public boolean userAgree() {
        System.out.println("Подтвердить? (Y,n) ");
        while (true) {
            String str = scan.nextLine().trim();
            if (str.isEmpty()) {
                return true;
            }
            if (str.length() > 1) {
                System.out.println("Введите один символ ");
                continue;
            }
            char symbol = Character.toUpperCase(str.charAt(0));
            if (symbol == 'Y') {
                return true;
            } else if (symbol == 'N') {
                return false;
            } else {
                System.out.println("Введите y или n ");
                continue;
            }
        }
    }
}