package cosc202.andie;

import java.awt.Graphics;

public class DrawPanel extends ImagePanel {
    private static int current = 0;
    public DrawPanel(ImagePanel p) {
    }
    public static int getCurrent() {
        return current;
    }
    public static void setCurrent(int current) {
        DrawPanel.current = current;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(CustomListener.getStartX(), CustomListener.getStartY(), 
            CustomListener.getCurrentX() - CustomListener.getStartX(), CustomListener.getCurrentY() - CustomListener.getStartY());
    }
   
}
