import java.awt.*;
public class Ship{
    private int length;
    private int direction;
    private int xLoc;
    private int yLoc;
    private int width, height;
    
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
                xLoc = 20+400*(l%2+1);
            else
                xLoc = 50;
        }
    }
    
    public void draw( Graphics page ){
        page.fillRect(this.xLoc, this.yLoc, 50*length, 30);
        page.setColor( new Color( 255, 255, 255 ) );
        for(int i = 0; i < length; i ++)
            page.fillOval(this.xLoc + (50*i) + 20, this.yLoc + 10, 7, 7);
    }
    
    // public void makeButtons(){
        // for(int r = 0; r < height; r++)
            // for(int c = 0; c < width; c++)
            // {
                // Spot s = new Spot(r, c);
                // int color = (int)(Math.random() * Jewel.NUM_COLORS);
                // Jewel jewel = new Jewel(color, s, jewelsMap, this);
                // jewelsMap.put(s, jewel);
                // JButton button = new JButton();
                // button.setIcon(jewel.getIcon());
                // button.setBackground(Color.BLACK);
                // button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                // buttonMap.put(s, button);
                // buttons.add(button);
                // button.addActionListener(this);
                // button.setActionCommand(r + " " + c);
            // }
    // }
}