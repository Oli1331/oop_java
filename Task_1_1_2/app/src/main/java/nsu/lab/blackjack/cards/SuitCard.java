package nsu.lab.blackjack.cards;

/**
 * Enumeration representing playing card suits.
 */
public enum SuitCard {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    private String symbol;

    /**
     * Constructs a suit enum constant.
     *
     * @param s unicode symbol character.
     */
    SuitCard(String s) {
        symbol = s;
    }

    /**
     * Returns the suit symbol.
     *
     * @return string suit icon.
     */
    public String getSymbol() {
        return symbol;
    }
}