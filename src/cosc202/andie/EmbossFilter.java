package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;
/**
 * <p> A filter that apply emboss to image.</p>
 * 
 * <p> The emboss filter applys a convolution operation to the image. </p>
 * 
 * @author Daniel Lee
 */
public class EmbossFilter implements ImageOperation, java.io.Serializable { 
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * Construct a Emboss filter with the given size.
     * @param radius The radius of the newly constructed Emboss filter.
     */

    EmbossFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Emboss filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Emboss Blur filter has radius 1.
     * </p>
     * 
     * @see EmbossFilter(int)
     */
    //default constructor
    EmbossFilter() {
        this(1);
    }

    /**<p>Applys the Emboss filter to an input BufferedImage.</p>
     * 
     * <p>A convolution is applied to the image using a special kernel. Every pixels final colour value is influenced by the 
     * neighbouring pixels colour values. The amount a which the neighbours effect the color is specified in a kernel.
     * </p>
     * 
     * @param input The image to apply the filter to.
     * 
     * @return Returns the an image with the Emboss filter applied.
    */

    public BufferedImage apply (BufferedImage input) {
        int size = (2*radius + 1) * (2*radius + 1);
        float[] array = new float[size];

        // Make a filter from the array
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);

        //Emboss algorithm here

        // Apply this as a convolution - same code as in MeanFilter
        Convolve convOp = new Convolve(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
 
        return output;
    }
}
