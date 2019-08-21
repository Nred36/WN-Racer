package wn.racer;

/**
 *
 * @author Will Pringle
 */
public class Player {
    
    static int maxSpeed;
    static int minSpeed;
    static int health;
    static int posx;
    static int posy;
    static int currSpeed;
    static int horizontalSpeed;
    static int direction; // -42 to pos 42
    
    public Player(){
        health = 100;
        maxSpeed = 10;
        minSpeed = 30;
        currSpeed = 30;
        horizontalSpeed =5;
    }
    
    /**
     * Changes the threshold by the number given
     * Ex. changeThreshold(-20) decreases the threshold by 20
     * @param delta 
     */
    public static void changeThreshhold(int delta){
        maxSpeed = maxSpeed + delta;
        minSpeed = minSpeed - delta;
        
    }
    
    /**
     * Moves the player left and right
     * The barrier is the "wall" that the player can't get past.
     * isLeft is true if the player is moving left.
     * @param barrier
     * @param isLeft 
     */
    public void updatePosition(int barrier, boolean isLeft){
        // if the player is moving left
        if(isLeft){
            // if the player is away from the wall
            if(posx >= 0){
                posx-= horizontalSpeed;
            } 
        }
        // if the player is moving right
        else if(!isLeft){
            // if the player is away from the wall
           if(posx <= barrier){
                posx+= horizontalSpeed;
            } 
        }
        // if there is an error (should be impossible since sending boolean)
        else{
            System.out.println("Error - updatePosition in Player");
        }
    }
}
