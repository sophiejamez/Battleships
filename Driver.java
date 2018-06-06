//Last Changed 6/6/2018 final
//done

import javax.swing.*;
import java.awt.*;
public class Driver
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    
    public static void driver()
    {
        JFrame frame = new JFrame( "Player 1" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocation( 0, 0 ); 
        Game game = new Game(Driver.WIDTH, Driver.HEIGHT);
        frame.getContentPane().add(game); 
        frame.pack();
        frame.setVisible(true);
        
        game.playGame();
    }
}