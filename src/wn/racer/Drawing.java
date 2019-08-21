/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wn.racer;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;

/**
 *
 * @author Nred
 */
public class Drawing extends Canvas {
    Graphics2D draw;
    Image dbImage, master;
    private Graphics dbg;   
    Canvas can;
    
    /*public void paint(Graphics g) {
        //JApplet jj = new JApplet();
        dbImage = j.createImage(j.getWidth(), j.getHeight());      //creates and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, j);
    }*/

    public void paint(Graphics g) {
        draw = (Graphics2D) g;        
    }
    
    /*
    
    */
    public Polygon poly(int x, int y, int t){
        Polygon p = new Polygon();
        Scaling s = new Scaling();
        
        if (t == 0){  //For the Road its self
            p.addPoint(0-x/11,y);
            p.addPoint(x/3, y/11);
            p.addPoint(x - x/3, y/11);
            p.addPoint(x + x/11, y);
        }else if(t == 1){ //For drawing the strips
            int w=15, h = 45;
            
            w = s.getS(y);
            h =(int)(s.getS(y)*2.5);
            
            x= x - (w/2);
            y = y - (h/2);
            p.addPoint(x,y);
            p.addPoint(x+w, y);
            p.addPoint(x+w+w/3, y+h);
            p.addPoint(x-w/3, y+h);
        }
        
        return p;
    }
}
