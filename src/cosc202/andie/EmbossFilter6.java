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
public class EmbossFilter6 implements ImageOperation, java.io.Serializable { 

    /**<p>Applys the Emboss filter to an input BufferedImage.</p>
     * 
     * <p>A convolution is applied to the image using a special kernel. Every pixels final colour value is influenced by the 
     * neighbouring pixels colour values. The amount a which the neighbours effect the color is specified in a kernel.
     * </p>
     * 
     * <p>This emboss goes from bottom right to top left. </p>
     * 
     * @param input The image to apply the filter to.
     * 
     * @return Returns the an image with the Emboss filter applied.
    */

    public BufferedImage apply (BufferedImage input) {
        float[] array = {-1, 0, 0,
                        0, 0, 0,
                        0, 0, +1};
        
        // Make a filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        
        // Apply this as a convolution - same code as in MeanFilter
        Convolve convo = new Convolve(kernel, true);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convo.filter(input, output);

        return output;
    }


    
}
