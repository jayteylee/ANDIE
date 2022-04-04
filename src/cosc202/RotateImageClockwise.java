package cosc202;

import java.awt.image.BufferedImage;

public class RotateImageClockwise {

    public BufferedImage apply(BufferedImage input) {
        int newHeight = input.getWidth();
        int newWidth = input.getHeight();
        int imageType = input.getType();
    
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, imageType);
    
        return rotatedImage;
        }
    
    
}
