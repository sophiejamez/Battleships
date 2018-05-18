public class Player{
    private Ship[] ships;
    public Player(){
        ships = new Ship[5];
        for(int i = 0; i < ships.length; i ++)
            ships[i] = new Ship(i+1); 
    }
}