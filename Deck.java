package solitaire;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> pile = new ArrayList<Card>();
    private Random r = new Random();
    private int rand;
    private boolean flipped = false;
    
    Deck() {
        for (Card c : Card.values()) {
            deck.add(c);
        }
        rand = r.nextInt(deck.size());
        toPile();
    }
    
    public Card getCard() {
        if (flipped) {
            Card temp = deck.get(0);
            deck.remove(0);
            return temp;
        }
        else {
            Card temp = deck.get(rand);
            deck.remove(rand);
            if (deck.size() > 1) { rand = r.nextInt(deck.size()); }
            else { rand = 0; }
            temp.setFaceUp();
            return temp;
        }
    }
    
    public void removeCard(Card c) {
        pile.remove(c);
    }
    
    public void toPile() {
        pile.add(getCard());
    }
    
    public int pileSize() {
        return pile.size();
    }
    
    public Card showTopCard() {
        return pile.get(pile.size()-1);
    }
    
    public Card show2ndCard() {
        return pile.get(pile.size()-2);
    }
    
    public int size() {
        return deck.size();
    }
    
    public void flip() {
        for (Card c : pile) {
            deck.add(c);
        }
        pile.clear();
        flipped = true;
    }
    
}
