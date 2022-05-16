package cosc202.andie;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class CustomListener implements MouseInputListener{
    private static boolean entered = false;
    private static boolean pressed = false;
    private static boolean running = false;
    // public static final int ENTERED = 0;
    // public static final int EXITED = 1;
    // public static final int CLICKED = 2;
    // public static final int PRESSED = 3;
    // public static final int RELEASED = 4;
    // public static final int DRAGGED = 5;
    // public static final int MOVED = 6;
    private static int startX = 0;
    private static int startY = 0;
    private static int endX = 0;
    private static int endY = 0;
    private static int currentX = 0;
    private static int currentY = 0;

public CustomListener(){};
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        running = true;
        if(MouseActions.running && entered){
        setStartX(e.getX());
        setStartY(e.getY());
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        running = false;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
        entered = true;
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        entered = false;  
        running = false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(MouseActions.running && entered && pressed){
        setCurrentX(e.getX());
        setCurrentY(e.getY());
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    public static boolean isEntered() {
        return entered;
    }
    public static void setEntered(boolean entered) {
        CustomListener.entered = entered;
    }
    public static boolean isPressed() {
        return pressed;
    }
    public static boolean isRunning() {
        return running;
    }
    public static void setPressed(boolean pressed) {
        CustomListener.pressed = pressed;
    }
    public static int getStartX() {
        return startX;
    }
    public static void setStartX(int startX) {
        CustomListener.startX = startX;
    }
    public static int getStartY() {
        return startY;
    }
    public static void setStartY(int startY) {
        CustomListener.startY = startY;
    }
    public static int getEndX() {
        return endX;
    }
    public static void setEndX(int endX) {
        CustomListener.endX = endX;
    }
    public static int getEndY() {
        return endY;
    }
    public static void setEndY(int endY) {
        CustomListener.endY = endY;
    }
    public static int getCurrentX() {
        return currentX;
    }
    public static void setCurrentX(int currentX) {
        CustomListener.currentX = currentX;
    }
    public static int getCurrentY() {
        return currentY;
    }
    public static void setCurrentY(int currentY) {
        CustomListener.currentY = currentY;
    }
    
 }