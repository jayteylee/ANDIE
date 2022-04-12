package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;
//import java.util.*;
/**
 * <p> A filter that apply gaussian blur to image.</p>
 * 
 * <p> The gaussian blur filter applys a convolution operation to the image. </p>
 * 
 * @author Daniel Lee
 */

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

    /**
     * <p>
     * Construct a Gaussian Blur filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Gaussian Blur filter has radius 1.
     * </p>
     * 
     * @see GaussianBlur(int)
     */
    //default constructor
    GaussianBlur() {
        this(1);
    }
    
    /**<p>Applys the Gaussian Blur filter to an input BufferedImage.</p>
     * 
     * <p>A convolution is applied to the image using a special kernel. Every pixels final colour value is influenced by the 
     * neighbouring pixels colour values. The amount a which the neighbours effect the color is specified in a kernel.
     * </p>
     * 
     * @param input The image to apply the filter to.
     * 
     * @return Returns the an image with the Gaussian Blur filter applied.
    */

    public BufferedImage apply (BufferedImage input) {
        int size = (2*radius+1) * (2*radius +1);
        float [] array = new float[size];

        //the algorithm of the gaussian blur equation, defining the variables
        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        
        //computing the algorithm of gaussian blur
        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            array[index] = (float) Math.exp(-distance / twoSigmaSquare)/ sigmaRoot;
            total += array[index];
        }

        for (int i = 0; i < array.length; i++) {
            array[i] /= total;
        }
        
        // Make a filter from the array
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);

        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
 
        return output;
    }
    
}
