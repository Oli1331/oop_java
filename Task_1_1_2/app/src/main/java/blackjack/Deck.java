package blackjack;

import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    int countCard = 52;
    Card[] card = new Card[countCard];

    Deck() {
        int ind = 0;
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                this.card[ind] = new Card(r, s);
            }
        }
        for (int i = 0; i < countCard; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 52);
            int j = (randomNum + i) % countCard;
            Card tmp = this.card[randomNum];
            this.card[randomNum] = this.card[j];
            this.card[j] = tmp;
        }
    }

    Card get_card() {
        this.countCard--;
        return this.card[countCard];
    }

}
