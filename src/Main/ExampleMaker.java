/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Hayab_000
 */
public class ExampleMaker {

    Random rand = new Random();

    static public List<Example> makeExamples() {
        List<Example> examplesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            double angle1 = Math.random() * Math.PI;
            double tmpX = Math.cos(angle1) * MyRobot.radius + MyRobot.p1.x;
            double tmpY = Math.sin(angle1) * MyRobot.radius + MyRobot.p1.y;
            for (int j = 0; j < 30; j++) {
                double angle2 = Math.random() * Math.PI;
                double x = Math.cos(angle2) * MyRobot.radius + tmpX;
                double y = Math.sin(angle2) * MyRobot.radius + tmpY;
                PointEx p = new PointEx(x, y);
                Example ex = new Example(p, angle1, angle2);
                examplesList.add(ex);
            }
        }
        return examplesList;
    }
}
