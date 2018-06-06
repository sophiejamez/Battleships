//The Location class has private variables that hold the values for the coordinates of a ship
//and the length of each ship. Methods included allow for the construction of a location, 
//the ability to access x and y coordinates, check if coordinates are in bounds, snapping a
//ship to the center of the row it is in, and increasing the col value by one. Everything
//to do with location is in this class. Board, Marker, and Ship all use this class. 

public class Location{
    private int xCoord;
    private int yCoord;
    private String gridX;
    private int gridY;
    public static int SPACE = 10;
    
    private int length;

    private boolean inBounds;

    //contstructor
    public Location(int x, int y){
        xCoord = x;
        yCoord = y;
        gridX = convertIntX(x);
        gridY = convertToGridY(y);
        inBounds = checkBounds();
        if(inBounds){
            snapTo();
        }
    }

    //constructor
    public Location(int x, int y, int l){
        xCoord = x;
        yCoord = y;
        gridX = convertIntX(x);
        gridY = convertToGridY(y);
        inBounds = checkBounds();
        length = l;
        if(inBounds){
            snapTo();
        }
    }

    //constructor
    public Location(String x, int y){
        gridX = x;
        gridY = y;
    }

    //returns whether selected location is in bounds
    public boolean locEquals(Location check){
        if(check.checkBounds() && this.checkBounds()){
            if(check.getGridX().equals(this.getGridX()) && check.getGridY() == this.getGridY())
                return true;
        }
        else{
            if(check.getXVal() >= this.getXVal() && check.getXVal() <= this.getXVal() + 60*length)
                if(check.getYVal() >= this.getYVal() && check.getYVal() <= this.getYVal() + 30){
                    return true;
                }
        }
        return false;
    }

    //returns x coordinate
    public int getXVal(){
        return xCoord;
    }

    //returns y coordinate
    public int getYVal(){
        return yCoord;
    }

    //returns string of x coordinate of grid A-J
    public String getGridX(){
        return gridX;
    }
    
    //returns y coordinate of grid 0-9
    public int getGridY(){
        return gridY;
    }

    // pre: setInBounds() has been called
    // post: returns if location is inBounds
    public boolean checkBounds(){
        if(gridY == -1 || gridX == null)
            return false;
        return true;
    }

    //places ship in the middle of section selected
    public void snapTo(){
        if(!inBounds)
            return;
        int colVal = getColVal(gridX);
        xCoord = ((colVal) * Board.SIDE) + Board.LEFT + SPACE;
        yCoord = ((gridY) * Board.SIDE) + Board.TOP + 2*SPACE;
    }

    // pre: none
    // post: turns int x loc into a String on grid
    public static int getColVal(String col){
        if (col==null)
            return -1;
        else if(col.equals("A"))
            return 0;
        else if (col.equals("B"))
            return 1;
        else if (col.equals("C"))
            return 2;
        else if (col.equals("D"))
            return 3;
        else if (col.equals("E"))
            return 4;
        else if (col.equals("F"))
            return 5;
        else if (col.equals("G"))
            return 6;
        else if (col.equals("H"))
            return 7;
        else if (col.equals("I"))
            return 8;
        else if (col.equals("J"))
            return 9;
        return -1;
    }

    // pre: x is in grid
    // post: gets int loc of x and changes it into a String
    public static String convertIntX(int x){
        String answer = "A";
        int num = 0;
        while(answer!=null){
            if(x >= Board.LEFT + Board.SIDE*num && x <= Board.LEFT + Board.SIDE*(num+1))
                return answer;
            answer = increment(answer);
            num++;
        }
        return null;
    }

    //converts y coordinate to grid y coordinate on board
    public static int convertToGridY(int y){
        int answer = 0;
        int num = 0;
        while(num <= 9){
            if(y >= Board.TOP + Board.SIDE*num && y <= Board.TOP + Board.SIDE*(num+1))
                return answer;
            answer ++;
            num ++;
        }
        return -1;
    }

    //increases x coordinate by one (string)
    public static String increment(String change){
        if(change == null)
            return null;
        else if(change.equals("A"))
            return "B";
        else if(change.equals("B"))
            return "C";
        else if(change.equals("C"))
            return "D";
        else if(change.equals("D"))
            return "E";
        else if(change.equals("E"))
            return "F";
        else if(change.equals("F"))
            return "G";
        else if(change.equals("G"))
            return "H";
        else if(change.equals("H"))
            return "I";
        else if(change.equals("I"))
            return "J";
        return null;
    }
    
    //decreases x coordinate by one (string)
    public static String decrement(String change){
        if(change == null)
            return null;
        else if(change.equals("J"))
            return "I";
        else if(change.equals("I"))
            return "H";
        else if(change.equals("H"))
            return "G";
        else if(change.equals("G"))
            return "F";
        else if(change.equals("F"))
            return "E";
        else if(change.equals("E"))
            return "D";
        else if(change.equals("D"))
            return "C";
        else if(change.equals("C"))
            return "B";
        else if(change.equals("B"))
            return "A";
        return null;
    }
}