package nsu.lab.blackjack;

import nsu.lab.blackjack.gameCore.GameEngine;

public class Main {
    public static void main(String[] args) {

        int countDeck;
        if (args.length == 0)
            countDeck = 1;
        else {
            try {
                int value = Integer.parseInt(args[0]);
                if (value < 0) {
                    countDeck = 1;
                } else if (value > 100) {
                    countDeck = 100;
                } else {
                    countDeck = value;
                }
            } catch (NumberFormatException e) {
                countDeck = 1;
            }
        }

        GameEngine game = new GameEngine(countDeck);

        while (game.getNumRound() < 21 && game.startRound()) {

            game.dealCard();

            game.playerTurn();

            game.dealerTurn();

            game.checkResults();
        }
    }
}
