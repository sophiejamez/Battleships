//done

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Computer extends Player{
    private ArrayList<Marker> computerMarkers = new ArrayList<Marker>();
    private Location lastLoc;
    private boolean found;
    private Ship[] playerShips;
    private ArrayList<Location> guesses;
    private boolean initialed = false;
    private boolean lookBehind = false;
    private Location behind;

    public Computer(){
        super();
        randomize();
        found = false;
        playerShips = new Ship[NUMSHIPS];
        guesses = new ArrayList<Location>();
        lastLoc = getRandomGuess();
        initialed = true;
        behind = new Location("", -1);
    }

    public void setBehind(Location l){
        behind = new Location(Location.decrement(l.getGridX()), l.getGridY());
    }
    
    public void intelliGuess(){
        Location save = lastLoc;
        
        Location forward = new Location(Location.increment(save.getGridX()), save.getGridY());
        Location backward = new Location(Location.decrement(save.getGridX()), save.getGridY());
        
        if(lookBehind){
            lastLoc = behind;
            lookBehind = false;
        }
        else if(!forward.checkBounds() || alreadyGuessed(forward))
            lastLoc = backward;
        else
            lastLoc = forward;
        guesses.add(lastLoc);
    }

    public void setLookBehind(boolean b){
        lookBehind = b;
    }

    public void setFound(boolean f){
        found = f;
    }

    public void setPlayerShips(Ship[] player){
        for(int i = 0; i < player.length; i ++)
            playerShips[i] = player[i];
    }

    public Location getRandomGuess(){
        int x = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.LEFT;
        int y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
        while(alreadyGuessed(new Location(x,y))){
            x = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.LEFT;
            y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
        }
        if(initialed)
            guesses.add(new Location(x,y));

        return new Location(x, y);
    }

    public boolean alreadyGuessed(Location check){
        if(!check.checkBounds()){
            found = false;
            return true;
        }
        for(Location next : guesses){
            if(check.getGridX().equals(next.getGridX()) && check.getGridY() == next.getGridY())
                return true;
        }
        return false;
    }

    public void randomize(){
        for(int i = 0; i < Player.NUMSHIPS; i ++){
            int x = (int)(Math.random()*((11-getShip(i).getLength())*Board.SIDE))+Board.LEFT;
            int y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
            while(shipOverlap(new Location(x,y),i+1)){
                x = (int)(Math.random()*((11-getShip(i).getLength())*Board.SIDE))+Board.LEFT;
                y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
            }
            getShip(i).move(x,y);
        }
        createLocs();
    }

    public void setLastLoc(Location next){
        lastLoc = next;
    }

    public Location getLastLoc(){
        return lastLoc;
    }

    public boolean pointOverlapComp(Location next){
        boolean answer = false;
        if(!next.checkBounds())
            return false;
        for(int i = 0; i < playerShips.length; i++){
            String storedX = playerShips[i].getLoc().getGridX();
            int storedY = playerShips[i].getLoc().getGridY();
            if(next.getGridY() == storedY){
                for(int check = 0; check < playerShips[i].getLength(); check++){
                    if(next.getGridX().equals(storedX)){
                        answer = true;
                        this.hitUpdate(i);
                    }
                    storedX = Location.increment(storedX);
                } 
            }
        }
        return answer;
    }

    public boolean getFound()
    {
        return found;
    }

    public void hitUpdate(int i){
        super.hitUpdate(i);
        if(super.sunk())
            found = false;
    }
}

