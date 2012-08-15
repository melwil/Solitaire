package solitaire;

import java.util.ArrayList;

public class Stack {
    
    private ArrayList<Card> stack = new ArrayList<Card>();
        
    Stack(Deck d,int i) {
        Card c;
        for (int j=0;j<i;j++) {
            c = d.getCard();
            if (j < i-1) { c.setFaceDown(); }
            stack.add(c);
        }
    }
    
    Stack () { }
    
    public void add(Card c) {
        stack.add(c);
    }
    
    public void remove(Card c) {
        stack.remove(c);
    }
    
    public Card get(int i) {
        return stack.get(i);
    }
    
    public int size() {
        return stack.size();
    }
    
    public Card getTopCard() {
        if (stack.size() == 0) {
            return null;
        }
        else { 
            return stack.get(stack.size()-1);
        }
    }
    
    public Card get2ndCard() {
        return stack.get(stack.size()-2);
    }    
    
    public ArrayList<Card> getStack() {
        return stack;
    }
    
}
