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
        /* Math here to get the thing one thing closer to the screen
        I'm sure the math would involve using the posx and posy since
        it gets closer to the thing at an accelerated rate */        
         posy += posy/player.currSpeed;
         
         sizex = ((posy-60) * 30)/421 * widthDiff;
         sizey = ((posy-60) * 30)/421;
        
    }
    
    
}