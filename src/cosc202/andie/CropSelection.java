package cosc202.andie;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * <p>
 * Crop Selection to crop an image based on the selected area.
 * </p>
 * 
 * @author Jay Lee
 */
public class CropSelection implements ImageOperation, java.io.Serializable {

    private int[] coordArr = new int[4];
    public CropSelection(){
        this.coordArr = calcCoordinates();
    }
    public BufferedImage apply(BufferedImage input){

        DrawApply.calcCoordinates();

        BufferedImage img = input.getSubimage(coordArr[0], coordArr[1], coordArr[2] - coordArr[0], coordArr[3] - coordArr[1]); //fill in the corners of the desired crop location here
        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
            return copyOfImage; //or use it however you want
    }
    public static int[] calcCoordinates() {
        int coordArr[] = new int[4];
        coordArr[0] = Math.min(CustomListener.getCurrentX(), CustomListener.getStartX());
        coordArr[1] = Math.min(CustomListener.getCurrentY(), CustomListener.getStartY());
        coordArr[2] = Math.max(CustomListener.getCurrentX(), CustomListener.getStartX());
        coordArr[3] = Math.max(CustomListener.getCurrentY(), CustomListener.getStartY());
        return coordArr;
    }
    

}
