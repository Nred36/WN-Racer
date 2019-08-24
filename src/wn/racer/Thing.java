package wn.racer;

/**
 * superclass for all things. includes posx and posy 
 * @author Will Pringle
 */
public class Thing {
        
    static int posx;
    static int posy;
    static int sizex;
    static int sizey;
    static int width;
    
    /**
     * Spawns the thing at the horizon. Further spawn actions should be 
     * done in the subclass' constructor. 
     * @param screenHeight
     * @param screenWidth 
     */
    Thing(int screenHeight){
        posy = screenHeight / 11; // spawn the thing at the horizon
    }
    
    /**
     * Changes the position on screen of the player
     * @param player
     * @param screenWidth
     * @param screenHeight 
     */
    public void updatePosition(Player player, int screenWidth, int screenHeight){
        
        //posy += posy / player.currSpeed; // advances the thing downard (based on speed
        
        posy += posy/player.currSpeed; // advances the thing downard (based on speed
        
        
 
        
      /*  posy += posy/player.currSpeed;
        sizex = ((posy-60) * 30)/421 * widthDiff;
        sizey = ((posy-60) * 30)/421;*/
        
    }
    
    
}