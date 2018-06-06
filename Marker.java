//The Marker class places a red circle if the spot guessed is a hit and white if it is not. 
//It them makes these graphics visible on the screen. Computer and Board use this class. 

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Marker
{
    private Color col;
    Location loc;
    public static final int SIZE = 20;
    
    //constructor
    public Marker(boolean hit, int x, int y){
        if(hit)
            col = Color.RED;
        else
            col = Color.WHITE;
        loc = new Location(x,y);
    }
    
    //constructor
    public Marker(boolean hit, Location next){
        if(hit)
            col = Color.RED;
        else
            col = Color.WHITE;
        loc = next;
    }

    //draws the page
    public void draw( Graphics page )
    {
        page.setColor(col);
        page.fillOval(loc.getXVal(), loc.getYVal(), SIZE, SIZE);
    }
    
    //draws the mini version in the corner
    public void drawMini(Graphics page){
        page.setColor(col);
        page.fillOval(Board.MINI_LEFT + 7 + Location.getColVal(loc.getGridX())*Board.MINI_SIDE, Board.MINI_TOP + loc.getGridY()*Board.MINI_SIDE + 7, 5, 5); 
    }
}