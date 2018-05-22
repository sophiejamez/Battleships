import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Marker
{
    private Color col;
    private int yCoord;
    private int xCoord;
    
    public Marker(boolean hit, String x, int y)
    {
        if (hit == true)
        {
            col = Color.RED;
        }
        else
        {
            col = Color.WHITE;
        }
        yCoord = y;
        //change string x into an int x coord
    }

    public void draw( Graphics page )
    {
        page.setColor(col);
        page.fillOval(xCoord, yCoord, 20, 20);
    }
}