//The Game class has a multitude of private variables that hold different Strings, Boards,
//Players, and that will determine how the board looks, how the board works, and finds if the
//location selected is within boundaries. The Driver uses this class. 


import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Game extends JPanel implements MouseListener,ActionListener,KeyListener
{
    public static String TEXT = "DONE PLACING YOUR SHIPS?";
    public static String YOURTURN = "Try to find the ships!";
    public static String HIT = "HIT!";
    public static String MISS = "MISS!";
    public static String SUNK = "SUNK!";
    public static String IN_GRID = "All of your ships must be in the grid.";
    public static String START = "PRESS SPACE TO BEGIN";
    
    private Player player;
    private Board board;
    private Computer computer;
    private boolean placed = false;
    private boolean selected = false;
    private JButton button;
    private JLabel turn;
    private JLabel result;
    private JLabel error;
    private JLabel titleBack;
    private JLabel logo;
    private JLabel begin;
    private boolean begun;
    private Board compBoard;
    private JLabel finish;

    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        this.setLayout( null );
        this.setBackground( new Color(0, 100, 180) );
        this.setPreferredSize( new Dimension( width, height ) );

        player = new Player(); 
        board = new Board();
        compBoard = new Board(true);
        
        computer = new Computer();
        this.addMouseListener(this);
        this.addKeyListener(this);
        
        button = new JButton(TEXT);
        button.setBounds(700,500,200,50);
        this.add(button);
        button.setVisible(false);
        button.addActionListener(this);

        turn = new JLabel(YOURTURN);
        turn.setForeground(Color.WHITE);
        turn.setBounds(80,50,800,50);
        this.add(turn);
        turn.setVisible(false);
        turn.setFont(new Font("Courier New", Font.ITALIC, 36));

        error = new JLabel(IN_GRID);
        error.setForeground(Color.WHITE);
        error.setBounds(80,50,800,50);
        this.add(error);
        error.setVisible(false);
        error.setFont(new Font("Courier New", Font.BOLD, 25));

        result = new JLabel();
        result.setForeground(Color.RED);
        result.setBounds(700, 700, 300, 80);
        this.add(result);
        result.setVisible(false);
        result.setFont(new Font("Courier New", Font.BOLD, 80));
        
        finish = new JLabel();
        finish.setBounds(650,500,400,80);
        this.add(finish);
        finish.setVisible(false);
        finish.setFont(new Font("Courier New", Font.BOLD, 70));
        
        createTitleScreen();
        
        begun = false;
        
        this.setFocusable(true);
    }
    
    //Creates the title screen.
    public void createTitleScreen(){
        begin = new JLabel(START);
        begin.setBounds(300, 500, 500,30);
        begin.setForeground(Color.WHITE);
        this.add(begin);
        begin.setVisible(true);
        begin.setFont(new Font("Courier New", Font.BOLD, 35));
        
        ImageIcon battleLogo = new ImageIcon("logo.jpg");
        logo = new JLabel(battleLogo);
        logo.setBounds(50,250,900,240);
        this.add(logo);
        logo.setVisible(true);
        
        ImageIcon iconLogo = new ImageIcon("boats.jpg");
        titleBack = new JLabel(iconLogo);
        titleBack.setBounds(0,0,1000,800);
        this.add(titleBack);
        titleBack.setVisible(true);
    }

    public void playGame()
    {
        boolean over = false;
        while( !player.won() && !computer.won() )
        {
            try
            {
                Thread.sleep( 200 );//pause for 200 milliseconds
                if(!begun)
                    begin.setVisible(!begin.isVisible());
            }catch( InterruptedException ex ){}
            this.repaint();
            if(error.isVisible() || result.isVisible())
                try{
                    Thread.sleep(800);
                    result.setVisible(false);
                    error.setVisible(false);
                }catch(InterruptedException ex){}

            this.repaint();

        }
        if(player.won())
            finish.setText("You WON!");
        else
            finish.setText("You Lost.");
        finish.setVisible(true);
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );
        player.snapTo();
        computer.snapTo();
        if(!placed)
        {
            board.draw(page);
            player.draw( page );
        }
        else
        {
            turn.setVisible(true);
            board.drawMini(page);
            player.drawMini(page);
            board.drawGame(page);
            compBoard.drawGame(page);
        }
    }

    //checks if the spot pressed is in bounds and a valid selection, and sets whatever visuals
    //are required for that spot to visible. 
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Location clicked = new Location(x,y);
        result.setVisible(false);
        if(!placed){
            if(!selected){
                player.act(clicked);
                selected = true;
            }
            else{
                selected = player.move(x, y);
            }
        }
        else if(clicked.checkBounds()){
            int locHit = computer.bombHit(new Location(x,y));
            if((locHit != -1)) 
            {
                player.hitUpdate(locHit);
                result.setForeground(Color.YELLOW);
                result.setText(HIT);
                if(player.sunk()){
                    result.setForeground(Color.RED);
                    result.setText(SUNK);
                }
                result.setVisible(true);
                board.placeHit(new Location(x,y));
            }
            else{
                result.setForeground(new Color(200,200,200));
                result.setText(MISS);
                result.setVisible(true);
                board.placeMiss(new Location(x,y));
            }
            computerTurn();
        }
    }
    
    //switches the turn to computer after player completes their move. Then completes
    //computer move.
    public void computerTurn(){
        if(!computer.getFound()){
            computer.setLastLoc(computer.getRandomGuess());
        }
        else
            computer.intelliGuess();
        if(computer.pointOverlapComp(computer.getLastLoc())){
            compBoard.placeHit(computer.getLastLoc());
            if(!computer.getFound()){
                computer.setFound(true);
                computer.setLookBehind(false);
                computer.setBehind(computer.getLastLoc());
            }
            if(computer.sunk()){
                computer.setFound(false);
                computer.setLookBehind(false);
            }
        }
        else{
            compBoard.placeMiss(computer.getLastLoc());
            if(computer.getFound())
                computer.setLookBehind(true);
        }
    }

    //Makes selected the opposite of whatever it was. 
    public void changePlaceBoolean(){
        if (selected)
            selected = false;
        else
            selected = true;
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }
    
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode() == KeyEvent.VK_SPACE ){
            titleBack.setVisible(false);
            logo.setVisible(false);
            begin.setVisible(false);
            button.setVisible(true);
            begun = true;
        }
    }

    public void actionPerformed(ActionEvent event){
        player.createLocs();
        computer.createLocs();
        computer.setPlayerShips(player.getShips());
        if(player.outOfBounds())
            error.setVisible(true);
        else{
            placed = true;
            button.setVisible(false);
        }
    }
    
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
    }
}
