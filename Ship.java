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
    }
    
    public void draw( Graphics page ){
        page.setColor( new Color( 150, 100, 50 ) );//color defined using rgb values (0-255 each)
    }
    
    public void makeButtons(){
    	for(int r = 0; r < height; r++)
    		for(int c = 0; c < width; c++)
    		{
    			Spot s = new Spot(r, c);
    			int color = (int)(Math.random() * Jewel.NUM_COLORS);
    			Jewel jewel = new Jewel(color, s, jewelsMap, this);
    			jewelsMap.put(s, jewel);
    			JButton button = new JButton();
    			button.setIcon(jewel.getIcon());
    			button.setBackground(Color.BLACK);
    			button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    			buttonMap.put(s, button);
    			buttons.add(button);
    			button.addActionListener(this);
    			button.setActionCommand(r + " " + c);
    		}
    }
}