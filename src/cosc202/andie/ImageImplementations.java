package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageImplementations {
    
        /** A list of actions for the Filter menu. */
        protected ArrayList<Action> actions;
    
        /**
         * <p>
         * Create a set of Filter menu actions.
         * </p>
         */
        public ImageImplementations() {
            actions = new ArrayList<Action>();
            actions.add(new RotateImageClockwise("Rotate Clockwise", null, "Rotate image clockwise", Integer.valueOf(KeyEvent.VK_M)));
        }
    
        /**
         * <p>
         * Create a menu containing the list of Image actions.
         * </p>
         * 
         * @return The filter menu UI element.
         */
        public JMenu createMenu() {
            JMenu fileMenu = new JMenu("Image");
    
            for(Action action: actions) {
                fileMenu.add(new JMenuItem(action));
            }
    
            return fileMenu;
        }




        public class RotateImageClockwise extends ImageAction {
            public RotateImageClockwise(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
            }
    
            public void actionPerformed(ActionEvent e) {
                target.getImage().apply(new SharpenFilter());
                target.repaint();
                target.getParent().revalidate();
            }
        }
}
