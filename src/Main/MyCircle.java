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
public class MyCircle {

    PApplet aplet;
    double x;
    double y;
    int radius;
    int color;

    public double distance(MyCircle otherPoint){
        return ((this.x - otherPoint.x)*(this.x - otherPoint.x)) + ((this.y - otherPoint.y)*(this.y - otherPoint.y));
    }
    
    public void update(double alpha, double G, MyCircle otherPoint) {
        this.x += alpha * G * (otherPoint.x - this.x);
        this.y += alpha * G * (otherPoint.y - this.y);
    }
    
    public MyCircle(PApplet aplet, double x, double y, int radius, int color) {
        this.aplet = aplet;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public MyCircle(double x, double y, int radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void display() {
        aplet.stroke(255);
        aplet.fill(204, 102, 0);
        aplet.ellipse((float)x, (float)y, radius, radius);
        aplet.fill(100, 100, 255);
    }

    public void setAplet(PApplet aplet) {
        this.aplet = aplet;
    }
}
