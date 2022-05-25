package cosc202.andie;

import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cosc202.andie.CustomException.CustomCode;
import javax.swing.Action;

public class MacroActions {
    protected ArrayList<Action> actions;
    protected boolean running;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public MacroActions() {
        actions = new ArrayList<Action>();
        actions.add(new MacroStartAction("Start recording", null, "Start recording", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new MacroStopAction("Stop recording", null, "Save the file", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new MacroOpenAction("Load", null, "Add macro to file", Integer.valueOf(KeyEvent.VK_L)));

    }
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Macro");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }
    public class MacroStartAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MacroStartAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the macro start action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MacroStartAction is triggered.
         * Starts recording operations by setting the running boolean to true
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
          EditableImage.macroRunning = true;
          try {
            Andie.resetToolbar();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        }

    }
    public class MacroStopAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MacroStopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the macro stop action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MacroStopAction is triggered.
         * This stops recording operations by setting the running boolean to false
         * Pops out an option dialog box, if the user answers yes, saves the macro file
         * by default to the macros folder in the andie/bin
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            EditableImage.macroRunning = false; 
            int option = JOptionPane.showOptionDialog(null, "Would you like to save this macro?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(option == JOptionPane.YES_OPTION){
                try {
                    try {
                        JFileChooser fileChooser = new JFileChooser("./macros");
                        int result = fileChooser.showSaveDialog(target);
    
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                            target.getImage().saveAsMacro(imageFilepath);
                        }
    
                    } catch(CustomException cE) {
                        if(cE.code == CustomCode.FILE_SAVE_NULL_EXCEPTION) {
                            JOptionPane.showMessageDialog(target, cE.getMessage(), "Invalid operation.", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            throw cE;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "An error occured while saving the image.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            try {
                Andie.resetToolbar();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

    }
    public class MacroOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MacroOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
        }

         /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser("./macros");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Input files", "macro", "ops");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(target);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().macroOpen(filePath);
                    target.repaint();
                    target.getParent().revalidate();
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }
    }
}
