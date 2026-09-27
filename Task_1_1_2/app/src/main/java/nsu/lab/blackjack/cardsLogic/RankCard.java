package nsu.lab.blackjack.cardsLogic;

/**
 * Enumeration representing standard playing card ranks and values.
 */
public enum RankCard {
    TWO(2, "Двойка"), THREE(3, "Тройка"), FOUR(4, "Четвёрка"), FIVE(5, "Пятёрка"),
    SIX(6, "Шестёрка"), SEVEN(7, "Семёрка"), EIGHT(8, "Восьмёрка"), NINE(9, "Девятка"), TEN(10, "Десятка"),
    JACK(10, "Валет"), QUEEN(10, "Дама"), KING(10, "Король"), ACE(11, "Туз");

    private int value;
    private String name;

    /**
     * Constructs a rank enum constant.
     *
     * @param v numeric card value.
     * @param name Russian display name of the rank.
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
     * Returns the localized rank name.
     *
     * @return string representation of the name.
     */
    public String getName() {
        return name;
    }
}