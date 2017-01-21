/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Random;

/**
 *
 * @author Hayab_000
 */
public class Perceptron {
    double[] weights;
    double delta;
    double a;
    double out;
    double wholeNetworkOutput;

    public Perceptron(int numOfWeights) {
        Random rand = new Random();
        weights = new double[numOfWeights];
        for (int i = 0; i< weights.length; i++) {
            weights[i] = (rand.nextInt(200000) - 100000f) / 100000f;
        }
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public void setWholeNetworkOutput(double wholeNetworkOutput) {
        this.wholeNetworkOutput = wholeNetworkOutput;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
    
    
    
}
