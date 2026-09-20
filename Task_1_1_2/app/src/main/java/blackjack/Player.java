package blackjack;

public class Player extends Participant {

    @Override
    public void printCard() {
        System.out.print("Ваши карты: {");
        for (int i = 0; i < card.size(); i++) {
            System.out.print(card.get(i) + "; ");
        }
        System.out.println("} == " + sumCard);

    }
}