package cosc202.andie;

import java.awt.image.BufferedImage;

public class RotateImageClockwise implements ImageOperation, java.io.Serializable{

    RotateImageClockwise(){}

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int imageType = input.getType();
    
        BufferedImage rotatedImage = new BufferedImage(height, width, imageType);

    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedImage.setRGB(height - y - 1,x , input.getRGB(x,y));
            }
        }
    
    
        return rotatedImage;
        }
    
    
}
