package nsu.lab.blackjack.gameCore;

import java.util.Scanner;

import nsu.lab.blackjack.cardsLogic.Card;

public class ConsoleIO {
    private Scanner scan;

    public ConsoleIO() {
        scan = new Scanner(System.in);
    }

    public void messageGameStart() {
        System.out.print("Добро пожаловать в Блэкджек! \n Сколько колод будут в игре? ");
    }

    public void messageAboutExit() {
        System.out.print("Выйти из игры? ");
    }

    public void messageCountPoints(int playerPoints, int dealerPoints) {
        System.out.println("Счёт: Дилер=" + dealerPoints + "\tИгрок=" + playerPoints);
    }

    public void messageRoundStart(int numRound) {
        System.out.println("Раунд №" + numRound);
    }

    public void messageBlackjack() {
        System.out.println("Блэкджек!");
    }

    public void messagePlayerTurn() {
        System.out.println("ВАШ ХОД\\n*************\n Взять карту?\n");
    }
    public void messageDealerTurn() {
        System.out.println("ХОД ДИЛЕРА\n*************\n Дилер открывает закрытую карту ");
    }

    public void messageDealCard() {
        System.out.println("Дилер раздал карты");
    }

    public void messageOpenCard(Card c) {
        System.out.println("Открыта карта " + c);
    }
    public void messagePlayerWin() {
        System.out.println("Вы победили!");
    }
    public void messagePlayerLose() {
        System.out.println("Вы проиграли.");
    }
    public void messagePlayerMore21() {
        System.out.println("У вас сумма карт больше 21.");
    }
    public void messageDealerMore21() {
        System.out.println("У дилера сумма карт больше 21.");
    }
    public void messageDealerMorePlayer() {
        System.out.println("У дилера сумма карт больше.");
    }
    public void messagePlayerMoreDealer() {
        System.out.println("У вас сумма карт больше.");
    }
    public void messageTie() {
        System.out.println("Ничья");
    }

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
