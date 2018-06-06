//This class creates ships of five different lengths with locations. Methods included in 
//this class allow for the setting and changing of locations, moves the ships, and makes
//them visible on the screen. There are private ints for length and the direction a ship
//is facing, a private boolean that determines whether a ship is selected, and a final int
//for the border around ships when they "snap to" their place on the board. Board, Location,
//and Player all use this class. 

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Ship{
    private int length;
    private boolean selected = false;
    
    public static final int BORDER = 3;
    
    private Location loc;
    
    //player's ship constructor
    public Ship(int l){
        length = l; 
        setStartLoc();
    }
    
    // pre: ship has a location on the grid
    // post: calls location to center ship
    public Ship(){
        length=0;
    }
    
    public void snapLoc(){
        loc.snapTo();
    }
    
    // pre: ship has a length > 0
    // post: sets initial location for each ship
    public void setStartLoc(){
        if(length == 2 || length == 5){
            loc = new Location(20+(50*length)+200*(length%2),20, length);
        }
        else{
            int y = 80;
            if(length!=1)
                loc = new Location(20+300*(length%2+1), y, length);
            else
                loc = new Location(50, y, length);
        }
    }
    
    // pre: length > 0
    // post: gets length of ship
    public int getLength(){
        return length;
    }
    
    // pre: length > 0   
    // post: gets location of first x value of ship
    public Location getLoc(){
        return loc;
    }
    
    // pre: ship has a location
    // post: finds which ship person clicked and where it is
    public boolean overlapsWith(Location next){
        if(loc.locEquals(next)){
            selected = true;
            return true;
        }
        return false;
    }
    
    // pre: a ship is selected
    // post: moves ship to the place where person or computer clicked
    public void move(int x, int y){
        loc = new Location(x,y);
        selected = false;
    }
    
     // pre: none
    // post: if the person clicked on the ship, the ship is now selected
    public void changeState(){
        if(selected)
            selected = false;
        else
            selected = true;
    }
    
    // pre: none
    // post: if you select the ship, outlines selected ship in yellow
    public void draw( Graphics page ){
        if(loc.checkBounds())
            loc.snapTo();
        
        if (selected){
            page.setColor(Color.YELLOW);
            page.fillRect(loc.getXVal() - BORDER, loc.getYVal() - BORDER, 60*length -14, 36);
        }
        
        page.setColor(new Color(100,100,100));
        page.fillRect(loc.getXVal(), loc.getYVal(), 60*length -20, 30);
        page.setColor( new Color( 255, 255, 255 ) );
        for(int i = 0; i < length; i ++)
            page.fillOval(loc.getXVal() + (60*i) + 20, loc.getYVal() + 10, 7, 7);
    }
    
    // pre: none
    // post: draws mini ship in the top right corner
    public void drawMini(Graphics page){
        page.setColor(new Color(100,100,100));
        int miniX = Location.getColVal(loc.getGridX());
        int miniY = loc.getGridY(); 
        page.fillRect(Board.MINI_LEFT + miniX * Board.MINI_SIDE + 5, Board.MINI_TOP + miniY * Board.MINI_SIDE + 5, Board.MINI_SIDE*length-6, 10);
        page.setColor( new Color( 255, 255, 255 ) );
        for(int i = 0; i < length; i ++)
            page.fillOval(Board.MINI_LEFT + miniX * Board.MINI_SIDE + (Board.MINI_SIDE*i) + 9, Board.MINI_TOP + miniY * Board.MINI_SIDE +9, 2, 2);
    }
}