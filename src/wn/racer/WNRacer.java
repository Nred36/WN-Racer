package wn.racer;//package name

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

public class WNRacer extends JApplet implements ActionListener, KeyListener {
    static JFrame f = new JFrame("");
    Drawing d = new Drawing();
    Graphics2D myPic;
    Image dbImage, master;
    private Graphics dbg;
    Timer timer;
    Player player = new Player();

    int mX, mY, strips = 6,ticksR = 0, px = 900, py= 700;
    int [] roadi = new int[strips];
    int press[] = {0, 0, 0, 0};
    

    public WNRacer() {//program name
        for(int i = 0; i < strips; i++){
            roadi[0]= getHeight()/11;
            if(i>0){
                roadi[i]=roadi[i-1]+175;
            }
        }       
               
        
        timer = new Timer(16, this);
        timer.setInitialDelay(100);     // probably delays the program for 0.1 seconds
        timer.start();
        
        // This commented out code has code where something can be executed every 2 seconds or so if wanted
/*        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this will run every 2 seconds
            }
        });*/

        addKeyListener(this);
    }

    public static void main(String[] args) {
        JApplet applet = new WNRacer();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setVisible(true); //makes it visible
        //f.setResizable(false);//makes in unsizable
        f.setBounds(10, 10, 1879, 1002);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //stops program if you x out the window    
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;      

        
        myPic.setColor(new Color(255,248,220)); //Sand
        myPic.fillRect(0,0,getWidth(),getHeight());
        
        myPic.setColor(Color.lightGray); //Road
        myPic.fillPolygon(d.poly(getWidth(),getHeight(),0));
        
        
        for(int i = 0; i < strips; i++){ //Road Lines
            myPic.setColor(Color.yellow);
            myPic.fillPolygon(d.poly(getWidth()/2, roadi[i],1));
            myPic.setColor(Color.black);
            myPic.drawPolygon(d.poly(getWidth()/2, roadi[i],1));
            if(roadi[i]<1500){                
                roadi[i]+=roadi[i] / player.currSpeed; //Base on Player Speed          
            }else if(roadi[i]>1500 && ticksR>player.currSpeed){ //If the strip is off the screen and a new one hasnt spawned in 30 frames, spawn one
              roadi[i]=getHeight()/11;
              ticksR=0;
            }
            if(i==0){ //Only increase the frame count on the first of the array
                ticksR++;
            }
        }      
        
        
        myPic.setColor(Color.cyan); //Drawing Sky
        myPic.fillRect(0, 0, getWidth(), getHeight()/11);
        
        //PLAYER
        myPic.fillRect(px, py, 60, 100);      
        
        
        if(press[0]==1){
            // move the bike to the left     
        }
        else if(press[1]==1){
            // move the bike to the right
        }
        if(press[2]==1){
            player.currSpeed++;
        }
        if(press[3]==1){
            player.currSpeed--;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
     public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            press[0] = 1;            
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            press[1] = 1;            
        }
        if (press[2] == 0 && (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)) {
            press[2] = 1;           
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            press[3] = 1;
        } 
        }    

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) { // if the a key or the left arrow key is pressed
            press[0] = 0; // set the 
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            press[1] = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            press[2] = 0;            
        }else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            press[3] = 0;
        }
    }
}