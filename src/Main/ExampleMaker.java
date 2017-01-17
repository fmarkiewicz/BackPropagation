/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import processing.core.PApplet;

/**
 *
 * @author Hayab_000
 */
public class ExampleMaker {

    Random rand = new Random();

    public float calcAngle(PointEx p1, PointEx p2, PointEx p3) {
        // p1 jest wierzcholkiem, p1 is vertex
        float p1_2 = calcLength(p1, p2);
        float p1_3 = calcLength(p1, p3);
        float p2_3 = calcLength(p2, p3);

        return (float) Math.acos((p1_2 * p1_2 + p1_3 * p1_3 - p2_3 * p2_3) / (2 * p1_2 * p1_3));
    }

    float calcLength(PointEx from, PointEx to) {
        return (float) Math.sqrt(((from.x - to.x) * (from.x - to.x)) + ((from.y - to.y) * (from.y - to.y)));
    }

    static public List<Example> makeExamples() {
        List<Example> examplesList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            double angle1 = Math.random() * Math.PI;
            float tmpX = (float) Math.cos(angle1) * MyRobot.radius + MyRobot.p1.x;
            float tmpY = (float) Math.sin(angle1) * MyRobot.radius + MyRobot.p1.y;
            for (int j = 0; j<30;j++) {
                double angle2 = Math.random() * Math.PI * 2;
                float x = (float) Math.cos(angle2) * MyRobot.radius + tmpX;
                float y = (float) Math.sin(angle2) * MyRobot.radius + tmpY;
                PointEx p = new PointEx(x, y);
                examplesList.add(new Example(p, (float)angle1, (float)angle2));
            }
        }

        return examplesList;
    }
}
