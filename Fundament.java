package solitaire;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Fundament {
    
    private ArrayList<Stack> fundament = new ArrayList<Stack>();
    private boolean game = false;
    
    Fundament() {
        for (int i = 0; i < 4; i++) {
            fundament.add(new Stack());
        }
    }
    
    public boolean checkGameOver() {
        
        game = true;
        
        for (Stack s : fundament) {
            if (s.size() != 13) {
                game = false;
            }
        }
        
        return game;
        
    }
    
    
    public ArrayList<Stack> getFundament() {
        return fundament;
        /*int i = 0;
        for (Stack stack : fundament) {
            if (stack.size() == 0) {
                image[i] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/53.png"));
            }
            else {
                image[i] = stack.get(stack.size()-1).getCardImage();
            }
            i++;
        }
        return image;*/
    }
    
    public Card showTopCard(int stack) {
        stack = stack - 3;
        Stack s = fundament.get(stack);
        if (s.size() == 0) {
            return null;
        }
        else { 
            return s.get(s.size()-1);
        }
    }
      
    public int size(int stack) {
        stack = stack - 3;
        return fundament.get(stack).size();
    }
    
    public void putCard(int stack, Card c) {
        stack = stack - 3;
        fundament.get(stack).add(c);
    }
    
    public void removeCard(int stack, Card c) {
        fundament.get(stack).remove(c);
    }
}
