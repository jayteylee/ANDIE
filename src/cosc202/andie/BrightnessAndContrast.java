package cosc202.andie;

import java.awt.image.*;

/**
 * Image Operation to change the brightness and contrast of given image.
 */

public class BrightnessAndContrast implements ImageOperation, java.io.Serializable {
    private double contrast;
    private double brightness;
    private BufferedImage temporaryImage; 
    
    public BrightnessAndContrast(int contrast, int brightness){
        this.contrast = contrast;
        this.brightness = brightness;
    }
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
    //Method to calculate the changed value of the pixel
    public int formula(int v){
        int output = (int)((1.0 + contrast/100.0)*(v - 127.5) + (127.5*(1.0 + (brightness/100))));
        if (output > 255){
            output = 255;
        }
        return output;
    }
}
