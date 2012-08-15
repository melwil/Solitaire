package solitaire;

import java.util.ArrayList;

public class Tablaa {
    
    private ArrayList<Stack> stacks = new ArrayList<Stack>();
    
    Tablaa(Deck d) {
        for (int i = 1; i<=7;i++) {
            stacks.add(new Stack(d,i));
        }
    }
        
    public ArrayList<Stack> getTablaa() {
        return stacks;
    }
    
    public ArrayList<Card> getStack(int i) {
        return stacks.get(i-1).getStack();
    }
    
    public void putCard(int stack, Card c) {
        stack--;
        stacks.get(stack).add(c);
    }
    
    public void removeCard(int stack, Card c) {
        stack--;
        stacks.get(stack).remove(c);
    }
    
}
