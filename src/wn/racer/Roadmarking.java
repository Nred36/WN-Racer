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
public class Roadmarking extends Thing{

    public Roadmarking(int screenHeight, int screenWidth) {
        super(screenHeight);
        
        // place each marking in the horizontal center of the screen
        posx = screenWidth/2;
    }


    
}
