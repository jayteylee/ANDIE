package cosc202;

import java.awt.image.BufferedImage;
import java.util.*;

public class RotateImageClockwise {

    public BufferedImage apply(BufferedImage input) {
        int newHeight = input.getWidth();
        int newWidth = input.getHeight();
        int imageType = input.getType();
    
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, imageType);

    
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                rotatedImage.setRGB(y, x, input.getRGB(x,y));
            }
        }
    
    
        return rotatedImage;
        }
    
    
}
