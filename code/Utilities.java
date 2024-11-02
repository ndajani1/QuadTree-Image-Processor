import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public final class Utilities
{
    // It reads a PGB image from a file and stores the data in an array of type Short.
    // We use Short instead of Integer because the values of grayscale pixels are in the range of 0-255
    // Time Complexity: O(n) where n is the number of pixels
    public static Short[][] loadData(String filename)
    {
        Short[][] pixels = null;
        try (Scanner scnr = new Scanner(new File(filename))) {
            scnr.nextLine();
            int width = scnr.nextInt();
            int height = scnr.nextInt();
            scnr.nextInt();

            pixels = new Short[height][width];
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    pixels[row][col] = scnr.nextShort();
                }
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
        return pixels;
    }

    // It writes a QuadTreeImage into a PGM file.
    // There is no time-complexity restriction for this method. This means that you are allowed to call the getColor method if you want.
    // This is the only place in your entire code that you can call the getColor method.
    public static <Pixel extends Number> void exportImage(QuadTreeImage<Pixel> image, String filename)
    {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.println("P2");
            int size = image.getSize(); 
            writer.println(size + " " + size);
            writer.println(255);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.print(image.getColor(j, i).intValue() + " ");
                }
                writer.println(); 
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot create file: " + filename);
            e.printStackTrace();
        }
    }
}
