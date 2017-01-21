/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;

/**
 *
 * @author Hayab_000
 */
public class MyButton extends Sketch {

    int x, y;
    int btnHeight, btnWidth;
    public Method method;
    String funcName;
    ButtonFunctions btnFnctInstance = new ButtonFunctions();
    private PApplet aplet;
    int clickColor = 126;
    String val = "";

    public MyButton(String funcName, PApplet aplet) {
        this.funcName = funcName;
        this.aplet = aplet;
        if (!funcName.equals("")) {
            try {
                this.method = btnFnctInstance.getClass().getDeclaredMethod(funcName, null);
            } catch (NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(Sketch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setCoordinates(int x, int y, int btnHeight, int btnWidth) {
        this.x = x;
        this.y = y;
        this.btnHeight = btnHeight;
        this.btnWidth = btnWidth;
    }

    public void func() {
        if (!funcName.isEmpty()) {
            try {
                method.invoke(btnFnctInstance, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Sketch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void displayVal() {
        aplet.stroke(255);
        aplet.fill(204, 102, 0);
        aplet.rect(x, y, btnWidth, btnHeight);
        aplet.fill(100, 100, 255);
        aplet.text(val, (int) (x + btnWidth / 4), (int) (y + btnHeight - btnHeight / 4));
    }

    public void display() {
        aplet.stroke(255);
        aplet.fill(204, 102, 0);
        aplet.rect(x, y, btnWidth, btnHeight);
        aplet.fill(100, 100, 255);
        aplet.text(funcName, (int) (x + btnWidth / 4), (int) (y + btnHeight - btnHeight / 4));
    }

    public void displayClicked() {
        aplet.stroke(clickColor);
        aplet.fill(204, 102, 0);
        aplet.rect(x, y, btnWidth, btnHeight);
        aplet.fill(100, 100, 255);
        aplet.text(funcName, (int) (x + btnWidth / 4), (int) (y + btnHeight - btnHeight / 4));
    }

    public boolean clicked(int mouseX, int mouseY) {
        return Calculations.isOverRec(x, y, btnWidth, btnHeight, mouseX, mouseY);
    }

    public void setVal(String val) {
        this.val = val;
    }
}
