package cosc202.andie;

import java.awt.image.*;

/**
 * <p> A custom implementation of image convolution.</p>
 * 
 * <p> Convolve is similiar to the java ConvolveOp class except the edges of an image are handled differently.
 * If a pixel to sample is outside of the image then the nearest pixel is used.</p> 
 * 
 * @author Jack Bredenbeck.
 */
public class Convolve {
    private Kernel kernel;

    /**<p>Constructs a new convolve object.</p>*/
    public Convolve(Kernel k) {
        kernel = k;
    }

    /**<p>Splits a color into different channels.</p>
     * 
     * <p>An integer color value is split into its different color channels and then each channel is placed into an array.</p> 
     * 
     * @param local The color value to be split.
     * @return An array of color channels.
     * */
    private int[] extractColors(int local) {
        int[] colors = {(local & 0xFF000000) >> 24,
        (local & 0x00FF0000) >> 16, (local & 0x0000FF00) >> 8, (local & 0x000000FF)};
        return colors;
    }

    /**<p>Combines color channels into one value.</p>
     * 
     * <p>Each color is recombined back into one integer value. If the color is outside the region 0-255
     * it is then clipped.</p>
     * @param colors The array of color channels to be recombined.
     * @return The one int value describing all of the color channels.
     */
    private int combineColors(int[] colors) {
        int finalColor = 0;
        for(int i = 0; i < colors.length; i++) {
            finalColor = finalColor << 8;
            // If there is an offset then add it to colors[i] here.
            if(colors[i] > 255) {
                //Clipping to large values.
                colors[i] = 255;
            }
            if(colors[i] < 0) {
                //Clipping to small values.
                colors[i] = 0;
            }
            finalColor = finalColor | colors[i];
        }
        return finalColor;
    }

    /**<p>Converts a int array to float array.</p>
     * @param in The array to be converted.
     * @return The converted array.
    */
    private float[] intsToFloats(int[] in) {
        float[] out = new float[in.length];
        for(int i = 0; i < in.length; i++) {
            out[i] = in[i];
        } 
        return out;
    }

    /**<p>Converts a float array to int array.</p>
     * @param in The array to be converted.
     * @return The converted array.
    */
    private int[] floatsToInts(float[] in) {
        int[] out = new int[in.length];
        for(int i = 0; i < in.length; i++) {
            out[i] = (int)Math.round(in[i]);
        }
        return out;
    }

    /**<p>Applies a convolution filter to an image.</p>
     * <p>The input image is used as the source for the convolution and the result of the convolution
     * is put in the output image. If pixels are sampled that are outside of the bounds of the image then the nearest
     * pixels are used instead.</p>
     * 
     * @param in The source image of the convolution.
     * @param out The destination image for the result of the convolution.
     */
    public void filter(BufferedImage in, BufferedImage out) {
        for(int x = 0; x < in.getWidth(); x++) {
            for(int y = 0; y < in.getHeight(); y++) {
                float[] oColors = {0.0f, 0.0f, 0.0f, 0.0f};
                for(int kx = 0; kx < kernel.getWidth(); kx++) {
                    for(int ky = 0; ky < kernel.getHeight(); ky++) {
                        int sampleX = x - kernel.getXOrigin() + kx;
                        int sampleY = y - kernel.getYOrigin() + ky;
                        if(sampleX < 0) {
                            sampleX = 0;
                        }
                        if(sampleY < 0) {
                            sampleY = 0;
                        }
                        if(sampleX >= in.getWidth()) {
                            sampleX = in.getWidth() - 1;
                        }
                        if(sampleY >= in.getHeight()) {
                            sampleY = in.getHeight() -1;
                        }
                        float colors[] = intsToFloats(extractColors(in.getRGB(sampleX, sampleY)));
                        // Add kernel products to origin.
                        float kData[] = kernel.getKernelData(null);
                        for(int i = 0; i < 4; i++) {
                            //For each color (a, r, g ,b)
                            oColors[i] += colors[i] * kData[ky * kernel.getWidth() + kx];
                        }

                    }
                }
                //Restore the modified origin colors to the orginal origin colors.
                out.setRGB(x, y, combineColors(floatsToInts(oColors)));
            }
        }
    }
}