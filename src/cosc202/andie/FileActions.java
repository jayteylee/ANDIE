package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cosc202.andie.CustomException.CustomCode;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications, 
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FileActions {
    
    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;


    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction("Open", null, "Open a file", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction("Save", null, "Save the file", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction("Save As", null, "Save a copy", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileExportAction("Export", null, "Export the file", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileExitAction("Exit", null, "Exit the program", Integer.valueOf(0)));

    }

    /**
     * <p>
     * Create a menu contianing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("File");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */

    public class FileOpenAction extends ImageAction {

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
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
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
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Input files", "jpg", "jpeg", "png","JFIF");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(target);
            target.setSize(target.getPreferredSize());
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
            
            Andie.resizeFrame();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                try {
                    if(target.getImage().getCurrentImage() == null) {
                        throw new CustomException(CustomException.CustomCode.FILE_SAVE_NULL_EXCEPTION, 
                        "Please open a file before attempting to save.");
                    }
                    target.getImage().save();           
                } catch (CustomException cE) {
                    if(cE.code == CustomException.CustomCode.FILE_SAVE_NULL_EXCEPTION) {
                        System.out.println(cE.getMessage());
                        JOptionPane.showMessageDialog(target, cE.getMessage(), "Invalid Operation", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        throw cE;
                    }
                }
            }catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "An error occurred while trying to save the image.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.VK_C));
        }

         /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                try {

                    if(target.getImage().getCurrentImage() == null) {
                        throw new CustomException(CustomException.CustomCode.FILE_SAVE_NULL_EXCEPTION, "Please open an image before attempting to save.");
                    }
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(target);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        target.getImage().saveAs(imageFilepath);
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

    }
    public class FileExportAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

         /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                try {
                    if(target.getImage().getCurrentImage() == null) {
                        throw new CustomException(CustomException.CustomCode.FILE_SAVE_NULL_EXCEPTION, "Please open a file before trying to export.");
                    }
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(target);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        target.getImage().export(imageFilepath);
                    }
                } catch(CustomException cE) {
                    if(cE.code == CustomCode.FILE_SAVE_NULL_EXCEPTION) {
                        JOptionPane.showMessageDialog(target, cE.getMessage(), "Invalid Operation", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        throw cE;
                    }
                }
            } catch(Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "An error occured while trying to export the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

         /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

}
