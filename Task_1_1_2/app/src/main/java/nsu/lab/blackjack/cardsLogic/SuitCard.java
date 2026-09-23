package nsu.lab.blackjack.cardsLogic;

public enum SuitCard {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    private String symbol;

    SuitCard(String s) {
        symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }
}