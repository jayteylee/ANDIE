package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p> An action to rotate an image anti-clockwise.</p>
 * 
 * @author Jay Lee
 */
public class RotateImageAntiClockwise implements ImageOperation, java.io.Serializable{

    RotateImageAntiClockwise(){}

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int imageType = input.getType();
    
        BufferedImage rotatedImage = new BufferedImage(height, width, imageType);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setRGB(y, width - x - 1, input.getRGB(x,y));
            }
        }
             
    
        return rotatedImage;
        }
    
    
}
