package cosc202.andie;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.awt.BasicStroke;

public class DrawApply implements ImageOperation, Serializable{
    private int current = 0;
    private Color colour;
    private int[] coordArr = new int[4];
    private int[] lineArr = new int[4];
    private double zoom;
    protected static int[][] xArr, yArr;
    protected static int[] numPoints;

    public DrawApply(ImagePanel panel, int current, Color colour ) {
        this.coordArr = calcCoordinates();
        this.lineArr = lineCoordinates();
        this.current = current;
        this.colour = colour;
        numPoints = new int[1];
        xArr = new int[1][10000];
        yArr = new int [1][10000];
        
    }
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
        //lineArr[0] = CustomListener.getStartX()* Andie.imagePanel.getZoom();
        temp = (CustomListener.getStartX()* Andie.imagePanel.getZoom())/100;
        lineArr[0] = (int)temp;
        temp = (CustomListener.getStartY()* Andie.imagePanel.getZoom())/100;
        lineArr[1] = (int)temp;
        temp = (CustomListener.getCurrentX()* Andie.imagePanel.getZoom())/100;
        lineArr[2] = CustomListener.getCurrentX();
        temp = (CustomListener.getCurrentY()* Andie.imagePanel.getZoom())/100;
        lineArr[3] = CustomListener.getCurrentY();
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
            }else if (current == MouseActions.FREEDRAW) {
                outputGraphics.setStroke(new BasicStroke(6));
                outputGraphics.drawPolyline(xArr[0], yArr[0], numPoints[0]);
            }
            return output;
        }
        

}
    

