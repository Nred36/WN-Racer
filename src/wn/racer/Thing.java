package wn.racer;

/**
 * superclass for all things. includes posx and posy 
 * @author Will Pringle
 */
public class Thing {
        
    static int posx;
    static int posy;
    
    Thing(){
        posx = 0;
        posy = 0;

    }
    
    public static void updatePosition(){
        /* Math here to get the thing one thing closer to the screen
        I'm sure the math would involve using the posx and posy since
        it gets closer to the thing at an accelerated rate */
    }
    
    
}