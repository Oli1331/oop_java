package nsu.lab.blackjack.cardsLogic;

public class Card {

    private RankCard rank;
    private SuitCard suit;
    public static int countCard = 52;

    Card(RankCard r, SuitCard s) {
        this.rank = r;
        this.suit = s;
    }

    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return rank.getName() + " " + suit.getSymbol() + " (" + rank.getValue() + ")";
    }
}
