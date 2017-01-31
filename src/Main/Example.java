/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayab_000
 */
public class Example {

    public PointEx point;
    public double angle1;
    public double angle2;

    public Example(PointEx point, double angle1, double angle2) {
        this.point = point;
        this.angle1 = angle1;
        this.angle2 = angle2;
    }

    public static List<Example> prepareExamples(List<Example> examples) {
        double xMax = -9999;
        double yMax = -9999;
        for (Example ex : examples) {
            xMax = ex.point.x > xMax ? ex.point.x : xMax;
            yMax = ex.point.y > yMax ? ex.point.y : yMax;
        }
        Sketch.xMax = yMax;
        Sketch.yMax = yMax;

        for (Example ex : examples) {
            ex.angle1 = ex.angle1 / Math.PI;
            ex.angle2 = ex.angle2 / Math.PI;
            ex.point.x = ex.point.x / xMax;
            ex.point.y = ex.point.y / yMax;
        }

        return examples;
    }

    public static List<Example> getExamples(List<Example> examples) {
        for (Example ex : examples) {
            ex = getExample(ex);
        }
        return examples;
    }

    public static Example getExample(Example ex) {
            ex.angle1 = ex.angle1 * Math.PI;
            ex.angle2 = ex.angle2 * Math.PI;
            ex.point.x = ex.point.x * Sketch.xMax;
            ex.point.y = ex.point.y * Sketch.yMax;
        return ex;
    }
}
