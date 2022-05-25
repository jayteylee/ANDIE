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
     * This class creates a custom colour panel
     * @author Jake Norton, Jack Bredenbeck
     */
public class CustomColourPanel implements ChangeListener {
    protected JDialog frame;
    protected JColorChooser chooser;
    protected JPanel previewP;
    protected JButton ok;
    protected JButton cancel;
    protected JButton add;
    protected JLabel previewText;
    protected JPanel p;
    protected JPanel buttonP;
    protected JPanel textPanel;
    protected JPanel dummyPanel2;
    protected JPanel previewPanels;
    protected ArrayList<Color> colourArray;

    /**
     * Constructor for CustomColourPanel that also instantiates a colour chooser panel, 
     * depending on colorType and whether it creates an add button or not.
     */
    public CustomColourPanel(String colorType, boolean multiple, boolean modal) {
        frame = new JDialog(Andie.frame, "Choose a colour", modal);

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        colourArray = new ArrayList<Color>();
        chooser = new JColorChooser();
        Listener l = new Listener(buttons, chooser);
        //Set dimensions and layout of the frame
        frame.setSize(new Dimension(650, 400));
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        //Removes all chooser panels apart from HSV
        AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (!accp.getDisplayName().equals(colorType)) {
                chooser.removeChooserPanel(accp);
            }
        }
        chooser.getSelectionModel().addChangeListener(this);
        previewP = new JPanel();
        previewP.setBackground(MouseActions.colour);

        //Set panel and button sizes
        previewP.setPreferredSize(new Dimension(100, 100));
        ok = new JButton("Ok");
        ok.setPreferredSize(new Dimension(80, 20));
        cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(80, 20));
        add = new JButton("add");
        add.setPreferredSize(new Dimension(80, 20));
        textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(100, 20));
        dummyPanel2 = new JPanel();
        dummyPanel2.setPreferredSize(new Dimension(100, 20));

        //
        previewPanels = new JPanel();
        previewPanels.add(previewP);
        previewPanels.add(dummyPanel2);
        previewText = new JLabel("Colour preview");
        textPanel.add(previewText);
        textPanel.add(dummyPanel2);
        // add buttons to the buttons arraylist
        buttons.add(ok);
        buttons.add(cancel);
        buttons.add(add);
        

        //add buttons to the button panel
        buttonP = new JPanel();
        buttonP.setPreferredSize(new Dimension(500,40));
        buttonP.add(ok);
        buttonP.add(cancel);
        if(multiple) {
            buttonP.add(add);
        }

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
        frame.add(p);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * This method changes the background of the preview panel depending on the colour chosen
     * or the colour chooser component
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        previewP.setBackground(chooser.getColor());
    }
    /**
     * Action listener class to handle actions on the buttons on the custom colour chooser
     */
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
                colourArray.add(chooser.getColor());
                frame.dispose();
            }
            if (e.getSource() == buttons.get(1)) {
                frame.dispose();
            }
            if (e.getSource() == buttons.get(2)) {
                colourArray.add(chooser.getColor());
            }
        }

    }
}
