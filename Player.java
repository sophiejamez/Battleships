//The Player class creates an array of Ships for both the player and computer. It checks if 
//ships are in bounds, it creates an array of Locations of all the ships, and it snaps a ship
//to the center of the row it is placed in. Game uses this class, and Computer extends it.

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Player{
    public static final int NUMSHIPS = 5;
    
    private Ship[] ships;
    private Ship clicked;
    private ArrayList<Location> locs;
    private int numSunk;
    private int[] numGuess;
    private int lastHit;

    //constructor
    public Player(){
        ships = new Ship[NUMSHIPS];
        clicked = new Ship();
        for(int i = 0; i < ships.length; i ++){
            ships[i] = new Ship(i+1); 
        }
        locs = new ArrayList<Location>();
        numGuess = new int[NUMSHIPS];
        numSunk = 0; 
        lastHit = -1;
    }
    
    //returns whether all opponent's ships have been sunk
    public boolean won(){
        if(numSunk == NUMSHIPS)
            return true;
        return false;
    }
    
    //creates location array list that hols locations of all ships
    public void createLocs(){
        locs = new ArrayList<Location>();
        for(Ship next : ships){
            if(next.getLoc().checkBounds()){
                String x = next.getLoc().getGridX();
                for(int i = 0; i < next.getLength(); i ++){
                    locs.add(new Location(x, next.getLoc().getGridY()));
                    x = Location.increment(x);
                }
            }
        }
    }
    
    //returns ship array
    public Ship[] getShips(){
        return ships;
    }
    
    //returns whether ship is in or out of bounds
    public boolean outOfBounds(){
        for(Ship next : ships)
            if(!next.getLoc().checkBounds())
                return true;
        return false;
    }

    //returns ship object from array of ships
    public Ship getShip(int i){
        return ships[i];
    }
    
    //returns the point where bomb hit a ship, -1 if no ship is hit
    public int bombHit(Location next){
        if(pointOverlapPlayer(next)!=-1)
            return pointOverlapPlayer(next);
        return -1;
    }
    
    //snaps ship to middle of location
    public void snapTo(){
        for(Ship next : ships)
            next.snapLoc();
    }
    
    //returns array list of locations
    public ArrayList<Location> getLocs(){
        return locs;
    }

    //draws ships
    public void draw( Graphics page ){
        //color defined using rgb values (0-255 each)
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].draw(page);
        }
    }

    //draws a mini graphic of player's ships
    public void drawMini(Graphics page){
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].drawMini(page);
        }
    }

    //returns whether player can move
    public boolean move(int x, int y){
        if(!shipOverlap(new Location(x,y), clicked.getLength())){
            clicked.move(x,y);
            return false;
        }
        return true;
    }

    //makes the player act
    public void act(Location next){
        boolean overlaps = false;
        int num = 0;

        while(!overlaps && num < 5){
            overlaps = ships[num].overlapsWith(next);
            num++;
        }
        if(overlaps)
            clicked = ships[num-1];
    }
    
    //returns whether ship being placed is overlapping with ships already on the board
    public boolean shipOverlap(Location next, int length){
        if(!next.checkBounds())
            return false;
        createLocs();
        String x = next.getGridX();
        int y = next.getGridY();
        int k = 0;
        while(k < length){
            for(Location check : locs)
                if(check.locEquals(new Location(x,y))){
                    return true;
                }
            k++;
            x = Location.increment(x);
        }
        return false;
    }

    //returns int that indicates the point at which two locations overlap
    public int pointOverlapPlayer(Location next){
        if(!next.checkBounds())
            return -1;
            for(int i = 0; i < ships.length; i++){
            String storedX = ships[i].getLoc().getGridX();
            int storedY = ships[i].getLoc().getGridY();
            if(next.getGridY() == storedY){
                for(int check = 0; check < ships[i].getLength(); check++){
                    if(next.getGridX().equals(storedX)){
                        return i;
                    }
                    storedX = Location.increment(storedX);
                } 
            }
        }
        return -1;
    }
    
    //returns whether ships has been sunk
    public boolean sunk(){
        if(numGuess[lastHit] == lastHit+1)
            return true;
        return false;
    }
    
    //updates how many times ships has been hit
    public void hitUpdate(int i){
        numGuess[i] ++;
        lastHit = i;
        if(numGuess[i] == i+1)
            numSunk ++;
    }
}