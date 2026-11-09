package nsu.lab.blackjack.cards;

/** Enumeration representing standard playing card ranks and values. */
public enum RankCard {
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(10, "Jack"),
    QUEEN(10, "Queen"),
    KING(10, "King"),
    ACE(11, "Ace");

    private final int value;
    private final String name;

    /**
     * Constructs a rank enum constant.
     *
     * @param v numeric card value.
     * @param name display name of the rank.
     */
    RankCard(int v, String name) {
        this.name = name;
        value = v;
    }

    /**
     * Returns the numeric value of the rank.
     *
     * @return the card point value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the display name of the rank.
     *
     * @return string representation of the name.
     */
    public String getName() {
        return name;
    }
}
