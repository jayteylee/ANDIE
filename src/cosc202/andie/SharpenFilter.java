package cosc202.andie;

import java.awt.image.*;

/**
 * <p> A filter that sharpens an input image.</p>
 * 
 * <p> The sharpen filter applys a convolution operation to the image. </p>
 * 
 * @author Jack Bredenbeck
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {

    /**<p>Constructs a new SharpenFilter.</p>*/
    public SharpenFilter() {
        //Empty
    }

    /**<p>Applys the sharpen filter to an input BufferedImage.</p>
     * 
     * <p>A convolution is applied to the image using a special kernel. Every pixels final colour value is influenced by the 
     * neighbouring pixels colour values. The amount a which the neighbours effect the color is specified in a kernel.
     * </p>
     * 
     * @param input The image to apply the filter to.
     * 
     * @return Returns the an image with the Sharpen filter applied.
    */
    public BufferedImage apply(BufferedImage input) {
        float kernelArray[] = {0.0f, -0.5f, 0.0f, -0.5f, 3.0f, -0.5f, 0.0f, -0.5f, 0.0f};
        Kernel kernel = new Kernel(3, 3, kernelArray);
        ConvolveOp cOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        cOp.filter(input, output);
        return output;
    } 
    
}
