package cosc202.andie;

import java.awt.Graphics;

public class DrawPanel extends ImagePanel {

    public DrawPanel() {
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(100, 100, 100, 100);
    }
}
