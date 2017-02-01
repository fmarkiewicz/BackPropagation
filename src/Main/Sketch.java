
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

public class Sketch extends PApplet {

    int numberOfHiddenLayers = 2;
    int numberOfNeuronsForHiddenLayer = 10;
    
    static int T = 1000000;
    static double learnStatic = 0.002;
    static double xMax;
    static double yMax;
    
    int buttonsX = 10;
    int buttonsY = 500;
    int buttonsHeight = 30;
    int buttonsWidth = 120;

    static final int FIELD_X = 0;
    static final int FIELD_Y = 0;
    static final int FIELD_WIDTH = 500;
    static final int FIELD_HEIGHT = 450;

    static List<MyButton> buttonList = new ArrayList<>();
    static List<Field> fieldList = new ArrayList<>();
    public static List<Example> examplesList = new ArrayList<>();
    MyRobot robot = new MyRobot();
    public static List<List<Perceptron>> network = new ArrayList<>();
    MyButton tmpButton;
    MyButton showExamples;

    static public void main(String args[]) {
        PApplet.main(new String[]{"Main.Sketch"});
    }

    @Override
    public void setup() {
        textSize(25);

        List<Perceptron> inputLayer = new ArrayList<>();
        List<Perceptron> outputLayer = new ArrayList<>();
        // LOSOWANIE WAG
        // tworzenie warstwy wejsciowej
        for (int i = 0; i < 3; i++) {
            inputLayer.add(new Perceptron(numberOfNeuronsForHiddenLayer));
            //ustawianie biasu
            if (i == 2) {
                inputLayer.get(i).setA(1);
            }
        }
        network.add(inputLayer);

        // tworzenie ostatniej warstwy
        for (int i = 0; i < 2; i++) {
            outputLayer.add(new Perceptron(0));
        }

        // uzupelnianie ukrytych warstw
        for (int i = 0; i < numberOfHiddenLayers; i++) {
            List<Perceptron> hiddenLayer = new ArrayList<>();
            // warunek sprawdzajacy czy to ostatnia z ukrytych warstw
            if (i == numberOfHiddenLayers - 1) {
                for (int j = 0; j < numberOfNeuronsForHiddenLayer; j++) {
                    hiddenLayer.add(new Perceptron(2));
                    //ustawianie biasu
                    if (j == numberOfNeuronsForHiddenLayer - 1) {
                        hiddenLayer.get(j).setA(1);
                    }
                }
            } else {
                for (int j = 0; j < numberOfNeuronsForHiddenLayer; j++) {
                    hiddenLayer.add(new Perceptron(numberOfNeuronsForHiddenLayer));
                    //ustawianie biasu
                    if (j == numberOfNeuronsForHiddenLayer - 1) {
                        hiddenLayer.get(j).setA(1);
                    }
                }
            }
            network.add(hiddenLayer);
        }
        network.add(outputLayer);

        buttonList.add(new MyButton("random", this));
        buttonList.add(new MyButton("learn", this));

//        tmpButton = new MyButton("learn", this);
//        tmpButton.setCoordinates(10, 600, 30, 300);
//        tmpButton.display();
        buttonList.add(new MyButton("", this));

        for (int i = 0; i < buttonList.size(); i++) {
            MyButton but = buttonList.get(i);
            but.setCoordinates(buttonsX, buttonsY + buttonsHeight * i, buttonsHeight, buttonsWidth);
            but.display();
        }

        fieldList.add(new Field(FIELD_X, FIELD_Y, FIELD_HEIGHT, FIELD_WIDTH, this));
        fieldList.get(0).display();

    }

    @Override
    public void settings() {
        size(1000, 700);  // size always goes first!
//  surface.setResizable(true);
    }

    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {
        update(mouseX, mouseY);
    }

    @Override
    public void mouseDragged() {
        if (fieldList.get(0).clicked(mouseX, mouseY)) {
            update(mouseX, mouseY);
        }
    }

    void update(int x, int y) {
        if (fieldList.get(0).clicked(x, y)) {
            fieldList.get(0).display();
            
            PointEx point = new PointEx((double)x/xMax, (double)y/yMax);
            Example answer = ButtonFunctions.answer(point);
            robot.drawHand(Example.getExample(answer), this);
            
//            PointEx point = new PointEx((double)x, (double)y);
//            Example answer = ButtonFunctions.answer(point);
//            robot.drawHand(answer, this);
            

//            for (Example ex : examplesList) {
//                robot.drawHand(ex, this);
//            }
//        } else if (tmpButton.clicked(x, y)) {
//            ButtonFunctions.learn();
        } else {
            for (MyButton but : buttonList) {
                if (but.clicked(x, y)) {
                    clearButtonClicked();
                    but.func();
                    but.displayClicked();
                }
            }
        }
    }

    @Override
    //klawiatura
    public void keyPressed() {
        if ((int) key > 47 && (int) key < 57) {
            int a = (int) key - 48;
            System.out.println(a);

        }
    }

    public void clearButtonClicked() {
        for (MyButton b : buttonList) {
            b.display();
        }
    }
}
