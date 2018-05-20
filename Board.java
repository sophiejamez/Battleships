import java.awt.*;
public class Board{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public static final int TOP = 150;
    public static final int SIDE = 60;
    public static final int LEFT = 20;
    public static final int THICK = 2;
    public Board(){
    }
    public void draw( Graphics page ){
        page.setColor( Color.BLACK );//color defined using rgb values (0-255 each)
        for(int r = 0; r <= NUM_ROWS; r ++)
            page.fillRect(LEFT, TOP+SIDE*r, NUM_COLS * SIDE, THICK);
        for(int c = 0; c <= NUM_COLS; c++)
            page.fillRect(LEFT+SIDE*c, TOP, THICK, NUM_ROWS * SIDE);
    }
}