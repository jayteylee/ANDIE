package cosc202;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import cosc202.andie.ImageOperation;

public class ResizeFilter implements ImageOperation, java.io.Serializable {

    private int rWidth, rHeight;

    public ResizeFilter(int w, int h) {
        this.rWidth = w;
        this.rHeight = h;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(rWidth, rHeight, input.getType());;
        Graphics g = output.getGraphics();
        Image scaledImg;
        
        if(rWidth * rHeight < input.getWidth() * input.getHeight()) {
            //If resize image area is smaller then the image area.
            //Use area averaging.
            scaledImg = input.getScaledInstance(rWidth, rHeight, Image.SCALE_AREA_AVERAGING);
            g.drawImage(scaledImg, 0, 0, null);
        } else if(rWidth * rHeight >= input.getWidth() * input.getHeight()) {
            //If resize is larger.
            //Use smooth interpolation.
            scaledImg = input.getScaledInstance(rWidth, rHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImg, 0, 0, null);
        }

        return output;
    }
}
