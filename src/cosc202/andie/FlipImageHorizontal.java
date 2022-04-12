package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p> An action that enables the image to be flip horizontally.</p>
 * 
 * @author Daniel Lee
 */

public class FlipImageHorizontal implements ImageOperation, java.io.Serializable {
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int imageType = input.getType();

        BufferedImage flipImage = new BufferedImage(width, height, imageType);

        //change the value of x to achieve the horizontal flip
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                flipImage.setRGB((width - 1) - x, y, input.getRGB(x, y));
            }
        }
        
        return flipImage;
    }

    
}
