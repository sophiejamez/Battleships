import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Ship{
    private int length;
    private int direction;
    private int xLoc;
    private int yLoc;
    
    //player's ship constructor
    public Ship(int l){
        length = l; 
        if(l == 2 || l == 5){
            yLoc = 20;
            xLoc = 20+(50*l)+200*(l%2);
        }
        else{
            yLoc = 80;
            if(l!=1)
                xLoc = 20+300*(l%2+1);
            else
                xLoc = 50;
        }
    }
    
    public Ship(){
    }
    
    public boolean overlapsWith(int x, int y){
        System.out.println("Coords: " + x + ", "+ y);
        System.out.println("Ship: " + xLoc + ", "+ yLoc);
        if(x >= xLoc && x <= xLoc + 60*length && y >= yLoc && y <= yLoc + 30)
            return true;
        return false;
    }
    
    public void move(int x, int y){
        xLoc = x;
        yLoc = y;
    }
    
    public void draw( Graphics page ){
        page.fillRect(this.xLoc, this.yLoc, 60*length -20, 30);
        page.setColor( new Color( 255, 255, 255 ) );
        for(int i = 0; i < length; i ++)
            page.fillOval(this.xLoc + (60*i) + 20, this.yLoc + 10, 7, 7);
    }
    
    public String toString(){
        return "LENGTH: " + length;
    }
}