package nsu.lab.blackjack.entity;

public class Player extends Participant {

    @Override
    public void printCard() {
        System.out.print("Ваши карты: {");
        for (int i = 0; i < getCountCard(); i++) {
            System.out.print(getCard(i) + "; ");
        }
        System.out.println("} == " + getSumCard());

    }
}