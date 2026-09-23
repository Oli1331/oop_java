package nsu.lab.blackjack.cardsLogic;

public enum RankCard {
    TWO(2, "Двойка"), THREE(3, "Тройка"), FOUR(4, "Четвёрка"), FIVE(5, "Пятёрка"),
    SIX(6, "Шестёрка"), SEVEN(7, "Семёрка"), EIGHT(8, "Восьмёрка"), NINE(9, "Девятка"), TEN(10, "Десятка"),
    JACK(10, "Валет"), QUEEN(10, "Дама"), KING(10, "Король"), ACE(11, "Туз");

    private int value;
    private String name;

    RankCard(int v, String name) {
        this.name = name;
        value = v;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
