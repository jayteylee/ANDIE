package cosc202.andie;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;
/**
 * <p>
 * A Custom listener class to handle mouse drag and click operations.
 * Once running, it tracks starting x,y coordinates and while dragging the current x,y coordinates
 * </p>
 * 
 * @author Jake Norton
 */
public class CustomListener implements MouseInputListener {
    private static boolean entered = false;
    private static boolean pressed = false;
    private static boolean running = false;
    private static ArrayList<Integer> xList = new ArrayList<Integer>();
    private static ArrayList<Integer> yList = new ArrayList<Integer>();
    private static int startX = 0;
    private static int startY = 0;
    private static int endX = 0;
    private static int endY = 0;
    private static int currentX = 0;
    private static int currentY = 0;

    public CustomListener() {
    };

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**Once pressed, set pressed boolean to true.
     * If the listener is running and the mouse is on the listened to panel, set the start x and y coordinates
     * of the mouse.
     * If the current operation is freedraw, create new arraylists for the x and y coordinates
     */
    @Override
    public void mousePressed(MouseEvent e) { 
        pressed = true;
        if (entered && running) {
            setStartX(e.getX());
            setStartY(e.getY());
            if(MouseActions.current == MouseActions.FREEDRAW){
                CustomListener.xList = new ArrayList<Integer>();
                CustomListener.yList = new ArrayList<Integer>();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (CustomListener.running && CustomListener.entered) {
            if(MouseActions.current == MouseActions.CROP){
             Andie.imagePanel.getImage().apply(new CropSelection());
            }else{
            Andie.imagePanel.getImage().apply(new DrawApply(MouseActions.current, MouseActions.colour));
            }
            ImageAction.target.repaint();
            ImageAction.target.revalidate();
            pressed = false;
            running = false;
        }
        clear();
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
        if (entered && pressed && running) {
            setCurrentX(e.getX());
            setCurrentY(e.getY());
            if(MouseActions.current == MouseActions.FREEDRAW){
                xList.add(currentX);
                yList.add(currentY);
               }
        }
    }
    public static ArrayList<Integer> getxList() {
        return xList;
    }

    public static void setxList(ArrayList<Integer> xList) {
        CustomListener.xList = xList;
    }

    public static ArrayList<Integer> getyList() {
        return yList;
    }

    public static void setyList(ArrayList<Integer> yList) {
        CustomListener.yList = yList;
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

    public static void setRunning(boolean running) {
        CustomListener.running = running;
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
    /**
     * A method to clear all the static coordinates between runs
     */
    public static void clear() {
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        currentX = 0;
        currentY = 0;
        CustomListener.xList = new ArrayList<>();
        CustomListener.yList = new ArrayList<>();
    }
}