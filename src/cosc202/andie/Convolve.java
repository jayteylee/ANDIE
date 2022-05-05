package cosc202.andie;

import java.awt.image.*;

public class Convolve {
    private Kernel kernel;
    public Convolve(Kernel k) {
        kernel = k;
    }

    private int[] extractColors(int local) {
        int[] colors = {(local & 0xFF000000) >> 24,
        (local & 0x00FF0000) >> 16, (local & 0x0000FF00) >> 8, (local & 0x000000FF)};
        return colors;
    }

    private int combineColors(int[] colors) {
        int finalColor = 0;
        for(int i = 0; i < colors.length; i++) {
            finalColor = finalColor << 8;
            // If there is an offset then add it to colors[i] here.
            if(colors[i] > 255) {
                colors[i] = 255;
            }
            if(colors[i] < 0) {
                colors[i] = 0;
            }
            finalColor = finalColor | colors[i];
        }
        return finalColor;
    }

    private float[] intsToFloats(int[] in) {
        float[] out = new float[in.length];
        for(int i = 0; i < in.length; i++) {
            out[i] = in[i];
        } 
        return out;
    }

    private int[] floatsToInts(float[] in) {
        int[] out = new int[in.length];
        for(int i = 0; i < in.length; i++) {
            out[i] = (int)Math.round(in[i]);
        }
        return out;
    }

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