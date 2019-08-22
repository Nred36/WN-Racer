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
    static int widthDiff;
    Thing(){
        posx = 0;
        posy = 0;

    }
    
    public void updatePosition(Player player){
  
      /*  posy += posy/player.currSpeed;

        sizex = ((posy-60) * 30)/421 * widthDiff;
        sizey = ((posy-60) * 30)/421;*/
        
    }
    
    
}