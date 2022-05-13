package cosc202.andie;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel {

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;
    private CustomListener l = new CustomListener();
    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        
        this.addMouseListener(l);
        this.addMouseMotionListener(l);
        image = new EditableImage();
        scale = 1.0;
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            //if(MouseActions.running){
            g2.drawRect(l.getStartX(), l.getStartY(), l.getCurrentX() - l.getStartX(), l.getCurrentY() - l.getStartY());
            repaint();
            //}
            g2.dispose();
        }
    }

public static class CustomListener implements MouseInputListener{
        private boolean entered = false;
        private boolean pressed = false;
        private boolean crop = false;
        private int startX = 0;
        private int startY = 0;
        private int endX = 0;
        private int endY = 0;
        private int currentX = 0;
        private int currentY = 0;

    public CustomListener(){};
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            pressed = true;
            setStartX(e.getX());
            setStartY(e.getY());
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            entered = true;
            
        }
        @Override
        public void mouseExited(MouseEvent e) {
            entered = false;  
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            setCurrentX(e.getX());
            setCurrentY(e.getY());
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            
        }
        public boolean isEntered() {
            return entered;
        }
        public void setEntered(boolean entered) {
            this.entered = entered;
        }
        public boolean isPressed() {
            return pressed;
        }
        public void setPressed(boolean pressed) {
            this.pressed = pressed;
        }
        public boolean isCrop() {
            return crop;
        }
        public void setCrop(boolean crop) {
            this.crop = crop;
        }
        public int getStartX() {
            return startX;
        }
        public void setStartX(int startX) {
            this.startX = startX;
        }
        public int getStartY() {
            return startY;
        }
        public void setStartY(int startY) {
            this.startY = startY;
        }
        public int getEndX() {
            return endX;
        }
        public void setEndX(int endX) {
            this.endX = endX;
        }
        public int getEndY() {
            return endY;
        }
        public void setEndY(int endY) {
            this.endY = endY;
        }
        public int getCurrentX() {
            return currentX;
        }
        public void setCurrentX(int currentX) {
            this.currentX = currentX;
        }
        public int getCurrentY() {
            return currentY;
        }
        public void setCurrentY(int currentY) {
            this.currentY = currentY;
        }
        
    // public static class CustomListener implements MouseInputListener{
    //     private boolean entered = false;
    //     private boolean pressed = false;
    //     private boolean crop = false;
    //     private static int startX = 0;
    //     private static int startY = 0;
    //     private static int endX = 0;
    //     private static int endY = 0;
    //     private static int currentX = 0;
    //     private static int currentY = 0;

    //     @Override
    //     public void mouseClicked(MouseEvent e) {
    //         System.out.println("clicked");
            
    //     }
    //     @Override
    //     public void mousePressed(MouseEvent e) {
    //         pressed = true;
    //         startX = e.getX();
    //         startY = e.getY();
    //     }
    //     @Override
    //     public void mouseReleased(MouseEvent e) {
    //         Andie.imagePanel.repaint();
            
    //     }
    //     @Override
    //     public void mouseEntered(MouseEvent e) {
    //         entered = true;
            
    //     }
    //     @Override
    //     public void mouseExited(MouseEvent e) {
    //         entered = false;  
    //     }
    //     @Override
    //     public void mouseDragged(MouseEvent e) {
    //             currentX = e.getX();
    //             currentY = e.getY();
    //     }
    //     @Override
    //     public void mouseMoved(MouseEvent e) {
    //         //System.out.println("moved");
            
    //     }

     }
}