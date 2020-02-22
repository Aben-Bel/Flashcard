package app.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String title;
    private List<Card> cardList = new ArrayList<>();

    public Deck(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
    public void addCard(Card cd){ cardList.add(cd); }
}
