package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;


public class ImageTransformations { 
    
        /** A list of actions for the Filter menu. */
        protected ArrayList<Action> actions;
    
        /**
         * <p>
         * Create a set of Filter menu actions.
         * </p>
         */
        public ImageTransformations() {
            actions = new ArrayList<Action>();
            actions.add(new RotateImageClockwiseAction("Rotate Clockwise", null, "Rotate image clockwise", Integer.valueOf(KeyEvent.VK_CLOSE_BRACKET)));
            actions.add(new RotateImageAntiClockwiseAction("Rotate Anti-Clockwise", null, "Rotate image anti-clockwise", Integer.valueOf(KeyEvent.VK_OPEN_BRACKET)));
            actions.add(new ResizeAction("Resize the image", null, "Make the image larger or smaller.", Integer.valueOf(KeyEvent.VK_R)));
            actions.add(new FlipImageHorizontalAction("Flip images horizontal", null, "Flip the image horizontally. ", Integer.valueOf(KeyEvent.VK_CLOSE_BRACKET)));
            actions.add(new FlipImageVerticalAction("Flip images vertically", null, "Flip the image vertically. ", Integer.valueOf(KeyEvent.VK_OPEN_BRACKET)));
            actions.add(new CropSelectionAction("Crop Selection", null, "Crop image to selected size. ", Integer.valueOf(KeyEvent.VK_OPEN_BRACKET)));
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

        public class CropSelectionAction extends ImageAction {
            public CropSelectionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
            }

            public void actionPerformed(ActionEvent e) {
                CustomListener.setRunning(true);
                MouseActions.current = MouseActions.CROP;
                Andie.imagePanel.repaint();
                Andie.frame.pack();

            }
        }


        public class RotateImageClockwiseAction extends ImageAction {
            public RotateImageClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
            }
    
            public void actionPerformed(ActionEvent e) {
                target.getImage().apply(new RotateImageClockwise());
                target.repaint();
                target.getParent().revalidate();
                Andie.frame.pack();
            }
        }


        public class RotateImageAntiClockwiseAction extends ImageAction {
            public RotateImageAntiClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
            }
    
            public void actionPerformed(ActionEvent e) {
                target.getImage().apply(new RotateImageAntiClockwise());
                target.repaint();
                target.getParent().revalidate();
                Andie.frame.pack();
            }
        }

        public class FlipImageHorizontalAction extends ImageAction {
            public FlipImageHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
            }
    
            public void actionPerformed(ActionEvent e) {
                target.getImage().apply(new FlipImageHorizontal());
                target.repaint();
                target.getParent().revalidate();
            }
        }

        public class FlipImageVerticalAction extends ImageAction {
            public FlipImageVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
            }
    
            public void actionPerformed(ActionEvent e) {
                target.getImage().apply(new FlipImageVertical());
                target.repaint();
                target.getParent().revalidate();
            }
        }

        public class ResizeAction extends ImageAction {
            public ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
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
                            target.getImage().apply(new ResizeOperation(newW, newH));
                            target.repaint();
                            target.getParent().revalidate();
                            Andie.resizeFrame();
                        }
                    }
                }
            }
        }
}
