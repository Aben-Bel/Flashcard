package app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Flashcard {
    private List<Deck> deckList;

    public Flashcard(){
        this.deckList = new ArrayList<>();

        Deck oneDeck = new Deck("one");

        Card oneCard = new Card("Life", "is for the living");
        Card twoCard = new Card("Love", "is a ladder to heaven");
        Card threeCard = new Card("Lie", "takes away everything");
        Card fourCard = new Card("Death", "is just the beginning?");

        oneDeck.setCardList(new ArrayList<Card>(Arrays.asList(oneCard, twoCard, threeCard, fourCard)));

        Deck twoDeck = new Deck("two");

        Card oneCard1 = new Card("Hello", "from the other side");
        Card twoCard1 = new Card("Good morning", "to you too");
        Card threeCard1 = new Card("Fall", "asleep never to touch");
        Card fourCard1 = new Card("You", "only need to let her go");

        twoDeck.setCardList(new ArrayList<Card>(Arrays.asList(oneCard1, twoCard1, threeCard1, fourCard1)));

        Deck threeDeck = new Deck("three");

        Card oneCard2 = new Card("Good", "bad");
        Card twoCard2 = new Card("Best", "worst");
        Card threeCard2 = new Card("Leave", "return");
        Card fourCard2 = new Card("Give", "take");

        threeDeck.setCardList(new ArrayList<Card>(Arrays.asList(oneCard2, twoCard2, threeCard2, fourCard2)));

        Deck fourDeck = new Deck("four");

        Card oneCard3 = new Card("No Pain", "No gain");
        Card twoCard3 = new Card("A", "B");
        Card threeCard3 = new Card("Two", "goes with three");
        Card fourCard3 = new Card("Final", "is never final");

        fourDeck.setCardList(new ArrayList<Card>(Arrays.asList(oneCard3, twoCard3, threeCard3, fourCard3)));

        this.deckList.addAll(Arrays.asList(oneDeck, twoDeck, threeDeck, fourDeck));
    }

    public List<Deck> getDeckList() {
        return deckList;
    }
    public void addDeck(Deck d){
        getDeckList().add(d);
    }

    public Deck getDeck(String title){
        for(Deck d : deckList){
            if(title.equalsIgnoreCase(d.getTitle())){
                return d;
            }
        }
        return null;
    }

    public void remove(Deck deck) {
        deckList.remove(deck);
    }
}
