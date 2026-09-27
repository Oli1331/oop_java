package nsu.lab.blackjack.entity;

/**
 * Represents human player participant.
 */
public class Player extends Participant {

    /**
     * Outputs player's cards and score to stdout.
     */
    @Override
    public void printCard() {
        System.out.print("Ваши карты: {");
        for (int i = 0; i < getCountCard(); i++) {
            System.out.print(getCard(i) + "; ");
        }
        System.out.println("} == " + getSumCard());
    }
}