import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Game extends JPanel implements MouseListener,KeyListener
{
    private Player player;
    private Board board;

    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        //make a panel with dimensions width by height with a black background
        this.setLayout( null );//Don't change
        this.setBackground( new Color(0, 100, 180) );
        this.setPreferredSize( new Dimension( width, height ) );//Don't change
        
        //initialize the instance variables
        player = new Player();  //change these numbers and see what happens
        board = new Board();

        this.addMouseListener(this);//allows the program to respond to key presses - Don't change

        this.setFocusable(true);//I'll tell you later - Don't change
    }
    
    public void playGame()
    {
        boolean over = false;
        while( !over )
        {
            try
            {
                Thread.sleep( 200 );//pause for 200 milliseconds
            }catch( InterruptedException ex ){}
            
            this.repaint();//redraw the screen with the updated locations; calls paintComponent below
        }
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );//I'll tell you later.
        player.draw( page );//calls the draw method in the Player class
        board.draw(page);
    }

    //not used but must be present
    public void keyReleased( KeyEvent event )
    {  
    }
    
    // //tells the program what to do when keys are pressed
    public void keyPressed( KeyEvent event )
    {
        if( event.getKeyCode() == KeyEvent.VK_LEFT )
        {
            //player.moveLeft();
        }
    }
    
    //not used but must be present
    public void keyTyped( KeyEvent event )
    {
    }
    
    public void mousePressed(MouseEvent e){
        System.out.println("yo");
        int xPos = e.getX();
        int yPos = e.getY();
    }
    public void mouseClicked(MouseEvent e){
    }
    
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
}