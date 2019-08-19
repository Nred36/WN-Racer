/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wn.racer;

/**
 *
 * @author Nred
 */
public class Scaling {
    public int getS(int y){
        
        return (((y-60) *30)/421); //function returns returns width when given the height on the screen
    }   
}
