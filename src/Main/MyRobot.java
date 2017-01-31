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

    public static double radius = 100;
    static PointEx p1 = new PointEx(250, 30);

    public void drawHand(Example ex, PApplet aplet) {
        
        double elbowX =  Math.cos(ex.angle1) * MyRobot.radius + p1.x;
        double elbowY =  Math.sin(ex.angle1) * MyRobot.radius + p1.y;
        MyCircle elbow = new MyCircle(aplet, elbowX, elbowY, 6, 6);
        
        double handX = Math.cos(ex.angle2) * MyRobot.radius + elbowX;
        double handY = Math.sin(ex.angle2) * MyRobot.radius + elbowY;
        MyCircle hand = new MyCircle(aplet, handX, handY, 6, 6);
        
        aplet.line((float)p1.x, (float)p1.y, (float)elbowX, (float)elbowY);
        aplet.line((float)elbowX, (float)elbowY, (float)hand.x, (float)hand.y);
        hand.display();
        elbow.display();
    }
    
        public void drawExample(Example ex, PApplet aplet) {
        MyCircle hand = new MyCircle(aplet, ex.point.x, ex.point.y, 6, 6);
        
        double elbowX =  Math.cos(ex.angle1) * MyRobot.radius + p1.x;
        double elbowY =  Math.sin(ex.angle1) * MyRobot.radius + p1.y;
        
        MyCircle elbow = new MyCircle(aplet, elbowX, elbowY, 6, 6);
        
        aplet.line((float)p1.x, (float)p1.y, (float)elbowX, (float)elbowY);
        aplet.line((float)elbowX, (float)elbowY, (float)hand.x, (float)hand.y);
        hand.display();
        elbow.display();
    }
}
