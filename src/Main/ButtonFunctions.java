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

    static public void learn() {
        System.out.println("learn");
        Random rand = new Random();

        for (int i = 0; i < Sketch.T; i++) {
            //losowanie przykladu
            Example currentExample = Sketch.examplesList.get(rand.nextInt(Sketch.examplesList.size()));
            // ustawianie wejscia
            Sketch.network.get(0).get(0).setOut(currentExample.point.x);
            Sketch.network.get(0).get(1).setOut(currentExample.point.y);

            //ustawianie wyjscia
            Sketch.network.get(Sketch.network.size() -1 ).get(0).setWholeNetworkOutput(currentExample.angle1);
            Sketch.network.get(Sketch.network.size() -1 ).get(1).setWholeNetworkOutput(currentExample.angle2);

            //obliczanie wyjscia perceptronu dla wszystkich ukrytych warstw w przód plus warstwy wyjsciowej
            for (int j = 1; j < Sketch.network.size(); j++) {
                //obliczenia dla kazdego neuronu w warstwie razem z biasu
                for (int k = 0; k < Sketch.network.get(j).size(); k++) {
                    double a = 0;
                    // liczenie sumy (a) dla neuronu z ukrytej warstwy
                    for (int m = 0; m < Sketch.network.get(j - 1).size(); m++) {
                        a += Sketch.network.get(j - 1).get(m).out * Sketch.network.get(j - 1).get(m).weights[k];
                    }
                    //zapamietanie sumy
                    Sketch.network.get(j).get(k).setA(a);
                    //obliczenie wyjscia z funkcji
                    a = Calculations.sigmoid(a);
                    // zapisanie wyjscia z funkcji
                    Sketch.network.get(j).get(k).setOut(a);
                }
            }

            // obliczanie delty perceptronu dla ostatniej warstwy
            for (int j = 0; j < Sketch.network.get(Sketch.network.size() - 1).size(); j++) {
                double t = Sketch.network.get(Sketch.network.size() -1 ).get(j).wholeNetworkOutput;
                double out = Sketch.network.get(Sketch.network.size() -1 ).get(j).out;
                double delta = (out - t) * out * (1 - out);
                Sketch.network.get(Sketch.network.size() -1 ).get(j).setDelta(delta);
            }

            // obliczenie delt, propagacja wsteczna
            // dla kazdej warstwy ukrytej wstecz
            for (int j = Sketch.network.size() - 2; j > 0; j--) {
                //dla kazdego neuronu w warstwie
                for (int k = 0; k < Sketch.network.get(j).size() - 1; k++) {
                    //oblicz delte
                    double delta = 0;
                    double out = Sketch.network.get(j).get(k).out;
                    double sum = 0;
                    //pomysl 1
//                    for (int m = 0; m < Sketch.network.get(j + 1).size(); m++) {
//                        double weight = Sketch.network.get(j).get(k).weights[m];
//                        double deltaBefore = Sketch.network.get(j).get(k).delta;
//                        delta += deltaBefore * weight * out * (1 - out);
//                    }

                    // pomysl 2
                    // policz sume wagi razy delty z wyzszej warstwy
                    for (int m = 0; m < Sketch.network.get(j + 1).size() - 1; m++) {
                        double weight = Sketch.network.get(j).get(k).weights[m];
                        double deltaBefore = Sketch.network.get(j).get(k).delta;
                        sum += weight * deltaBefore;
                    }
                    delta = sum * out * (1 - out);
                    Sketch.network.get(j).get(k).setDelta(delta);
                }
            }

            //poprawianie wag dla kazdej wartswy za wyjatkiem wyjsciowej
            for (int j = 0; j < Sketch.network.size() -1 ; j++) {
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

        showStatus("learned");
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
