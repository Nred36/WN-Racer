package wn.racer;

/**
 *
 * @author Will Pringle
 */
public class Enemy extends Thing{
    
    static int speed = 20;
    
    public Enemy(int screenHeight) {
        super(screenHeight);
        posx = 0;
        posy = 0;
    }
 
    /**
     * moves the enemy towards the player
     * @param player 
     */
    public void updatePositionAgain(Player player){
       int horizontalDifference = posx - player.posx;
       int verticalDifference = posy - player.posy;
       
       double ratio = horizontalDifference / verticalDifference;
       
       // this moves the enemy towards the player in a line
       posx = posx + (int)(speed*ratio);
       posy = posy + (int)(speed*(1-ratio));
    }
    
}
