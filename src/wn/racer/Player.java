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
public class Player {
    
    static int maxSpeed;
    static int minSpeed;
    static int health;
    static int posx;
    static int posy;
    static int currSpeed;
    static int direction; // -42 to pos 42
    
    public Player(){
        health = 100;
        maxSpeed = 10;
        minSpeed = 30;
        currSpeed = 30;
        
        
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
    


}
