package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.ResizeFilter;

public class ImageImplementations { //Change to imageTransformations
    
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
            actions.add(new ResizeFilterAction("Resize the image", null, "Make the image larger or smaller.", Integer.valueOf(KeyEvent.VK_R)));
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

        public class ResizeFilterAction extends ImageAction {
            public ResizeFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
            }
    
            public void actionPerformed(ActionEvent e) {
                if(target.getImage().getCurrentImage() != null) {
                    int currW = target.getImage().getCurrentImage().getWidth();
                    int currH = target.getImage().getCurrentImage().getHeight();
                    JTextField wTxtField = new JTextField(String.valueOf(currW), 5); //Text field filled with the current image width value.
                    JTextField hTxtField = new JTextField(String.valueOf(currH), 5); //Filled with current height value.
                    Object[] content = {"Width: ", wTxtField, "Height: ", hTxtField};
                    String[] options = {"Resize", "Exit"};
                    int option = JOptionPane.showOptionDialog(null, content, "Resize", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                    if(option == 0) {
                        int newW = Integer.parseInt(wTxtField.getText());
                        int newH = Integer.parseInt(hTxtField.getText());
                        if(newW > 0 && newH > 0) {
                            target.getImage().apply(new ResizeFilter(newW, newH));
                            target.repaint();
                            target.getParent().revalidate();
                        }
                    }
                }
            }
        }
}
