package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    private int current = 0;
    private Color colour;
    private int[] coordArr = new int[4];
    private int[] lineArr = new int[4];
    protected int[] xArr, yArr;

    public DrawPanel(ImagePanel panel, int current, Color colour) {
        this.coordArr = calcCoordinates();
        this.lineArr = lineCoordinates();
        this.current = current;
        this.colour = colour;

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g.setColor(colour);
        if (CustomListener.isRunning()) {
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
            } else if (current == MouseActions.CROP) {
                g.drawRect(coordArr[0], coordArr[1],
                        coordArr[2] - coordArr[0],
                        coordArr[3] - coordArr[1]);
            } else if (current == MouseActions.DRAWLINE) {
                g.drawLine(lineArr[0], lineArr[1],
                        lineArr[2], lineArr[3]);
            }  else if (current == MouseActions.FREEDRAW) {
                this.xArr = new int [CustomListener.getxList().size()];
                this.yArr = new int [CustomListener.getyList().size()];
                for(int i = 0; i < CustomListener.getxList().size(); i++){
                    xArr[i] = CustomListener.getxList().get(i);
                    yArr[i] = CustomListener.getyList().get(i);
                }
                g2.setStroke(new BasicStroke(6));
                g2.drawPolyline( xArr, yArr, xArr.length); 
            }
        }
        Andie.imagePanel.repaint();
        Andie.imagePanel.revalidate();
    }


    public static int[] calcCoordinates() {
        int coordArr[] = new int[4];
        coordArr[0] = Math.min(CustomListener.getCurrentX(), CustomListener.getStartX());
        coordArr[1] = Math.min(CustomListener.getCurrentY(), CustomListener.getStartY());
        coordArr[2] = Math.max(CustomListener.getCurrentX(), CustomListener.getStartX());
        coordArr[3] = Math.max(CustomListener.getCurrentY(), CustomListener.getStartY());
        return coordArr;
    }

    public static int[] lineCoordinates() {
        int lineArr[] = new int[4];
        lineArr[0] = CustomListener.getStartX();
        lineArr[1] = CustomListener.getStartY();
        lineArr[2] = CustomListener.getCurrentX();
        lineArr[3] = CustomListener.getCurrentY();
        return lineArr;
    }
}
