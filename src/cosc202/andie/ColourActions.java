package cosc202.andie;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {
    
    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new BrightnessAndContrastAction("Contrast and Brightness", null, "Changes contrast and brightness", Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new PosterizeAction("Posterize", null, "Reduce the images color palette", null));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Colour");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }
    public class BrightnessAndContrastAction extends ImageAction {
        BrightnessAndContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        public void actionPerformed(ActionEvent e){
            int contrast;
            int brightness;
            JPanel panel = new JPanel();
            JSlider contrastSlider = new JSlider(-100, 100);
            JLabel contrastNumber = new JLabel("Change contrast by: 0%");
            JLabel brightnessNumber = new JLabel("Change brightness by: 0%");
            contrastSlider.setMajorTickSpacing(25);
            contrastSlider.setPaintTicks(true);
            contrastSlider.setPaintLabels(true);
            contrastSlider.addChangeListener(ce -> {
                contrastNumber.setText("Change contrast by: " + String.valueOf(contrastSlider.getValue()) + "%");
            });
            JLabel contrastLabel = new JLabel("Contrast");
            JSlider brightnessSlider = new JSlider(-100, 100);
            brightnessSlider.setMajorTickSpacing(25);
            brightnessSlider.setPaintTicks(true);
            brightnessSlider.setPaintLabels(true);
            brightnessSlider.addChangeListener(ce -> {
                brightnessNumber.setText("Change brightness by: " + String.valueOf(brightnessSlider.getValue()) + "%");
            });
            JLabel brightnessLabel = new JLabel("Brightness");
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(contrastLabel);
            panel.add(contrastSlider);
            panel.add(contrastNumber);
            panel.add(brightnessLabel);
            panel.add(brightnessSlider);
            panel.add(brightnessNumber);
            JOptionPane.showOptionDialog(null, panel, "Brightness and contrast control panel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            contrast = contrastSlider.getValue();
            brightness = brightnessSlider.getValue();
            target.getImage().apply(new BrightnessAndContrast(contrast, brightness));
            target.repaint();
            target.getParent().revalidate();
        }
        public void changeListener(ActionEvent listener){

        }
    }

    public class PosterizeAction extends ImageAction {

        PosterizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            //TODO Auto-generated constructor stub
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            class CustomCellRenderer extends JLabel implements ListCellRenderer<Color> {

                @Override
                public Component getListCellRendererComponent(JList<? extends Color> list, Color color, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    String s = "A:"+color.getAlpha()+" R:"+color.getRed()+" G:"+color.getGreen()+" B:"+color.getBlue();
                    setText(s);
                    setEnabled(true);
                    setFont(list.getFont());
                    setOpaque(true);
                    return this;
                }
            }

            Vector<Color> colours = new Vector<>();
            JList<Color> list = new JList<Color>(colours);

            class MouseListener extends MouseAdapter {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selectedIndex = list.locationToIndex(e.getPoint());
                    //System.out.println("Clicked " + selectedIndex);
                    if(selectedIndex == list.getSelectedIndices()[0]) {
                        list.clearSelection();
                    }
                }
            }

            list.setCellRenderer(new CustomCellRenderer());
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.addMouseListener(new MouseListener());

            JScrollPane scrollPane = new JScrollPane(list);
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(scrollPane);

            JDialog frame = new JDialog(Andie.frame, "Color bands", true);
            JPanel buttonPanel = new JPanel();
            JButton posterizeButton = new JButton("Posterize");
            JButton addButton = new JButton("Add color band");

            class ButtonListener implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == addButton) {
                        CustomColourPanel colourChooser = new CustomColourPanel("RGB", false, true);
                        colours.add(colourChooser.colourArray.get(0));
                        list.setListData(colours);
                    }
                    if(e.getSource() == posterizeButton) {
                        frame.dispose();
                        Color coloursArr[] = new Color[colours.size()];
                        coloursArr = colours.toArray(coloursArr);
                        int coloursIntArr[] = new int[coloursArr.length];
                        for(int i = 0; i < coloursIntArr.length; i++) {
                            coloursIntArr[i] = (coloursArr[i].getAlpha() << 24) | (coloursArr[i].getRed() << 16)
                             | (coloursArr[i].getGreen() << 8) | (coloursArr[i].getBlue());
                        }
                        target.getImage().apply(new Posterize(coloursIntArr));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                    
                }

            }

            ButtonListener bListener = new ButtonListener();

            addButton.addActionListener(bListener);
            posterizeButton.addActionListener(bListener);
            
            buttonPanel.add(posterizeButton);
            buttonPanel.add(addButton);
            mainPanel.add(buttonPanel);

            frame.setSize(new Dimension(350, 200));
            frame.setResizable(false);
            frame.add(mainPanel);
            frame.setVisible(true);
            frame.pack();
        }

    }
}
