package wn.racer;

import java.awt.Image;

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
    static Image[] sprites; // these would be the three images of the car (or the two)
    
    public Player(){
        health = 100;
        maxSpeed = 10;
        minSpeed = 30;
        currSpeed = 30;
        horizontalSpeed =5;
        /**
         * WIP - TO DO
         * 
         * add in code for the images to be included into the image array sprites
         * this array would have three images. one of the car from the back, one
         * from the left side at a 20ish degree angle and one at the right mirrored.
         * 
         */
    }
    
    /**
     * Changes the threshold by the number given
     * Ex. changeThreshold(-20) decreases the threshold by 20
     * 
     * Might remove the functionality to simplify game and code. Could ad later
     * 
     * @param delta 
     */
//    public void changeThreshhold(int delta){
//        maxSpeed = maxSpeed + delta;
//        minSpeed = minSpeed - delta;
//        
//    }
    
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
