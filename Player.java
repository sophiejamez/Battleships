import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Player{
    private Ship[] ships;
    public static final int NUMSHIPS=5;
    private Ship clicked;

    public Player(){
        ships = new Ship[NUMSHIPS];
        clicked = new Ship();
        for(int i = 0; i < ships.length; i ++){
            ships[i] = new Ship(i+1); 
        }
    }

    public Ship getShip(int i){
        return ships[i];
    }

    public boolean bombHit(int xInt, int y)
    {
        String x = Ship.convertIntX(xInt);
        //y = Ship.convertToGridY(y);
        if(overlap(xInt,y))
            return true;
        return false;

        // for (int i = 0; i <ships.length; i++)
        // {
            // System.out.println("Ship at " + ships[i].getX() + ", " + ships[i].getY());
            // String storedX = ships[i].getX();
            // try{
                // if(x.equals(ships[i].getX()) && y == ships[i].getY())
                // {
                    // return true;
                // }
            // }catch(NullPointerException e){System.out.println("no");}
        // }
        // return false;
    }
    

    public boolean inBounds(){
        //doesnt work yet
        for(int i = 0; i < ships.length; i++){
            int x = ships[i].getXLoc();
            int y = ships[i].getY();
            //for (int j = ships[i].length - 1; j > 0; j--){
            if((x <= 20 || x>= 620) || (y <= 150 || y>= 750)){
                return false;
            }
            //}
        }
        return true;
    }

    //draws ships
    public void draw( Graphics page ){
        //color defined using rgb values (0-255 each)
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].draw(page);
        }
    }

    public void drawMini(Graphics page){
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].drawMini(page);
        }
    }

    public boolean move(int x, int y){
        if(!overlap(x, y)){
            clicked.move(x,y);
            return false;
        }
        return true;
        
    }

    public void act(int x, int y){
        boolean overlaps = false;
        int num = 0;

        while(!overlaps && num < 5){
            overlaps = ships[num].overlapsWith(x,y);
            num++;
        }
        if(overlaps)
            clicked = ships[num-1];
    }

    public void mapLocs(){
        for(Ship next : ships){
            next.setLoc();
        }
    }

    //NEED TO DO FOR ROTATED SHIP!!!
    public boolean overlap(int xLoc, int y){
        y = Ship.convertToGridY(y);
        String x = Ship.convertIntX(xLoc);
        for(int i = 0; i < ships.length; i++){
            String storedX = Ship.convertIntX(ships[i].getXLoc());
            int storedY = Ship.convertToGridY(ships[i].getYLoc());
            if(y == storedY){
                for(int check = 0; check < ships[i].getLength(); check++){
                    if(x.equals(storedX))
                        return true;
                    storedX = Ship.increment(storedX);
                } 
            }
        }
        return false;
    }
}