package wn.racer;

import java.awt.Image;

/**
 *
 * @author Will Pringle
 */
public class Player {
    
    static int health;
    static int posx;
    static int posy;
    static int currSpeed;
    static int horizontalSpeed;
    static Image[] sprites; // these would be the three images of the car (or the two)
    
    public Player(int initialHoeizontalPosition){
        health = 100;
        currSpeed = 30;
        horizontalSpeed = 5;
        posx = initialHoeizontalPosition;
        System.out.println("thing -= "+initialHoeizontalPosition);
        /**
         * WIP - TO DO
         * 
         * add in code for the images to be included into the image array sprites
         * this array would have three images. one of the car from the back, one
         * from the left side at a 20ish degree angle and one at the right mirrored.
         * 
         * make the player spawn in the horizontal middle
         */
    }
    
    
    /**
     * Moves the player left and right
     * The barrier is the "wall" that the player can't get past.
     * isLeft is true if the player is moving left.
     * @param barrier
     * @param isLeft 
     */
    public void updateHorizontalPosition(int barrier, boolean isLeft){
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
    }
    
    /**
     * Keeps the player in the same vertical position despite resizing the
     * screen.
     * @param screenHeight 
     */
    public void updateVerticalPosition(int screenHeight){
        posy = (int) (screenHeight * 8 / 11);
    }
    
}
