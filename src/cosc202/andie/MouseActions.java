package cosc202.andie;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.DimensionUIResource;

public class MouseActions {
    protected ArrayList<Action> actions;
    protected static boolean running;
    protected static boolean drawing;
    protected static boolean released = false;
    protected static Color colour;
    protected static final int crop = 0;
    protected static final int DRAWRECT = 1;
    protected static final int DRAWFILLRECT = 2;
    protected static final int DRAWOVAL = 3;
    protected static final int DRAWFILLOVAL = 4;
    protected static int current;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public MouseActions() {
        actions = new ArrayList<Action>();
        actions.add(new MouseSelectAction("Mouse selection", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawRectAction("Draw Rectangle", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawFillRectAction("Draw filled rectangle", null, "Start recording",Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawOvalAction("Draw oval", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawFillOvalAction("Draw filled oval", null, "Start recording",Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseColourAction("Choose colour", null, "Start recording",Integer.valueOf(KeyEvent.VK_O)));

    }

    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Mouse");

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    public class MouseSelectAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MouseSelectAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            running = true;
            drawing = true;
            Andie.imagePanel.repaint();
        }
    }

    public class MouseDrawRectAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MouseDrawRectAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            CustomListener.setRunning(true);
            current = DRAWRECT;
            Andie.imagePanel.repaint();
        }
    }

    public class MouseDrawFillRectAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MouseDrawFillRectAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            CustomListener.setRunning(true);
            current = DRAWFILLRECT;
            Andie.imagePanel.repaint();
        }
    }

    public class MouseDrawOvalAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MouseDrawOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            CustomListener.setRunning(true);
            current = DRAWOVAL;
            Andie.imagePanel.repaint();
        }
    }

    public class MouseDrawFillOvalAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MouseDrawFillOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            CustomListener.setRunning(true);
            current = DRAWFILLOVAL;
            Andie.imagePanel.repaint();
        }
    }
    public class MouseColourAction extends ImageAction implements ChangeListener{
        protected static JFrame f = new JFrame("Choose new colour");
        protected static JColorChooser chooser = new JColorChooser();
        protected static JPanel previewP = new JPanel();
        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MouseColourAction(String name, ImageIcon icon, String desc, Integer mnemonic){
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
            f.setSize(new Dimension(550, 500));
            f.setLayout(new FlowLayout(FlowLayout.LEFT));
            AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
            for (AbstractColorChooserPanel accp : panels) {
                System.out.println(accp.getDisplayName());
                if(!accp.getDisplayName().equals("HSV")) {
                    chooser.removeChooserPanel(accp);
                } 
            }
            chooser.getSelectionModel().addChangeListener(this);
            ArrayList<JButton> buttons = new ArrayList<JButton>();
            JPanel p = new JPanel();
            previewP.setBackground(Color.GREEN);
            //previewP.setSize(1000, 1000);
            previewP.setPreferredSize(new Dimension(200, 200));
            p.setLayout(new FlowLayout(FlowLayout.LEFT));
            JButton ok = new JButton("Ok");
            JButton cancel = new JButton("cancel");
            buttons.add(ok);
            buttons.add(cancel);
            Listener l = new Listener(buttons, chooser);
            ok.addActionListener(l);
            cancel.addActionListener(l);
            chooser.setPreviewPanel(previewP);
            p.add(chooser);
            p.add(ok);
            p.add(cancel);
            f.add(p);
            f.add(previewP);
            f.setVisible(true);
            f.pack();
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            previewP.setBackground(chooser.getColor());
        }
    }
    public class Listener implements ActionListener{
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JColorChooser chooser;
        public Listener(ArrayList<JButton> buttons, JColorChooser chooser){
            // for(JButton button: buttons){
            //     buttons.add(button);
            // }
            this.buttons = buttons;
            this.chooser = chooser;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttons.get(0)){
                MouseActions.colour = chooser.getColor();
                MouseColourAction.f.dispose();
            }
            if(e.getSource() == buttons.get(1)){
                MouseColourAction.f.dispose();
            }
        }

    }
}
