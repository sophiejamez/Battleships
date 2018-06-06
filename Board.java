//The Board class has a lot of final ints that hold the dimensions of the board itself. 
//Methods make the board visible, a smaller version of your own board so you remember 
//where your ships are, and methods to place markers if a ship was or wasn't hit. Ship,
//Location, Computer, and Game all use this class. 

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JFrame{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public static final int TOP = 150;
    public static final int SIDE = 60;
    public static final int LEFT = 20;
    public static final int THICK = 2;
    public static final int MINI_TOP = 10;
    public static final int MINI_SIDE = 20;
    public static final int MINI_LEFT = 790;
    public static final int MINI_THICK = 1;

    private ArrayList<Ship> computer = new ArrayList<Ship>();
    private ArrayList<Marker> markers = new ArrayList<Marker>();
    
    private boolean mini;

    // constructor
    public Board(){
        mini = false;
    }
    
    // constructor
    public Board(boolean size){
        mini = size;
    }

    // draws the grid on the screen
    public void draw( Graphics page ){
        page.setColor( Color.BLACK );//color defined using rgb values (0-255 each)
        for(int r = 0; r <= NUM_ROWS; r ++)
            page.fillRect(LEFT, TOP+SIDE*r, NUM_COLS * SIDE, THICK);
        for(int c = 0; c <= NUM_COLS; c++)
            page.fillRect(LEFT+SIDE*c, TOP, THICK, NUM_ROWS * SIDE);
    }
    
    // draws the page
    public void drawGame( Graphics page){
        this.draw(page);
        if(mini)
            for(Marker next : markers)
                next.drawMini(page);
        else
            for(Marker next : markers)
                next.draw(page);
    }
    
    // draws mini version of game in the corner
        public void drawMini(Graphics page){
        page.setColor( Color.BLACK );//color defined using rgb values (0-255 each)
        for(int r = 0; r <= NUM_ROWS; r ++)
            page.fillRect(MINI_LEFT, MINI_TOP+MINI_SIDE*r, NUM_COLS * MINI_SIDE, MINI_THICK);
        for(int c = 0; c <= NUM_COLS; c++)
            page.fillRect(MINI_LEFT+MINI_SIDE*c, MINI_TOP, MINI_THICK, NUM_ROWS * MINI_SIDE);
    }

    // draws a red circle to represent a hit
    public void placeHit(Location loc)
    {
        markers.add(new Marker(true, loc));
    }
    
    // draws a white circle to represent a miss
    public void placeMiss(Location loc)
    {
        markers.add(new Marker(false, loc));
    }
}