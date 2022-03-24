package cosc202.andie;

import java.awt.image.*;

public class SharpenFilter implements ImageOperation, java.io.Serializable {

    public SharpenFilter() {
        //Empty
    }

    public BufferedImage apply(BufferedImage input) {
        float kernelArray[] = {0.0f, -0.5f, 0.0f, -0.5f, 3.0f, -0.5f, 0.0f, -0.5f, 0.0f};
        Kernel kernel = new Kernel(3, 3, kernelArray);
        ConvolveOp cOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        cOp.filter(input, output);
        return output;
    }
    
}
