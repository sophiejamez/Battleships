import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Player{
    private Ship[] ships;
    public static final int NUMSHIPS=5;
    public Player(){
        ships = new Ship[NUMSHIPS];
        for(int i = 0; i < ships.length; i ++)
            ships[i] = new Ship(i+1); 
    }
    
    //draws ships
    public void draw( Graphics page ){
        //color defined using rgb values (0-255 each)
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].draw(page);
        }
    }
}