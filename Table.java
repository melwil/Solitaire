package solitaire;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;

public class Table extends JPanel implements MouseMotionListener {
    
    JFrame frame;
    
    Deck deck = new Deck();
    Tablaa tablaa = new Tablaa(deck);
    Fundament fundament = new Fundament(); 
    
    MyListener myListener = new MyListener();
    Wireframe wireframe = new Wireframe();  
    
    Color backgroundColor = Color.GREEN.darker().darker();
    
    final Dimension size = new Dimension(630,530);
    String cardBack = "img/b2fv.png";
    
    int cX,cY,offsetX,offsetY,offset,offsetType;
    boolean gameOver = false;
    
    ArrayList<Card> remaining = new ArrayList<Card>();
    
    public Table() {
        createAndShowGUI();
    }
    private void createAndShowGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(size);
        frame.setPreferredSize(size);
        frame.setMaximumSize(size);
        frame.setTitle("Solitaire");
        JComponent newContentPane = this;
        frame.setContentPane(newContentPane);
        
        //... Making menu
        JMenuBar    menubar = new JMenuBar();
        JMenu       menu = new JMenu("Menu"),submenu = new JMenu("Card color");
        JMenuItem   newGameItem = new JMenuItem("New Game"); 
        JMenuItem   quitItem = new JMenuItem("Quit"); 
        JRadioButtonMenuItem rbbutton1,rbbutton2;
        
        //... Add listeners to menu items
        newGameItem.addActionListener(new NewGameAction());
        quitItem.addActionListener(new QuitAction());
        
        ButtonGroup group = new ButtonGroup();
        rbbutton1 = new JRadioButtonMenuItem("Red back");
        rbbutton1.setSelected(true);
        group.add(rbbutton1);
        rbbutton2 = new JRadioButtonMenuItem("Blue back");
        group.add(rbbutton2);
        
        rbbutton1.addActionListener(new CardColorRed());
        rbbutton2.addActionListener(new CardColorBlue());
        
        menubar.add(menu);       
            menu.add(newGameItem);
            menu.addSeparator(); 
            menu.add(submenu);
                submenu.add(rbbutton1);
                submenu.add(rbbutton2);
            menu.addSeparator(); 
            menu.add(quitItem);
                
        frame.setJMenuBar(menubar);  
        //... End make menu
               
        addMouseListener(myListener);
        addMouseMotionListener(this);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }
    
    @Override
    public void paintComponent(Graphics g) {
        remaining.clear();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, screensize().width, screensize().height);
        if (deck.size() > 0) { g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(cardBack)), 20, 20, this); }
        else {
            g.setColor(Color.BLACK);
            g.drawRect(20,20,72,96); 
        }
        if (deck.pileSize() > 0) {
            if (deck.showTopCard().isSelected()) {
                remaining.add(deck.showTopCard());
                offsetType = 1;
                if (deck.pileSize() > 1) { g.drawImage(deck.show2ndCard().getCardImage(cardBack), 105, 20, this); }
            }
            else {
                g.drawImage(deck.showTopCard().getCardImage(cardBack), 105, 20, this);
            }
        }
        int col = 0, coltemp = 0;
        for (Stack stack : fundament.getFundament()) {
            if (stack.size() != 0) {
                if (stack.getTopCard().isSelected()) {
                    remaining.add(stack.getTopCard());
                    offsetType = 2;
                    coltemp = col;
                    if (stack.size() > 1) { g.drawImage(stack.get2ndCard().getCardImage(cardBack), 275+(col*85), 20, this); }
                    else { g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/53.png")), 275+(col*85), 20, this); }
                }
                else { 
                    g.drawImage(stack.getTopCard().getCardImage(cardBack), 275+(col*85), 20, this);
                }
            }
            else {
                g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/53.png")), 275+(col*85), 20, this);
            }
            col++;
        }
        int column = 0,row,columntemp=0,rowtemp=0;
        for (Stack stack : tablaa.getTablaa()) {    
            row = 0;
            if (stack.size() == 0) {
                g.setColor(Color.BLACK);
                g.drawRect(20+(column*85), 130+(row*20), 72, 96); 
            }
            for (Card card : stack.getStack()) {
                if (card.isSelected()) { 
                    rowtemp = row;
                    columntemp = column;
                    offset = 1; 
                    offsetType = 3;
                }
                if (offset == 1) {
                    remaining.add(card);
                    if (rowtemp == 0) {
                        g.setColor(Color.BLACK);
                        g.drawRect(20+(column*85), 130+(row*20), 72, 96); 
                    }
                    continue;
                }
                g.drawImage(card.getCardImage(cardBack), 20+(column*85), 130+(row*20), this);
                row++;
            }
            offset = 0;
            column++;
        }
        for (Card card : remaining) {
            if (offsetType == 1) { g.drawImage(deck.showTopCard().getCardImage(cardBack), 105+offsetX, 20+offsetY, this); }
            if (offsetType == 2) { g.drawImage(card.getCardImage(cardBack), 275+(coltemp*85)+offsetX, 20+offsetY, this); }
            if (offsetType == 3) { g.drawImage(card.getCardImage(cardBack), 20+(columntemp*85)+(offsetX), 130+(rowtemp*20)+(offsetY), this); }
            rowtemp++;
        }
        if (gameOver) {
            Random r = new Random();
            int x,y,vx,vy,i=0;
            for (Stack s : fundament.getFundament()) {  
                for (Card c : s.getStack()) {
                    x = 275+(i*85);
                    y = 20;
                    vx = r.nextInt(12)-6;
                    vy = r.nextInt(10);
                    while (x > 0 && x < screensize().width) {
                        x = x = vx;
                                g.drawImage(c.getCardImage(cardBack), x, y, this);
                    }
                }
                i++;
            }
        }
    }

    private Dimension screensize() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    }

    class CardColorRed implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cardBack = "img/b2fv.png";
            repaint();
        }
    }
    class CardColorBlue implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cardBack = "img/b1fv.png";
            repaint();
        }
    }
    class NewGameAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            newGame();
        }
    }
    class QuitAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int i = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "confirm quit", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                System.exit(0);  // terminate this program
            }
        }
    }
    public void mouseDragged(MouseEvent e) {
        offsetX = e.getX() - cX;
        offsetY = e.getY() - cY;
        repaint();
    }

    public void mouseMoved(MouseEvent arg0) { }

    private class MyListener extends MouseInputAdapter {

        Card selectedCard;

        int selectedStack,selectedNumber,selectedCardOrigin;
        @Override
        public void mousePressed(MouseEvent e) {
            int stack = stackHit(e);
            if (stack < 14) {
                cX = e.getX();
                cY = e.getY();
                if (stack == 1) {
                    if (deck.size() > 0) {
                        deck.toPile();
                    }
                    else {
                        deck.flip();
                    }
                }
                if (stack == 2) {
                    selectedCard = deck.showTopCard();
                    selectedCard.setSelected(true);
                    selectedCardOrigin = 1;
                }
                if (stack < 7 && stack > 2) {
                    if (fundament.size(stack) > 0) {
                        selectedCard = fundament.showTopCard(stack);
                        selectedCard.setSelected(true);
                        selectedStack = stack;
                        selectedCardOrigin = 2;
                    }
                }
                if (stack < 14 && stack > 6) {
                    selectedCard = findClickedCard(e.getY(),stack);
                    selectedStack = stack;
                    selectedCardOrigin = 3;
                    if (selectedCard != null) {
                        if (selectedCard.isFaceUp()) { selectedCard.setSelected(true); }
                        else {
                            if (selectedNumber == tablaa.getStack(stack-6).size() -1) {
                                selectedCard.setFaceUp();
                            }
                        }
                    }
                }
            }
            repaint();
            if (fundament.checkGameOver()) {
                gameOver();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedCard != null) {
                selectedCard.setSelected(false);
                offsetX = 0;
                offsetY = 0;
                //System.out.println("Mouse released at: " + e.getX() + "," + e.getY());
                int stack = stackHit(e);
                if (stack > 2 && stack < 7) {
                    if (remaining.size() > 1) { }
                    else {
                        if (fundament.size(stack) == 0) {
                            if (selectedCard.getFace() == 1) {
                                fundament.putCard(stack, selectedCard);
                                removeCard(selectedCard);
                            }
                        }
                        else {
                            if (fundament.showTopCard(stack).collide(selectedCard,stack)) {
                                fundament.putCard(stack, selectedCard);
                                removeCard(selectedCard);
                            }
                        }
                    }
                }
                if (stack < 14 && stack > 6) {
                    if (tablaa.getStack(stack-6).size() == 0) {
                        if (selectedCard.getFace() == 13) {
                            for (Card card : remaining) {
                                tablaa.putCard(stack-6, card);
                                removeCard(card);
                            }
                        }
                    }
                    else {
                        Card destinationCard = findClickedCard(e.getY(),stack);
                        if (destinationCard != null) {
                            if (destinationCard.collide(selectedCard,stack)) {
                                for (Card card : remaining) {
                                    tablaa.putCard(stack-6, card);
                                    removeCard(card);
                                }
                            }
                        }
                    }
                }
                repaint();
                if (fundament.checkGameOver()) {
                    gameOver();
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2){
                int stack = stackHit(e);
                Card clickedCard;
                if (stack == 2 || (stack < 14 && stack > 6)) {
                    if (stack == 2) {
                        clickedCard = deck.showTopCard();
                        selectedCardOrigin = 1;
                    }
                    else {
                        clickedCard = findClickedCard(e.getY(),stack);
                        selectedCardOrigin = 3;
                    }
                    if (clickedCard != null) {
                        int i = 2;
                        for (Stack s : fundament.getFundament()) {
                            i++;
                            Card topCard = s.getTopCard();
                            if (topCard == null) {
                                if (clickedCard.getFace() == 1) {
                                    s.add(clickedCard);
                                    removeCard(clickedCard);
                                    break;
                                }
                            }
                            else {
                                if (topCard.collide(clickedCard,i)) {
                                    s.add(clickedCard);
                                    removeCard(clickedCard);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (fundament.checkGameOver()) {
                gameOver();
            }
        }

        private void removeCard(Card card) {
            switch (selectedCardOrigin) {
                case 1: {
                    deck.removeCard(card);
                    break;
                }
                case 2: {
                    fundament.removeCard(selectedStack-3, card);
                    break;
                }
                case 3: {
                    tablaa.removeCard(selectedStack-6, card);
                    break;
                }
            }
        }

        private int stackHit(MouseEvent e) {
            int ret = 1;
            for (Shape s : wireframe.getWireFrame()) {
                if (s.contains(e.getPoint())) {
                    return ret;
                }
                ret++;
            }
            return ret;
        }

        private Card findClickedCard(int y, int stack) {
            stack = stack - 6;
            int i = 0;
            y = y - 130;
            for (Card c : tablaa.getStack(stack)) {
                if ((y > (i * 20)) && (y < ((i+1)*20))) {
                    selectedNumber = i;
                    return c;
                }
                if (i+1 == tablaa.getStack(stack).size()) {
                    if ((y > (i * 20)) && (y < ((i*20)+96))) {
                        selectedNumber = i;
                        return c;
                    }
                }
                i++;
            }
            return null;
        }

    }

    public void gameOver() {
        gameOver = true;
        repaint();
        int i = JOptionPane.showConfirmDialog(frame, "You have completed Håvards Epic Solitaire\nPlay Again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            newGame();           // starts a new game
        }
        if (i == JOptionPane.NO_OPTION) {
            System.exit(0);     // terminate this program
        }
    }

    public void newGame() {
        deck = new Deck();
        tablaa = new Tablaa(deck);
        fundament = new Fundament();
        gameOver = false;
        repaint();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Table();
            }
        });
    }

}