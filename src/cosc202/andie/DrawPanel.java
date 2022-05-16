package cosc202.andie;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DrawPanel extends ImagePanel implements ImageOperation {
    private static int current = 0;

    public DrawPanel() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MouseActions.drawing && CustomListener.isRunning()) {
            if (current == MouseActions.DRAWRECT) {
                g.drawRect(calcCoordinates()[0], calcCoordinates()[1],
                    calcCoordinates()[2] - calcCoordinates()[0],
                    calcCoordinates()[3] - calcCoordinates()[1]);
            }else if(current == MouseActions.DRAWFILLRECT){
                g.fillRect(calcCoordinates()[0], calcCoordinates()[1],
                    calcCoordinates()[2] - calcCoordinates()[0],
                    calcCoordinates()[3] - calcCoordinates()[1]);
            }else if(current == MouseActions.DRAWOVAL){
                g.drawOval(calcCoordinates()[0], calcCoordinates()[1],
                    calcCoordinates()[2] - calcCoordinates()[0],
                    calcCoordinates()[3] - calcCoordinates()[1]);
            }else if(current == MouseActions.DRAWFILLOVAL){
                g.fillOval(calcCoordinates()[0], calcCoordinates()[1],
                    calcCoordinates()[2] - calcCoordinates()[0],
                    calcCoordinates()[3] - calcCoordinates()[1]);
            }
        }else{
           // super.apply(EditableImage.getCurrentImage());
        }
    }
    public int[] calcCoordinates(){
        int[] arr = new int[4];
        arr[0] = Math.min(CustomListener.getCurrentX(), CustomListener.getStartX());
        arr[1] = Math.min(CustomListener.getCurrentY(), CustomListener.getStartY());
        arr[2] = Math.max(CustomListener.getCurrentX(), CustomListener.getStartX());
        arr[3] = Math.max(CustomListener.getCurrentY(), CustomListener.getStartY());
        return arr;
    }
    @Override
    public BufferedImage apply(BufferedImage input) {
        
        return null;
    }

    public static int getCurrent() {
        return current;
    }

    public static void setCurrent(int current) {
        DrawPanel.current = current;
    }
}
