package cosc202.andie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
    /** 
     * Constructor for CustomColourPanel that also instantiates a colour chooser panel
     */
public class CustomColourPanel implements ChangeListener {
    protected static JFrame f = new JFrame("Choose new colour");
    protected static JColorChooser chooser = new JColorChooser();
    protected static JPanel previewP = new JPanel();
    protected static JButton ok = new JButton("Ok");
    protected static JButton cancel = new JButton("cancel");
    protected static JButton add = new JButton("add");
    protected static JLabel previewText = new JLabel("Colour preview");
    protected static JPanel p = new JPanel();
    protected static JPanel buttonP = new JPanel();
    protected static JPanel textPanel = new JPanel();
    protected static JPanel dummyPanel2 = new JPanel();
    protected static JPanel previewPanels = new JPanel();
    protected static ArrayList<Color> colourArray = new ArrayList<Color>();

    /**
     * Constructor for CustomColourPanel that also instantiates a colour chooser panel
     */
    public CustomColourPanel() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Listener l = new Listener(buttons, chooser);
        //Set dimensions and layout of the frame
        f.setSize(new Dimension(650, 400));
        f.setLayout(new FlowLayout());
        f.setResizable(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        //Removes all chooser panels apart from HSV
        AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            System.out.println(accp.getDisplayName());
            if (!accp.getDisplayName().equals("HSV")) {
                chooser.removeChooserPanel(accp);
            }
        }
        chooser.getSelectionModel().addChangeListener(this);
        previewP.setBackground(MouseActions.colour);

        //Set panel and button sizes
        previewP.setPreferredSize(new Dimension(100, 100));
        ok.setPreferredSize(new Dimension(60, 20));
        cancel.setPreferredSize(new Dimension(60, 20));
        add.setPreferredSize(new Dimension(60, 20));
        textPanel.setPreferredSize(new Dimension(60, 20));
        dummyPanel2.setPreferredSize(new Dimension(60, 20));

        //
        previewPanels.add(previewP);
        previewPanels.add(dummyPanel2);
        textPanel.add(previewText);
        textPanel.add(dummyPanel2);
        // add buttons to the buttons arraylist
        buttons.add(ok);
        buttons.add(cancel);
        buttons.add(add);

        //add buttons to the button panel
        buttonP.add(ok);
        buttonP.add(cancel);
        buttonP.add(add);

        //Add button listeners
        ok.addActionListener(l);
        cancel.addActionListener(l);
        add.addActionListener(l);

        //overrites the default chooser panel
        chooser.setPreviewPanel(previewP);
        //Add all the panels to the main panel
        p.add(chooser);
        p.add(textPanel);
        p.add(previewPanels);
        p.add(dummyPanel2);
        p.add(buttonP);

        //Add main panel to the frame and set the open location to the centre of the screen
        f.add(p);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.pack();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        previewP.setBackground(chooser.getColor());
    }

    public class Listener implements ActionListener {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JColorChooser chooser;

        public Listener(ArrayList<JButton> buttons, JColorChooser chooser) {
            this.buttons = buttons;
            this.chooser = chooser;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttons.get(0)) {
                MouseActions.colour = chooser.getColor();
                f.dispose();
            }
            if (e.getSource() == buttons.get(1)) {
                f.dispose();
            }
            if (e.getSource() == buttons.get(2)) {
                colourArray.add(chooser.getColor());
            }
        }

    }
}
