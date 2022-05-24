package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.Graphics;


/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the median of the
 * pixels in a surrounding neighborhood.
 * </p>
 * 
 * @author Jay Lee
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {
    private int radius;


    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the local neighborhood (excluding the centre pixel).
     * A size of 1 is a 3x3 neighborhood, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    MedianFilter(int radius){
        this.radius = radius;
    }


    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MedianFilter(){
        this(1);
    }
    

    /**
     * <p>
     * Apply median filter to an image.
     * </p>
     * 
     * <p>
     * Every pixel in the image is set to the median a, r, g and b value of
     * it's local neighborhood.
     * </p>
     * 
     * @param input The image to which the median filter is going to be applied to.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage inputs) {

        BufferedImage input = new BufferedImage(inputs.getWidth()+(radius*2), inputs.getHeight()+(radius*2), BufferedImage.TYPE_INT_ARGB);
        Graphics grap = input.getGraphics();
        grap.drawImage(inputs,0,0,input.getWidth(),input.getHeight(),null);
        grap.drawImage(inputs, radius, radius, null);
        grap.dispose();


        ArrayList<Integer> local = new ArrayList<Integer>(); //ArrayList for local neighborhood

        ArrayList<Integer> aArray = new ArrayList<Integer>(); //ArrayLists for all a, r, g, b values in the local neighborhood
        ArrayList<Integer> rArray = new ArrayList<Integer>();
        ArrayList<Integer> gArray = new ArrayList<Integer>();
        ArrayList<Integer> bArray = new ArrayList<Integer>();


        for (int y = radius; y < input.getHeight()-radius; ++y) {
            for (int x = radius; x < input.getWidth()-radius; ++x) { //iterate through every pixel in image (except edge cases)
                local.add(input.getRGB(x,y));
                    for(int yRad = radius; yRad > 0; yRad--){
                        for(int xRad = radius; xRad > 0; xRad--){ //iterate through local neighborhood of pixel and add to local ArrayList
                            local.add(input.getRGB(x-xRad, y));
                            local.add(input.getRGB(x+xRad, y));
                            local.add(input.getRGB(x,y-yRad));
                            local.add(input.getRGB(x,y+yRad));
                            local.add(input.getRGB(x+xRad,y+yRad));
                            local.add(input.getRGB(x-xRad,y+yRad));
                            local.add(input.getRGB(x+xRad,y-yRad));
                            local.add(input.getRGB(x-xRad,y-yRad));
                        }
                    }

                for(int px : local){ //get the a, r, g, b values of the local neighborhood and add to corresponding ArrayList
                    int a = (px & 0xFF000000) >> 24;
                    int r = (px & 0x00FF0000) >> 16;
                    int g = (px & 0x0000FF00) >> 8;
                    int b = (px & 0x000000FF);
                    
                    aArray.add(a);
                    rArray.add(r);
                    gArray.add(g);
                    bArray.add(b);

                }

                Collections.sort(aArray); //sort the ArrayLists
                Collections.sort(rArray);
                Collections.sort(gArray);
                Collections.sort(bArray);

                
                int argb = (aArray.get(aArray.size()/2) << 24) | (rArray.get(rArray.size()/2) << 16) | (gArray.get(gArray.size()/2) << 8) | bArray.get(bArray.size()/2);
              
                

                input.setRGB(x, y, argb); //set pixel to the median argb of it's neighborhood

                

                local.removeAll(local);
                aArray.removeAll(aArray);
                rArray.removeAll(rArray);
                gArray.removeAll(gArray);
                bArray.removeAll(bArray); //clear all ArrayLists

               

            }
        
        }
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        BufferedImage img = output.getSubimage(radius, radius, inputs.getWidth(), inputs.getHeight());

        return img;

    }

}
