/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wn.racer;

/**
 *
 * @author Will Pringle
 */
public class Tumbleweed extends Thing{
    
    static int horizontalSpeed = 20;
    
    Tumbleweed(int screenHeight, int screenWidth) {
        super(screenHeight);
        
    } 
    
    
    /**
     * This function updates the position of the tumbleweed. The position
     * is updated to move to either the right or the left. If 0 is send, the 
     * tumbleweed will move to the right. 0 means move to the right, 1 means left
     * @param moveLeft 
     */
    public void updatePositionAgain(boolean moveLeft){
        // move horizontally to one direction
        if(moveLeft){
            posx--;
        }
        else{
            posx++;
        }
    }

}