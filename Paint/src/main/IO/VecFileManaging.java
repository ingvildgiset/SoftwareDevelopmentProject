package IO;
//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.awt.*;
//dependencies
import Shapes.*;
import Shapes.Rectangle;
import Shapes.Polygon;
import SquareImage.SquareImage;

/**
 * Class to handle reading and writing to file. Contains only static methods, should not be instantiated.
 */
public class VecFileManaging {
    /**
     * Creates a squareImage object based on the vec commands in file.
     * @param filePath file to be read
     * @return SquareImage object.
     * @throws FileNotFoundException
     */
    public static SquareImage constructImageFromVecFile(String filePath) throws FileNotFoundException {
        //creating a new image
        SquareImage newImage = new SquareImage(0);

        //default values
        Color drawColour = Color.black;
        Color fillColor = Color.white;
        boolean fill = false;

        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] command = sc.nextLine().split(" ");

            //read in a figure from one line
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
                    Line newLine = new Line(Double.parseDouble(command[1]), Double.parseDouble(command[2]), Double.parseDouble(command[3]), Double.parseDouble(command[4]), drawColour);
                    newImage.addShape(newLine);
                    break;
                case "PLOT":
                    Plot newPlot = new Plot(Double.parseDouble(command[1]), Double.parseDouble(command[2]), drawColour);
                    newImage.addShape(newPlot);
                    break;
                case "RECTANGLE":
                    Rectangle newRec = new Rectangle(Double.parseDouble(command[1]), Double.parseDouble(command[2]), Double.parseDouble(command[3]), Double.parseDouble(command[4]), drawColour, fill, fillColor);
                    newImage.addShape(newRec);
                    break;
                case "ELLIPSE":
                    Ellipse newEllipse = new Ellipse(Double.parseDouble(command[1]), Double.parseDouble(command[2]), Double.parseDouble(command[3]), Double.parseDouble(command[4]), drawColour, fill, fillColor);
                    newImage.addShape(newEllipse);
                    break;
                case "POLYGON":
                    int numberOfPoints = command.length/2;
                    double[] xVal = new double[numberOfPoints];
                    double[] yVal = new double[numberOfPoints];
                    int j = 0;

                    for (int i = 1; i < command.length-1; i += 2){
                        xVal[j] = Double.parseDouble(command[i]);
                        yVal[j] = Double.parseDouble(command[i+1]);
                        j++;
                    }

                    Polygon newPolygon = new Polygon(numberOfPoints, xVal, yVal, drawColour, fill, fillColor);
                    newImage.addShape(newPolygon);

                    break;
                default:
                    System.out.println(command[0]);
                    System.out.println("Invalid command");
            }
        }
        return newImage;

    }

    /**
     * Creates a vec file with all commands corresponding to a squareImage object.
     * @param filename filename for new file to be saved
     * @param image squareImage to be saved
     * @throws IOException
     */
    public static void createVecFileFromImage(String filename, SquareImage image) throws IOException {
        FileWriter fw = new FileWriter(filename + ".vec");
        List<Shapes> shapes = image.getShapes();

        for(Shapes shapeObj: shapes){
            String command = shapeObj.toVecFormat();
            fw.write(command);
        }
        fw.close();
    }

}



