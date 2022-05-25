package cosc202.andie;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Posterize implements ImageOperation, Serializable{
    private int[] colorBands;

    public Posterize(int colorBands[]) { 
        this.colorBands = colorBands;
    }

    private int[] splitARGB(int color) {
        int split[] = {(color & 0xFF000000) >>> 24, (color & 0x00FF0000) >>> 16, (color & 0x0000FF00) >>> 8, (color & 0x000000FF)};
        return split;
    }

    private int combineARGB(int split[]) {
        int finalColor = 0;
        for(int i = 0; i < split.length; i++) {
            finalColor = finalColor << 8;
            finalColor = finalColor | split[i];
        }
        return finalColor;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        if(colorBands != null && colorBands.length >= 1) {}
        for(int x = 0; x < output.getWidth(); x++) {
            for(int y = 0; y < output.getHeight(); y++) {
                int argb[] = splitARGB(input.getRGB(x, y));
                int closestColor[] = splitARGB(colorBands[0]);
                for(int i = 1; i < colorBands.length; i++) {
                    int bandColor[] =  splitARGB(colorBands[i]);
                    for(int j = 0; j < argb.length; j++) {
                        // For each index of a split color array {a, r, g, b}
                        if(Math.abs(argb[j] - bandColor[j]) <= Math.abs(argb[j] - closestColor[j])) {
                            closestColor[j] = bandColor[j];
                        }
                    }
                }
                output.setRGB(x, y, combineARGB(closestColor));
            }
        }
        return output;
    }
}