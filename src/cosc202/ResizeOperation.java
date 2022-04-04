package cosc202;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import cosc202.andie.ImageOperation;

public class ResizeOperation implements ImageOperation, java.io.Serializable {

    private int rWidth, rHeight;

    /**
     * <p> Contstructs a new ResizeOperation. </p>
     * @param w The new width of an image after applying a resize operation.
     * @param h The new height of an image after applying a resize operation.
     */
    public ResizeOperation(int w, int h) {
        this.rWidth = w;
        this.rHeight = h;
    }

    /**
     * <p> Resizes an input BufferedImage to desired dimensions. </p>
     * 
     * <p> Resizes an input BufferedImage to the dimensions specified in the constructor. 
     * If area of the new width and height is smaller then the original area, then an Area-Averaging method is
     * used to scale the image. If the area is greater then the original area, then an Smooth interpolation method is used to
     * scale the image.</p>
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(rWidth, rHeight, input.getType());
        Graphics g = output.getGraphics(); // Graphics object used to draw the scaled image to the output.
        Image scaledImg;
        
        if(rWidth * rHeight < input.getWidth() * input.getHeight()) {
            //If resize image area is smaller then the image area.
            //Use area averaging.
            scaledImg = input.getScaledInstance(rWidth, rHeight, Image.SCALE_AREA_AVERAGING);
            //ScaledImg is of type image so is needed to be drawn to the output.
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
