package wn.racer;//package name

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

public class WNRacer extends JApplet implements ActionListener, KeyListener {

    static JFrame f = new JFrame(""); // f is the JFrame object
    Drawing d = new Drawing(); // create a "Drawing" object
    Graphics2D myPic;
    Image dbImage;
    private Graphics dbg;
    Timer timer;
    Player player = new Player(/*getWidth()/2*/400); // BAD CODE - getWidth() always returns 0 since not created yet

    int strips = 2, ticksR = 0;
//    int [] roadi = new int[strips];
    Roadmarking[] markings = new Roadmarking[strips];
    int press[] = {0, 0, 0, 0};

    /**
     * WNRacer constructor used to make an instance of the main class
     */
    public WNRacer() {
        // Code for the road stripes
/*        for(int i = 0; i < strips; i++){
            roadi[0]= getHeight()/11; // sets each roadi to the top of the road
            if(i>0){
//                roadi[i]=roadi[i-1]+175; // I think this sets the frequency for the first 5?
                roadi[i]=roadi[i-1]+1000; // arbitrarily changed to 1000
            }
        }     */

        // create an array of road objects
        for (int i = 0; i < strips; i++) {

            //markings[i] = new Roadmarking(getHeight(), getWidth()); //!! getheight and getwidth are 0
            markings[i] = new Roadmarking(900, 500); //Temp code, arbitrary number
            if (i > 0) {
                //markings[i].posy=markings[i-1].posy+175; // I think this sets the frequency for the first 5?
                markings[i].posy = markings[i - 1].posy + 75; // I think this sets the frequency for the first 5?
                //markings[i].posx = getWidth()/2;
                //markings[i].posx = -100; // getWidth() returns 0 since applet not created yet or something? ask nathan. Anyways sending -100 instead                
            }
        }

        //       System.out.println("markings height ="+ markings[0].posy+"\nmarkings 1 height = "+markings[1].posy );
        for (int i = 0; i < strips; i++) {
            //markings[i] = new Roadmarking(getHeight());
        }

        addKeyListener(this);

        // Timer code for how often the code is run
        timer = new Timer(16, this);
        timer.setInitialDelay(100);// probably delays the program for 0.1 seconds
        timer.start();

    }

    /**
     * Main function for this program. Sets up the window and creates an
     * instance of the main class (applet) to be used to perform stuff on
     * regarding the window
     *
     * @param args
     */
    public static void main(String[] args) {
        JApplet applet = new WNRacer(); // applet is an object of the main class used to make the gui
        f.getContentPane().add("Center", applet); // get the content pane object from the frame and add center applet
        applet.init(); // lets the applet know that it has been initialized
        f.pack(); // lets the window be resized
        f.setVisible(true); // makes the window show up on the screen
        //f.setResizable(false);//makes in unsizable
        //f.setBounds(10, 10, 1879, 1002); // sets the default size of the window
        f.setBounds(10, 10, 900, 500); // sets the default size of the window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// program stops running if the window is closed  
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;

        myPic.setColor(new Color(255, 248, 220)); //Sand
        myPic.fillRect(0, 0, getWidth(), getHeight());

        myPic.setColor(Color.lightGray); //Road
        myPic.fillPolygon(d.poly(getWidth(), getHeight(), 0));

        /**
         * code for the road lines, needs to be simplified and generalized
         */
        /*       for(int i = 0; i < strips; i++){ 
            
            System.out.println("Width = "+getWidth()+"\nHeight = "+getHeight());
            
            // Make the yellow road markings
            myPic.setColor(Color.yellow);
            myPic.fillPolygon(d.poly(getWidth()/2, roadi[i],1));
            
            // outline the yellow road markings
            myPic.setColor(Color.black); // outline of the roadmarkingss
            myPic.drawPolygon(d.poly(getWidth()/2, roadi[i],1));
            
            // if branches 
            if(roadi[i]<1500){ // Where does 1500 comes from? When I change it it does not work?
            //if(roadi[i] < getHeight()){
                roadi[i]+=roadi[i] / player.currSpeed; //Base on Player Speed          
            }
            // If the strip is off the screen and a new 
            // one hasn't spawned in 30 frames, spawn one
            else if(roadi[i]>1500 && ticksR>player.currSpeed){ 
              roadi[i]=getHeight()/11;
              ticksR=0;
            }
            if(i==0){ //Only increase the frame count on the first of the array
                ticksR++;
            }
        }*/
        int spawn = 9999;

        // Output the roadmarkings 
        for (int i = 0; i < strips; i++) {

            // show the road markings on the screen
            outputRoadmarkings(markings[i]);

            // update the position of the marking            
            markings[i].updatePosition(player, getWidth(), getHeight());
            markings[i].updatePosition(getWidth());

            System.out.println(markings[i].posy + " i: " + i + " ticks: " + ticksR + " sp: " + player.currSpeed);
            // if the mrking is at the bottom of the screen and 88 ticks have passed, create a new object

            if (markings[i].posy >= getHeight() + getHeight() / 10 && spawn == 9999) {
                spawn = i;
                System.out.println("qwe");
            }

        }
        ticksR++;

        if (spawn != 9999 && ticksR > 44) { //WHY after 88 ticks? this should be dynamic                
            //markings[i] = new Roadmarking(getWidth(), getHeight());
            markings[spawn] = new Roadmarking(getWidth(), getHeight());
            ticksR = 0;
            System.out.println("NEW ROADMARKING SPAWNED: " + spawn);
        }

        // Drawing the sky
        myPic.setColor(Color.cyan); //Drawing Sky
        myPic.fillRect(0, 0, getWidth(), getHeight() / 11);

        // Drawing the player
        player.updateVerticalPosition(getHeight()); // update the vertical position
        player.updateHorizontalPosition(f.getWidth(), false, false);
        myPic.fillRect(player.posx, player.posy, 60, 100); // fill the reactanle (temp code)

        // If strufture for taking in movements on the keyboard (changing speed, moving side to side)
        if (press[0] == 1) {
            // move the bike to the left     
            player.updateHorizontalPosition(f.getWidth(), true, true);
        } else if (press[1] == 1) {
            // move the bike to the right
            player.updateHorizontalPosition(f.getWidth(), false, true);
        }
        if (press[2] == 1) {
            //player.currSpeed++;
            player.changeSpeed(1, getHeight());
            System.out.println("speed is = " + player.currSpeed + "\nrelative speed is " + player.ratioSpeed);
        }
        if (press[3] == 1) {
            //player.currSpeed--;
            if (player.currSpeed > 0) {
                player.changeSpeed(-1, getHeight());
                System.out.println("speed is = " + player.currSpeed + "\nrelative speed is " + player.ratioSpeed);
            } else {
                markings[0].posy = 300;
            }
        }

    }

    /**
     * This will output the road markings
     *
     * @param marking
     */
    public void outputRoadmarkings(Roadmarking marking) {

        // Make the yellow road markings
        myPic.setColor(Color.yellow);
        myPic.fillPolygon(d.poly(marking.posx, marking.posy, 1));

        // outline the yellow road markings
        myPic.setColor(Color.black); // outline of the roadmarkingss
        myPic.drawPolygon(d.poly(marking.posx, marking.posy, 1));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
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
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            press[3] = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
