package solitaire;

import java.awt.Image;
import java.awt.Toolkit;

public enum Card {
    H1 ("hearts", 1), H2 ("hearts", 2), H3 ("hearts", 3), H4 ("hearts", 4), H5 ("hearts", 5), H6 ("hearts", 6), H7 ("hearts", 7), H8 ("hearts", 8), H9 ("hearts", 9), H10 ("hearts", 10), H11 ("hearts", 11), H12 ("hearts", 12), H13 ("hearts", 13),
    S1 ("spades", 1), S2 ("spades", 2), S3 ("spades", 3), S4 ("spades", 4), S5 ("spades", 5), S6 ("spades", 6), S7 ("spades", 7), S8 ("spades", 8), S9 ("spades", 9), S10 ("spades", 10), S11 ("spades", 11), S12 ("spades", 12), S13 ("spades", 13),
    C1 ("clubs", 1), C2 ("clubs", 2), C3 ("clubs", 3), C4 ("clubs", 4), C5 ("clubs", 5), C6 ("clubs", 6), C7 ("clubs", 7), C8 ("clubs", 8), C9 ("clubs", 9), C10 ("clubs", 10), C11 ("clubs", 11), C12 ("clubs", 12), C13 ("clubs", 13),   
    D1 ("diamonds", 1), D2 ("diamonds", 2), D3 ("diamonds", 3), D4 ("diamonds", 4), D5 ("diamonds", 5), D6 ("diamonds", 6), D7 ("diamonds", 7), D8 ("diamonds", 8), D9 ("diamonds", 9), D10 ("diamonds", 10), D11 ("diamonds", 11), D12 ("diamonds", 12), D13 ("diamonds", 13);
  
    private final String suit;
    private final int face;
    private final Image image;
    private boolean faceUp = true;
    private boolean selected = false;
    
    Card(String suit, int face) {
        this.face = face;
        this.suit = suit;
        this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(getCardName()));
    }
    
    public int getFace() {
        return face;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public Image getCardImage(String color) {
        if (faceUp) {
            return image;
        }
        else {
            return Toolkit.getDefaultToolkit().getImage(getClass().getResource(color));
        }
    }
    
    public String getCardName() {
        return String.valueOf("img/" + suit.charAt(0)) + face + ".png";
    }
    
    public void setFaceDown() {
        faceUp = false;
    }
    
    public void setFaceUp() {
        faceUp = true;
    }
    
    public boolean isFaceUp() {
        return faceUp;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean b) {
        selected = b;
    }
    
    public boolean isColorEqual(Card c) {
        if (c.getSuit().equals("hearts") || c.getSuit().equals("diamonds")) {
            if (suit.equals("spades") || suit.equals("clubs")) { return false; }
            else { return true; }
        }
        else {
            if (suit.equals("hearts") || suit.equals("diamonds")) { return false; }
            else { return true; }
        }
    }
    
    public boolean collide(Card c, int stack) {
        if (stack < 7 && stack > 2) { 
            if (c.getSuit().equals(this.getSuit()) && (c.getFace() == this.getFace()+1)) {
                return true;
            }
            else { return false; }
        }
        if (stack < 14 && stack > 6) { 
            if (!c.isColorEqual(this) && (c.getFace() == this.getFace()-1)) {
                return true;
            }
            else { return false; }
        }
        return false;
    }
    
}
