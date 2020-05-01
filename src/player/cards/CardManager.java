package player.cards;

import java.util.ArrayList;

public class CardManager {

    private ArrayList<AbstractCard> activeCards;

    public CardManager() {
        this.activeCards = new ArrayList<>();
    }

    public void activate(AbstractCard card) {
        activeCards.add(card);
    }

    public void deactivate(AbstractCard card) {
        activeCards.remove(card);
    }
}
