package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighborhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class EmbossActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Emboss menu actions.
     * </p>
     */
    public EmbossActions() {
        actions = new ArrayList<Action>();
        actions.add(new EmbossFilter1Action("Top left emboss", null, "Apply a top left emboss filter", Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new EmbossFilter2Action("Mid left emboss", null, "Apply a middle left emboss filter", Integer.valueOf(KeyEvent.VK_2)));
        actions.add(new EmbossFilter3Action("Top mid emboss", null, "Apply a top mid emboss filter", Integer.valueOf(KeyEvent.VK_3)));
        actions.add(new EmbossFilter4Action("Top right emboss", null, "Apply a top right emboss filter", Integer.valueOf(KeyEvent.VK_4)));
        actions.add(new EmbossFilter5Action("Mid right emboss", null, "Apply a middle right emboss filter", Integer.valueOf(KeyEvent.VK_5)));
        actions.add(new EmbossFilter6Action("Bottom right emboss", null, "Apply a bottom right emboss filter", Integer.valueOf(KeyEvent.VK_6)));
        actions.add(new EmbossFilter7Action("Bottom middle emboss", null, "Apply a bottom middle emboss filter", Integer.valueOf(KeyEvent.VK_7)));
        actions.add(new EmbossFilter8Action("Bottom left emboss", null, "Apply a bottom left emboss filter", Integer.valueOf(KeyEvent.VK_8)));
        actions.add(new SoberHorizontalAction("Horizontal Sober", null, "Apply a horizontal sober filter", Integer.valueOf(KeyEvent.VK_9)));
        actions.add(new SoberVerticalAction("Vertical Sober", null, "Apply a vertical sober filter", Integer.valueOf(KeyEvent.VK_0)));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createEmbossMenu() {
        JMenu fileMenu = new JMenu("Emboss");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to apply emboss from the direction of top left to bottom right.
     * </p>
     * 
     * @see EmbossFilter1
     */
    public class EmbossFilter1Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        EmbossFilter1Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter1());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class EmbossFilter2Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter2Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter2());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class EmbossFilter3Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter3Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter3());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class EmbossFilter4Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter4Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter4());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class EmbossFilter5Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter5Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter5());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class EmbossFilter6Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter6Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter6());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class EmbossFilter7Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter7Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter7());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class EmbossFilter8Action extends ImageAction {
        /**
        * <p>
        * Create a new emboss filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        EmbossFilter8Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter8());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class SoberHorizontalAction extends ImageAction {
        /**
        * <p>
        * Create a new horizontal sober action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        SoberHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new SoberHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }   
    }

    public class SoberVerticalAction extends ImageAction {
        /**
        * <p>
        * Create a new horizontal sober action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        SoberVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when emboss the image from top left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilter1 is triggered.
         * It applys the emboss simulation starting from top left of the image. {@link EmbossFilter1}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new SoberVertical());
            target.repaint();
            target.getParent().revalidate();
        }   
    }
    
}