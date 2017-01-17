/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import processing.core.PApplet;

/**
 *
 * @author Hayab_000
 */
public class MyRobot {

    public static float radius = 100;
    static PointEx p1 = new PointEx(250, 250);

    public void drawHand(Example ex, PApplet aplet) {
        MyCircle hand = new MyCircle(aplet, ex.point.x, ex.point.y, 6, 6);
        
        float elbowX = (float) Math.cos(ex.angle1) * MyRobot.radius + p1.x;
        float elbowY = (float) Math.sin(ex.angle1) * MyRobot.radius + p1.y;
        
        MyCircle elbow = new MyCircle(aplet, elbowX, elbowY, 6, 6);
        
        aplet.line(p1.x, p1.y, elbowX, elbowY);
        aplet.line(elbowX, elbowY, hand.x, hand.y);
        hand.display();
        elbow.display();
    }
}
