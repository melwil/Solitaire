package solitaire;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

public class Wireframe {
    
    ArrayList<Shape> wireFrame = new ArrayList<Shape>();
    
    Wireframe() {
        wireFrame.add(new Rectangle(20,20,72,96));
        wireFrame.add(new Rectangle(105,20,72,96));
        wireFrame.add(new Rectangle(275,20,72,96));
        wireFrame.add(new Rectangle(360,20,72,96));
        wireFrame.add(new Rectangle(445,20,72,96));
        wireFrame.add(new Rectangle(530,20,72,96));
        wireFrame.add(new Rectangle(20,130,72,400));
        wireFrame.add(new Rectangle(105,130,72,400));
        wireFrame.add(new Rectangle(190,130,72,400));
        wireFrame.add(new Rectangle(275,130,72,400));
        wireFrame.add(new Rectangle(360,130,72,400));
        wireFrame.add(new Rectangle(445,130,72,400));
        wireFrame.add(new Rectangle(530,130,72,400));
    }
    
    public ArrayList<Shape> getWireFrame() {
        return wireFrame;
    }
    
}
