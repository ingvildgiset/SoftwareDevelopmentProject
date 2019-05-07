package IO;

import Image.Image;
import Shapes.Plot;
import Shapes.Rectangle;
import Shapes.Line;
import Shapes.Ellipse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;
import Config.Config;




public abstract class VecFileManaging {

    public static Image readVecFile(String fileName) throws FileNotFoundException {
        //Create Image
        //config-fil med defaul størrelser her
        Image newImage = new Image(Config.IMAGE_HEIGHT, Config.IMAGE_WIDTH);
        Color drawColour = Color.black; //default colour
        Color fillColor = Color.white;
        boolean fill = false;

        //her må vi kanskje legge til en config-fil eller noe som gjør at man kan legge til en annen filsti
        File file = new File("/Users/juliehaga/Desktop/SoftwareDevelopmentProject/Paint/src/Data/" + fileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] command = sc.nextLine().split(" ");

            switch (command[0]){
                case "PEN":
                    drawColour = Color.decode(command[1]);
                    break;
                case "FILL":
                    if (command[1].equals("OFF")){
                        fill = false;
                    } else {
                        fill = true;
                        fillColor = Color.decode(command[1]);
                    }
                    break;
                case "LINE":
                    Line newLine = new Line(Double.parseDouble(command[1])*Config.IMAGE_WIDTH, Double.parseDouble(command[2])*Config.IMAGE_HEIGHT, Double.parseDouble(command[3])*Config.IMAGE_WIDTH, Double.parseDouble(command[4])*Config.IMAGE_HEIGHT, drawColour);
                    newImage.addShape(newLine);
                    break;
                case "PLOT":
                    Plot newPlot = new Plot(Double.parseDouble(command[1])*Config.IMAGE_WIDTH, Double.parseDouble(command[2])*Config.IMAGE_HEIGHT, drawColour);
                    newImage.addShape(newPlot);
                    break;
                case "RECTANGLE":
                    Rectangle newRec = new Rectangle(Double.parseDouble(command[1])*Config.IMAGE_WIDTH, Double.parseDouble(command[2])*Config.IMAGE_HEIGHT, Double.parseDouble(command[3])*Config.IMAGE_WIDTH, Double.parseDouble(command[4])*Config.IMAGE_HEIGHT, drawColour, fill, fillColor);
                    newImage.addShape(newRec);
                    break;
                case "ELLIPSE":
                    Ellipse newEllipse = new Ellipse(Double.parseDouble(command[1])*Config.IMAGE_WIDTH, Double.parseDouble(command[2])*Config.IMAGE_HEIGHT, Double.parseDouble(command[3])*Config.IMAGE_WIDTH, Double.parseDouble(command[4])*Config.IMAGE_HEIGHT, drawColour, fill, fillColor);
                    newImage.addShape(newEllipse);
                    break;
                default:
                    System.out.println(command[0]);
                    System.out.println("Reading file, invalid command");
            }
        }
        return newImage;

    }

    public static void main(String[] args) {
        try {
            Image rectImage = readVecFile("ellipse.vec");
            int a = 0;
        }
        catch (java.io.FileNotFoundException e){
            System.out.println(e);
    }

}


}

