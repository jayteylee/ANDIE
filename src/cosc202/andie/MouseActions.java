package cosc202.andie;

import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cosc202.andie.CustomException.CustomCode;
import javax.swing.Action;

public class MouseActions {
    protected ArrayList<Action> actions;
    protected boolean running;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public MouseActions() {
        actions = new ArrayList<Action>();
        actions.add(new MouseStartAction("Start recording", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));

    }
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Mouse");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }
    public class MouseStartAction extends ImageAction {

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
        MouseStartAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
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
        }

    }
}
