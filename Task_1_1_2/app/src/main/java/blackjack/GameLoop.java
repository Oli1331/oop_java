package blackjack;

import java.util.Scanner;

public class GameLoop {

    int num_round = 1;
    int countDeck;
    Pile gamePile;
    Pile discardPile;
    Player player;
    Dealer dealer;

    GameLoop() {
        player = new Player();
        dealer = new Dealer();

    }

    void loop() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Добро пожаловать в Блэкджек! \n Сколько колод будут в игре? ");
        countDeck = scan.nextInt();
        gamePile = new Pile(countDeck);
        discardPile = new Pile(countDeck);
        gamePile.fill();

        while (true) {
            player.reset(discardPile);
            dealer.reset(discardPile);
            System.out.println("\n\n\nРаунд " + num_round++ + "\nСчёт: Дилер=" + dealer.gamePoints + "\tИгрок="
                    + player.gamePoints);
            System.out.println("Введите “0”, чтобы выйти из игры. ");
            if (scan.nextInt() == 0)
                break;

            player.takeCart(getCard());
            player.takeCart(getCard());
            dealer.takeCart(getCard());
            dealer.takeCartAsSecret(getCard());
            System.out.println("Дилер раздал карты");
            player.printCard();
            dealer.printCard();
            if (player.haveBlackjack()) {
                System.out.println("У вас Блэкджек! Вы выйграли.");
                player.gamePoints++;
                continue;
            }
            System.out.println("ВАШ ХОД\n*************");
            while (true) {
                System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться. ");
                if (scan.nextInt() == 1) {
                    Card c = getCard();
                    player.takeCart(c);
                    System.out.println("Вы вытащили " + c);
                    player.printCard();
                    if (player.sumCard >= 21)
                        break;
                } else
                    break;
            }
            if (player.sumCard > 21) {
                System.out.println("У вас сумма карт " + player.sumCard + ". Это больше 21. Вы проиграли.");
                dealer.gamePoints++;
                continue;
            }

            System.out.println("ХОД ДИЛЕРА\n*************\n Дилер открывает закрытую карту "
                    + dealer.card.getLast());
            dealer.openSecretCard();
            if (dealer.haveBlackjack()) {
                System.out.println("Дилер собрал Блэкджек! Вы проиграли.");
                dealer.gamePoints++;
                continue;
            }
            while (dealer.sumCard < 17) {
                Card c = getCard();
                dealer.takeCart(c);
                System.out.println("Дилер вытащил " + c);

            }
            if (dealer.sumCard > 21) {
                System.out.println("Вы выйграли. Дилер превысил сумму карт.");
                player.gamePoints++;
                continue;
            }

            player.printCard();
            dealer.printCard();

            if (player.sumCard > dealer.sumCard) {
                System.out.println("Вы выйграли. У вас больше сумма карт");
                player.gamePoints++;
            } else if (dealer.sumCard > player.sumCard) {
                System.out.println("Вы проиграли. У дилера сумма карт больше.");
                dealer.gamePoints++;
            } else {
                System.out.println("Ничья.");
            }

        }

        scan.close();

    }

    public Card getCard() {
        if (gamePile.empty()) {
            mixCard();
        }
        gamePile.countCard--;
        return gamePile.card[gamePile.countCard];

    }

    public void mixCard() {
        for (int i = 0; i < discardPile.countCard; i++) {
            gamePile.card[gamePile.countCard++] = discardPile.card[i];
        }
        discardPile.countCard = 0;
    }
}
