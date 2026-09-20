package blackjack;

public class Card {
    public enum Rank {
        TWO(2, "Двойка"), THREE(3, "Тройка"), FOUR(4, "Четвёрка"), FIVE(5, "Пятёрка"),
        SIX(6, "Шестёрка"), SEVEN(7, "Семёрка"), EIGHT(8, "Восьмёрка"), NINE(9, "Девятка"), TEN(10, "Десятка"),
        JACK(10, "Валет"), QUEEN(10, "Дама"), KING(10, "Король"), ACE(11, "Туз");

        int value;
        String name;

        Rank(int v, String name) {
            this.value = v;
            this.name = name;
        }
    }

    public enum Suit {
        HEARTS("♥"),
        DIAMONDS("♦"),
        CLUBS("♣"),
        SPADES("♠");

        String sym;

        Suit(String s) {
            this.sym = s;
        }
    }

    Rank rank;
    Suit suit;
    static int countCard = 52;

    Card(Rank r, Suit s) {
        this.rank = r;
        this.suit = s;
    }

    @Override
    public String toString() {
        return rank.name +" "+ suit.sym + " (" + rank.value + ")";
    }
}
