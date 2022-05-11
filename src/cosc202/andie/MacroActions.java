package cosc202.andie;

import java.util.ArrayList;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cosc202.andie.CustomException.CustomCode;
import javax.swing.Action;

public class MacroActions {
    protected ArrayList<Action> actions;


    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public MacroActions() {
        actions = new ArrayList<Action>();
        actions.add(new MacroStartAction("Start", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction("Save", null, "Save the file", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction("Save As", null, "Save a copy", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileExportAction("Export", null, "Export the file", Integer.valueOf(KeyEvent.VK_E)));

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
            
        }

    }
}
