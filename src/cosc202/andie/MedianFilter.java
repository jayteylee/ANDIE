package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class MedianFilter implements ImageOperation, java.io.Serializable {
    private int radius;

    MedianFilter(int radius){
        this.radius = radius;
    }

    MedianFilter(){
        this(1);
    }
    
    public BufferedImage apply(BufferedImage input) {

        ArrayList<Integer> local = new ArrayList<Integer>();

        ArrayList<Integer> aArray = new ArrayList<Integer>();
        ArrayList<Integer> rArray = new ArrayList<Integer>();
        ArrayList<Integer> gArray = new ArrayList<Integer>();
        ArrayList<Integer> bArray = new ArrayList<Integer>();


        for (int y = radius; y < input.getHeight()-radius; ++y) {
            for (int x = radius; x < input.getWidth()-radius; ++x) {
                local.add(input.getRGB(x,y));
                    for(int yRad = radius; yRad > 0; yRad--){
                        for(int xRad = radius; xRad > 0; xRad--){
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

                for(int px : local){
                    int a = (px & 0xFF000000) >> 24;
                    int r = (px & 0x00FF0000) >> 16;
                    int g = (px & 0x0000FF00) >> 8;
                    int b = (px & 0x000000FF);
                    
                    aArray.add(a);
                    rArray.add(r);
                    gArray.add(g);
                    bArray.add(b);

                }

                Collections.sort(aArray);
                Collections.sort(rArray);
                Collections.sort(gArray);
                Collections.sort(bArray);

                
                int argb = (aArray.get(aArray.size()/2) << 24) | (rArray.get(rArray.size()/2) << 16) | (gArray.get(gArray.size()/2) << 8) | bArray.get(bArray.size()/2);
              
                

                input.setRGB(x, y, argb);

                

                local.removeAll(local);
                aArray.removeAll(aArray);
                rArray.removeAll(rArray);
                gArray.removeAll(gArray);
                bArray.removeAll(bArray);

               

            }
        
        }
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        return output;

    }

}
