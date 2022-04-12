package cosc202.andie;

import java.awt.image.*;
import java.util.ArrayList;

/**
 * Image Operation to change the brightness and contrast of given image.
 * @author Jake Norton
 */

public class BrightnessAndContrast implements ImageOperation, java.io.Serializable {
    private double contrast;
    private double brightness;
    private ArrayList<Integer> origin = new ArrayList<Integer>();
    private ArrayList<Integer> newImage = new ArrayList<Integer>();
    public BrightnessAndContrast(int contrast, int brightness){
        this.contrast = contrast;
        this.brightness = brightness;
        this.origin.add(0);
        this.newImage.add(0);
    }
    /**
     * <p>
     * Apply brightness and contrast conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion from red, green, and blue values to greyscale uses a 
     * weighted average that reflects the human visual system's sensitivity 
     * to different wavelengths -- we are most sensitive to green light and 
     * least to blue.
     * </p>
     * 
     * @param input The image to be which is having its brightness and contrast changed
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage input){
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);
                argb = (a << 24) | (formula(r) << 16) | (formula(g) << 8) | formula(b);
                input.setRGB(x, y, argb);
            }
        }
        return input;
    }
    /**Method to calculate the changed value of the pixel
    * @param v separated argp value as input
    * @return The output of the contrast and brightness calculation
    */
    public int formula(int v){
        int output = (int)Math.round(((1.0 + contrast/100.0)*(v - 127.5) + (127.5*(1.0 + (brightness/100.0)))));
        if (output > 255){
            output = 255;
        }
        if (output < 0){
            output = 0;
        }
        return output;
    }
}
