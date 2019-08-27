/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wn.racer;

import java.awt.Polygon;

/**
 *
 * @author Nred
 */
public class Drawing {

    /**
     * creates a polygon object that looks faux 3d
     *
     * @param x // ???
     * @param y // ???
     * @param t // type, 1 for road, 2 for road marking
     * @return polygon object with the details given
     */
    public Polygon poly(int x, int y, int t) {
        Polygon p = new Polygon();

        if (t == 0) {  //For the Road its self
            p.addPoint(0 - x / 11, y);
            p.addPoint(x / 3, y / 11);
            p.addPoint(x - x / 3, y / 11);
            p.addPoint(x + x / 11, y);
        } else if (t == 1) { //For drawing the strips
            int w = 15, h = 45;

            w = getS(y);
            h = (int) (getS(y) * 2.5);

            x = x - (w / 2);
            y = y - (h / 2);
            p.addPoint(x, y);
            p.addPoint(x + w, y);
            p.addPoint(x + w + w / 3, y + h);
            p.addPoint(x - w / 3, y + h);
        }

        return p;
    }

    /**
     * Gives the width of the thing taken from the scaling class
     *
     * @param heightOfScreen
     * @return width of the object
     */
    public int getS(int heightOfScreen) {
        int width = ((heightOfScreen - heightOfScreen / 11) * 30) / 421;
        return width;
    }
}
