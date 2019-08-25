package wn.racer;

import java.awt.Image;

/**
 *
 * @author Will Pringle
 */
public class Player {
    
    static int health;
    static int posx;
    static double ratiox = 0.50; // horizontal ratio on the screen
    static int posy;
    static double ratioy = 8.0/11.0; // vertical ratio on the screen 
    static int currSpeed;
    static double ratioHorizontalSpeed = 2.5/100.0;
    static Image[] sprites; // these would be the three images of the car (or the two)
    
    public Player(int initialHoeizontalPosition){
        health = 100;
        currSpeed = 30;
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
     * @param screenWidth
     * @param isLeft 
     * @param movePlayer
     */
    public void updateHorizontalPosition(int screenWidth, boolean isLeft, boolean movePlayer){
         
        if(movePlayer){
            // if the player is moving left
            if(isLeft){
                // if the player is away from the wall
                if(ratiox >= 0){
                    ratiox-= ((double) ratioHorizontalSpeed*screenWidth) / 1000.0;
                } 
            }
            // if the player is moving right
            else if(!isLeft){
                // if the player is away from the wall
               if(ratiox <= 1.0){
                    ratiox+= ((double) ratioHorizontalSpeed*screenWidth) / 1000.0;
                } 
            }
        }
        
        posx = (int) (ratiox * (double) screenWidth);
        
        
    }
    
    /**
     * Keeps the player in the same vertical position despite resizing the
     * screen.
     * @param screenHeight 
     */
    public void updateVerticalPosition(int screenHeight){
        posy = (int) (screenHeight * 8 / 11);
    }
    
    /**
     * The image of the player will be returned
     * @param screenWidth
     * @return 
     */
    public Image getSprite(int screenWidth){
        
        
        
        return sprites[2];
    }
    
}
