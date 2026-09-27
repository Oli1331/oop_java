package nsu.lab.blackjack.cardsLogic;

/**
 * Represents a single playing card with rank and suit.
 */
public class Card {

    private RankCard rank;
    private SuitCard suit;

    /**
     * Standard 52-card deck size constant.
     */
    public static int countCard = 52;

    /**
     * Constructs a playing card with specified rank and suit.
     *
     * @param r rank of the card.
     * @param s suit of the card.
     */
    public Card(RankCard r, SuitCard s) {
        this.rank = r;
        this.suit = s;
    }

    /**
     * Returns the numeric value of the card.
     *
     * @return card point value.
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Formats card to string representation.
     *
     * @return formatted string with name, symbol and value.
     */
    @Override
    public String toString() {
        return rank.getName() + " " + suit.getSymbol() + " (" + rank.getValue() + ")";
    }
}