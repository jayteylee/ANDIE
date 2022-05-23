package cosc202.andie;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MouseActions {
    protected ArrayList<Action> actions;
    protected static boolean running;
    protected static boolean drawing;
    protected static boolean released = false;
    protected static Color colour = Color.BLACK;
    protected static final int CROP = 0;
    protected static final int DRAWRECT = 1;
    protected static final int DRAWFILLRECT = 2;
    protected static final int DRAWOVAL = 3;
    protected static final int DRAWFILLOVAL = 4;
    protected static final int DRAWLINE = 5;
    protected static int current;
    protected static JFrame f = new JFrame("Choose new colour");
    protected static JColorChooser chooser = new JColorChooser();
    protected static JPanel previewP = new JPanel();
    protected static JButton ok = new JButton("Ok");
    protected static JButton cancel = new JButton("cancel");
    protected static JLabel previewText = new JLabel("Colour preview");
    protected static JPanel p = new JPanel();
    protected static JPanel buttonP = new JPanel();
    protected static JPanel dummyPanel1 = new JPanel();
    protected static JPanel dummyPanel2 = new JPanel();
    protected static JPanel previewPanels = new JPanel();

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public MouseActions() {
        actions = new ArrayList<Action>();
        actions.add(new MouseDrawLineAction("Draw Line", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawRectAction("Draw Rectangle", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawFillRectAction("Draw filled rectangle", null, "Start recording",
                Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawOvalAction("Draw oval", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseDrawFillOvalAction("Draw filled oval", null, "Start recording",
                Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MouseColourAction("Choose colour", null, "Start recording", Integer.valueOf(KeyEvent.VK_O)));

    }

    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Mouse");

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    public class MouseDrawLineAction extends ImageAction {

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
        MouseDrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            current = DRAWLINE;
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

    public class MouseColourAction extends ImageAction implements ChangeListener {

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
        MouseColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            // f.setSize(new Dimension(650, 400));
            // f.setLayout(new FlowLayout());
            // f.setResizable(false);

            // AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
            // for (AbstractColorChooserPanel accp : panels) {
            //     System.out.println(accp.getDisplayName());
            //     if (!accp.getDisplayName().equals("HSV")) {
            //         chooser.removeChooserPanel(accp);
            //     }
            // }
            // chooser.getSelectionModel().addChangeListener(this);
            // ArrayList<JButton> buttons = new ArrayList<JButton>();

            // p.setBackground(Color.black);
            // chooser.setBackground(Color.BLACK);
            // previewP.setBackground(MouseActions.colour);
            // // previewP.setSize(1000, 1000);
            // previewP.setPreferredSize(new Dimension(100, 100));
            // p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            // ok.setPreferredSize(new Dimension(60, 20));
            // cancel.setPreferredSize(new Dimension(60, 20));
            // dummyPanel1.setPreferredSize(new Dimension(60, 20));
            // dummyPanel2.setPreferredSize(new Dimension(60, 20));
            // previewPanels.add(previewP);
            // previewPanels.add(dummyPanel2);
            // dummyPanel1.add(previewText);
            // dummyPanel1.add(dummyPanel2);
            // buttons.add(ok);
            // buttons.add(cancel);
            // buttonP.add(ok);
            // buttonP.add(cancel);
            // Listener l = new Listener(buttons, chooser);
            // ok.addActionListener(l);
            // cancel.addActionListener(l);
            // chooser.setPreviewPanel(previewP);
            // p.add(chooser);
            // p.add(dummyPanel1);
            // p.add(previewPanels);
            // p.add(dummyPanel2);
            // p.add(buttonP);
            // //p.add(new DrawPanel(Andie.imagePanel, current, MouseActions.colour));
            // f.add(p);
            //f.setVisible(true);
            //f.setLocationRelativeTo(null);
            //f.pack();
            CustomColourPanel frame = new CustomColourPanel();
        }

    //     @Override
    //     public void stateChanged(ChangeEvent e) {
    //         previewP.setBackground(chooser.getColor());
    //     }
    // }

    // public class Listener implements ActionListener {
    //     ArrayList<JButton> buttons = new ArrayList<JButton>();
    //     JColorChooser chooser;

    //     public Listener(ArrayList<JButton> buttons, JColorChooser chooser) {
    //         // for(JButton button: buttons){
    //         // buttons.add(button);
    //         // }
    //         this.buttons = buttons;
    //         this.chooser = chooser;
    //     }

    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         if (e.getSource() == buttons.get(0)) {
    //             MouseActions.colour = chooser.getColor();
    //             MouseActions.f.dispose();
    //         }
    //         if (e.getSource() == buttons.get(1)) {
    //             MouseActions.f.dispose();
    //         }
    //     }

     }
}
