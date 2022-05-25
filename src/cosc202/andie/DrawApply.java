package cosc202.andie;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.awt.BasicStroke;
/**
 * <p>
 * ImageOperation to add drawn shapes onto an image
 * </p>
 * 
 * <p>
 * The user can draw lines, filled and unfilled rectanges and ovals and change
 * the colour based on HSV values.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jake Norton
 */
public class DrawApply implements ImageOperation, Serializable{
    private int current = 0;
    private Color colour;
    private int[] coordArr = new int[4];
    private int[] lineArr = new int[4];
    protected int[] xArr, yArr;

    public DrawApply(int current, Color colour) {
        this.coordArr = calcCoordinates();
        this.lineArr = lineCoordinates();
        this.current = current;
        this.colour = colour;
        this.xArr = new int [CustomListener.getxList().size()];
        this.yArr = new int [CustomListener.getyList().size()];
                for(int i = 0; i < CustomListener.getxList().size(); i++){
                    this.xArr[i] = CustomListener.getxList().get(i);
                    this.yArr[i] = CustomListener.getyList().get(i);
                    }
    }
    /**
     * A method to calculate the correct coordinates to use for the drawing of rectangles and ovals.
     * It chooses the smaller of the x,y coordinates for the start values and the larger for the end.
     * @return coordArr, an array container the first start x,y followed by the end x,y
     */
    public static int[] calcCoordinates() {
        int coordArr[] = new int[4];
        coordArr[0] = (int) (Math.min(CustomListener.getCurrentX(), CustomListener.getStartX()) / ((Andie.imagePanel.getZoom())/100));
        coordArr[1] = (int) (Math.min(CustomListener.getCurrentY(), CustomListener.getStartY()) / ((Andie.imagePanel.getZoom())/100));
        coordArr[2] = (int) (Math.max(CustomListener.getCurrentX(), CustomListener.getStartX()) / ((Andie.imagePanel.getZoom())/100));
        coordArr[3] = (int) (Math.max(CustomListener.getCurrentY(), CustomListener.getStartY()) / ((Andie.imagePanel.getZoom())/100));
        return coordArr;
    }
    public static int[] lineCoordinates() {
        int lineArr[] = new int[4];
        double temp;
        temp = CustomListener.getStartX()/ (Andie.imagePanel.getZoom()/100);
        lineArr[0] = (int)temp;
        temp = CustomListener.getStartY()/ (Andie.imagePanel.getZoom()/100);
        lineArr[1] = (int)temp;
        temp = CustomListener.getCurrentX()/ (Andie.imagePanel.getZoom()/100);
        lineArr[2] = (int)temp;
        temp = CustomListener.getCurrentY()/ (Andie.imagePanel.getZoom()/100);
        lineArr[3] = (int)temp;
        return lineArr;
    }
    @Override
    public BufferedImage apply(BufferedImage input) {
            BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                    input.isAlphaPremultiplied(), null);
            Graphics2D outputGraphics = output.createGraphics();
            outputGraphics.setColor(this.colour);
            if (current == MouseActions.DRAWRECT) {
                outputGraphics.drawRect(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWFILLRECT) {
                outputGraphics.fillRect(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWOVAL) {
                outputGraphics.drawOval(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWFILLOVAL) {
                outputGraphics.fillOval(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            }else if (current == MouseActions.DRAWLINE) {
                outputGraphics.drawLine(lineArr[0], lineArr[1],
                        lineArr[2],lineArr[3]);
                    }
            else if (current == MouseActions.FREEDRAW) {
                outputGraphics.setStroke(new BasicStroke(6));
                outputGraphics.drawPolyline( this.xArr, this.yArr, this.xArr.length); 
            }
            return output;
        }
        

}
    

