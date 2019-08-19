/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class WNRacer extends JApplet implements ActionListener, KeyListener, MouseMotionListener, MouseWheelListener, MouseListener {

    static JFrame f = new JFrame("");
    Drawing d = new Drawing();
    Graphics2D myPic;
    Image dbImage, master;
    private Graphics dbg;
    Timer timer;
    Player player = new Player();

    int mX, mY, strips = 6,ticksR = 0, px = 700, py= 500;
    int [] roadi = new int[strips];
    int press[] = {0, 0, 0, 0};
    double rotation = 0;

    public WNRacer() {//program name
        for(int i = 0; i < strips; i++){
            roadi[0]= getHeight()/11;
            if(i>0){
                roadi[i]=roadi[i-1]+175;
            }
        }       
               
        
        timer = new Timer(16, this);
        timer.setInitialDelay(100);     //starts timer
        timer.start();
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this will run every 2 seconds
            }
        });
        try {//READ
            FileReader fr = new FileReader("save.txt"); //reads from text file (located in "files"
            BufferedReader br = new BufferedReader(fr);
            //map[c] = Integer.parseInt(br.readLine());            
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }
        addKeyListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
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
        AffineTransform old = myPic.getTransform();
        
        myPic.rotate(rotation, getWidth(),getHeight()); //Rotate
        
        
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
                roadi[i]+=roadi[i]/ player.currSpeed; //Base on Player Speed          
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
        
        //RESET ROTATION
        myPic.setTransform(old);
        
        
        //PLAYER
        myPic.fillRect(px, py, 60, 100);      
        
        
            if(press[0]==1){
                px--;               
                rotation -= 0.001;
            }else if(press[1]==1){
                px++;
                rotation += 0.001;
            }
            if(press[2]==1){
                py--;
            }else if(press[3]==1){
                py++;
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        /* if (zoom < 3 && e.getPreciseWheelRotation() < 0) {
            zoom++;
        } else if (zoom > 1 && e.getPreciseWheelRotation() > 0) {
            zoom--;
        }*/
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
