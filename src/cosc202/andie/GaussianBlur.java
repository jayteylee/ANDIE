package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;

public class GaussianBlur implements ImageOperation, java.io.Serializable {
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * Construct a GaussianBlur filter with the given size.
     * @param radius The radius of the newly constructed GaussianBlur filter.
     */

    GaussianBlur(int radius) {
        this.radius = radius;    
    }

    //default constructor
    public GaussianBlur() {}
    
    public BufferedImage apply (BufferedImage input) {
        // The values for the kernel as a 9-element array
        float [] array = { 0 , 1/8.0f, 0 , 1/8.0f, 1/2.0f, 1/8.0f, 0 , 1/8.0f, 0 };

        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);

        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
 
        return output;
    }
    
}
