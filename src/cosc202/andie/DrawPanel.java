package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DrawPanel extends ImagePanel implements ImageOperation {
    private int current = 0;
    private Color colour;
    private int[] coordArr = new int[4];

    public DrawPanel(ImagePanel panel, int current, Color colour ) {
        this.coordArr = calcCoordinates();
        this.current = current;
        this.colour = colour;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.colour);
        if(CustomListener.isRunning()){
            if (current == MouseActions.DRAWRECT) {
                g.drawRect(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWFILLRECT) {
                g.fillRect(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWOVAL) {
                g.drawOval(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWFILLOVAL) {
                g.fillOval(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            }
        }
        repaint();
        revalidate();
    }

    public static int[] calcCoordinates() {
        int coordArr[] = new int[4];
        coordArr[0] = Math.min(CustomListener.getCurrentX(), CustomListener.getStartX());
        coordArr[1] = Math.min(CustomListener.getCurrentY(), CustomListener.getStartY());
        coordArr[2] = Math.max(CustomListener.getCurrentX(), CustomListener.getStartX());
        coordArr[3] = Math.max(CustomListener.getCurrentY(), CustomListener.getStartY());
        return coordArr;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),input.isAlphaPremultiplied(), null);
        Graphics outputGraphics = output.getGraphics();
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
        }
        repaint();
        revalidate();
        

        return output;
    }
}
