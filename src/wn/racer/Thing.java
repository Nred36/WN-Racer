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
    Thing(int screenHeight){
        posy = screenHeight / 11; // spawn the thing at the horizon
    }
    
    public void updatePosition(Player player, int screenWidth, int screenHeight){
        
        //posy += posy / player.currSpeed; // advances the thing downard (based on speed
        
        posy += posy/player.currSpeed; // advances the thing downard (based on speed
        
        posx = screenWidth/2;
 
        
      /*  posy += posy/player.currSpeed;
        sizex = ((posy-60) * 30)/421 * widthDiff;
        sizey = ((posy-60) * 30)/421;*/
        
    }
    
    
}