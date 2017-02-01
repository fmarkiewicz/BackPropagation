/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Random;

/**
 *
 * @author Yumis
 */
public class ButtonFunctions {

    static private void setPointInNetwork(PointEx point) {
        Sketch.network.get(0).get(0).setOut(point.x);
        Sketch.network.get(0).get(1).setOut(point.y);
    }

    static private void feedForward() {
        for (int warstwa = 1; warstwa < Sketch.network.size(); warstwa++) {
            //obliczenia dla kazdego neuronu w warstwie razem z biasu
            for (int perceptron = 0; perceptron < Sketch.network.get(warstwa).size(); perceptron++) {
                double a = 0;
                // liczenie sumy (a) dla neuronu z ukrytej warstwy
                for (int neuron = 0; neuron < Sketch.network.get(warstwa - 1).size(); neuron++) {
                    a += Sketch.network.get(warstwa - 1).get(neuron).out * Sketch.network.get(warstwa - 1).get(neuron).weights[perceptron];
                }
                //zapamietanie sumy
                Sketch.network.get(warstwa).get(perceptron).setA(a);
                //obliczenie wyjscia z funkcji
                a = Calculations.sigmoid(a);
                // zapisanie wyjscia z funkcji
                Sketch.network.get(warstwa).get(perceptron).setOut(a);
            }
        }
    }

    static private void deltaForLastLayer() {
        for (int j = 0; j < Sketch.network.get(Sketch.network.size() - 1).size(); j++) {
            double t = Sketch.network.get(Sketch.network.size() - 1).get(j).wholeNetworkOutput;
            double out = Sketch.network.get(Sketch.network.size() - 1).get(j).out;
            double delta = (out - t) * out * (1 - out);
            Sketch.network.get(Sketch.network.size() - 1).get(j).setDelta(delta);
        }
    }

    static private void deltaForHiddenLayers() {
        for (int j = Sketch.network.size() - 2; j > 0; j--) {
            //dla kazdego neuronu w warstwie
            for (int k = 0; k < Sketch.network.get(j).size(); k++) {
                //oblicz delte
                double delta = 0;
                double out = Sketch.network.get(j).get(k).out;
                double sum = 0;

                // policz sume wagi razy delty z wyzszej warstwy
                for (int m = 0; m < Sketch.network.get(j + 1).size(); m++) {
                    double weight = Sketch.network.get(j).get(k).weights[m];
                    double deltaBefore = Sketch.network.get(j+1).get(m).delta;
                    sum += weight * deltaBefore;
                }
                delta = sum * out * (1 - out);
                Sketch.network.get(j).get(k).setDelta(delta);
            }
        }
    }

    static private void correctWeights() {
        for (int j = 0; j < Sketch.network.size() - 1; j++) {
            // dla kazdego perceptronu w warstwie
            for (int k = 0; k < Sketch.network.get(j).size(); k++) {
                // dla kazdej wagi perceptronu
                double out = Sketch.network.get(j).get(k).out;
                for (int m = 0; m < Sketch.network.get(j).get(k).weights.length; m++) {
                    double weight = Sketch.network.get(j).get(k).weights[m];
                    double deltaBefore = Sketch.network.get(j + 1).get(m).delta;
                    Sketch.network.get(j).get(k).weights[m] = weight - Sketch.learnStatic * deltaBefore * out;
                }
            }
        }
    }

    static public void learn() {
        System.out.println("learn");
        Random rand = new Random();
        Sketch.examplesList = Example.prepareExamples(Sketch.examplesList);
        
        for (int i = 0; i < Sketch.T; i++) {
            //losowanie przykladu
            Example currentExample = Sketch.examplesList.get(rand.nextInt(Sketch.examplesList.size()));
            // ustawianie wejscia
            setPointInNetwork(currentExample.point);

            //ustawianie wyjscia
            Sketch.network.get(Sketch.network.size() - 1).get(0).setWholeNetworkOutput(currentExample.angle1);
            Sketch.network.get(Sketch.network.size() - 1).get(1).setWholeNetworkOutput(currentExample.angle2);

            //obliczanie wyjscia perceptronu dla wszystkich ukrytych warstw w przód plus warstwy wyjsciowej
            feedForward();

            // obliczenie delt, propagacja wsteczna
            // obliczanie delty perceptronu dla ostatniej warstwy
            deltaForLastLayer();
            // dla kazdej warstwy ukrytej wstecz
            deltaForHiddenLayers();
            //poprawianie wag dla kazdej wartswy za wyjatkiem wyjsciowej
            correctWeights();
        }

        showStatus("learned");
    }

    public static Example answer(PointEx point) {
        setPointInNetwork(point);
        feedForward();
        double angle1 = Sketch.network.get(3).get(0).out;
        double angle2 = Sketch.network.get(3).get(1).out;
        return new Example(point, (angle1), (angle2));
    }

    public void random() {
        Sketch.examplesList = ExampleMaker.makeExamples();
        showStatus("done");
    }

    private static void showStatus(String status) {
        int lastButton = Sketch.buttonList.size() - 1;
        Sketch.buttonList.get(lastButton).setVal(status);
        Sketch.buttonList.get(lastButton).displayVal();
    }

}
