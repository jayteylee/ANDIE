package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * A class to create and output a toolbar, with functions like open, save,
 * export, undo, redo.
 * The toolbar makes frequent uses methods easier and quicker to access.
 * The icons used come from the links below.
 * <a href="https://www.flaticon.com/free-icons/rec" title="rec icons">Rec icons
 * created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/rec" title="rec icons">Rec icons
 * created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/open-folder" title="open folder
 * icons">Open folder icons created by kumakamu - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/undo" title="undo icons">Undo
 * icons created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/curved-arrow" title="curved
 * arrow icons">Curved arrow icons created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/export" title="export
 * icons">Export icons created by Andrean Prabowo - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/save" title="save icons">Save
 * icons created by srip - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/zoom-in" title="zoom in
 * icons">Zoom in icons created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/zoom-out" title="zoom out
 * icons">Zoom out icons created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/enlarge" title="enlarge
 * icons">Enlarge icons created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/rotate" title="rotate
 * icons">Rotate icons created by Karacis - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/user-interface" title="user
 * interface icons">User interface icons created by Karacis - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/opposite" title="opposite
 * icons">Opposite icons created by Freepik - Flaticon</a>
 * <a href="https://www.flaticon.com/free-icons/opposite" title="opposite
 * icons">Opposite icons created by Freepik - Flaticon</a>
 * 
 * @author Jake Norton
 */
public class Toolbar {
    private JButton open;
    private JButton save;
    private JButton export;
    private JButton undo;
    private JButton redo;
    private JButton zoomIn;
    private JButton zoomOut;
    private JButton resize;
    private JButton rotateClockwise;
    private JButton rotateAntiClockwise;
    private JButton flipHorizontal;
    private JButton flipVertical;
    private JButton macro;
    private MacroActions macroAction = new MacroActions();
    private FileActions fileAction = new FileActions();
    private EditActions editAction = new EditActions();
    private ViewActions viewAction = new ViewActions();
    private ImageTransformations imageAction = new ImageTransformations();

    /**
     * Constructor which calls methods to instantiate the buttons on the toolbar.
     */
    public Toolbar() {
        openButton();
        saveButton();
        exportButton();
        undoButton();
        redoButton();
        zoomInButton();
        zoomOutButton();
        resizeButton();
        rotateClockwiseButton();
        rotateAntiClockwiseButton();
        flipHorizontalButton();
        flipVerticalButton();
        if(EditableImage.macroRunning){
            macroStopButton();
        }else{
            macroStartButton();
        }
        
    }

    /**
     * A method to create and populate a toolbar
     * 
     * @return A toolbar with helpful buttons.
     */
    public JToolBar createToolBar() {
        JToolBar toolbar = new JToolBar(BorderLayout.WEST);
        toolbar.setFloatable(false);
        toolbar.add(open);
        toolbar.add(save);
        toolbar.add(export);
        toolbar.add(undo);
        toolbar.add(redo);
        toolbar.add(zoomIn);
        toolbar.add(zoomOut);
        toolbar.add(resize);
        toolbar.add(rotateClockwise);
        toolbar.add(rotateAntiClockwise);
        toolbar.add(flipHorizontal);
        toolbar.add(flipVertical);
        toolbar.add(macro);
        return toolbar;
    }

    /**
     * Method to create the open button and set its icon and instantiate an action
     * listener.
     */
    public void openButton() {
        try {
            open = new JButton(resizeImage("./src/toolbarIcons/open.png"));
            open.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load open button");
        }
    }

    /**
     * Method to create the save button and set its icon and instantiate an action
     * listener.
     */
    public void saveButton() {
        try {
            save = new JButton(resizeImage("./src/toolbarIcons/save.png"));
            save.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load save button");
        }
    }

    /**
     * Method to create the export button and set its icon and instantiate an action
     * listener.
     */
    public void exportButton() {
        try {
            export = new JButton(resizeImage("./src/toolbarIcons/export.png"));
            export.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load export button");
        }
    }

    /**
     * Method to create the undo button and set its icon and instantiate an action
     * listener.
     */
    public void undoButton() {
        try {
            undo = new JButton(resizeImage("./src/toolbarIcons/undo.png"));
            undo.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load undo button");
        }
    }

    /**
     * Method to create the redo button and set its icon and instantiate an action
     * listener.
     */
    public void redoButton() {
        try {
            redo = new JButton(resizeImage("./src/toolbarIcons/redo.png"));
            redo.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load redo button");
        }
    }

    /**
     * Method to create the zoomIn button and set its icon and instantiate an action
     * listener.
     */
    public void zoomInButton() {
        try {
            zoomIn = new JButton(resizeImage("./src/toolbarIcons/zoomIn.png"));
            zoomIn.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load zoomIn button");
        }
    }

    /**
     * Method to create the zoomOut button and set its icon and instantiate an
     * action listener.
     */
    public void zoomOutButton() {
        try {
            zoomOut = new JButton(resizeImage("./src/toolbarIcons/zoomOut.png"));
            zoomOut.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load zoomOut button");
        }
    }

    /**
     * Method to create the resize button and set its icon and instantiate an action
     * listener.
     */
    public void resizeButton() {
        try {
            resize = new JButton(resizeImage("./src/toolbarIcons/resize.png"));
            resize.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load resize button");
        }
    }

    /**
     * Method to create the rotateClockwise button and set its icon and instantiate
     * an action listener.
     */
    public void rotateClockwiseButton() {
        try {
            rotateClockwise = new JButton(resizeImage("./src/toolbarIcons/rotateClockwise.png"));
            rotateClockwise.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load rotateClockwise button");
        }
    }

    /**
     * Method to create the rotateAntiClockwise button and set its icon and
     * instantiate an action listener.
     */
    public void rotateAntiClockwiseButton() {
        try {
            rotateAntiClockwise = new JButton(resizeImage("./src/toolbarIcons/rotateAntiClockwise.png"));
            rotateAntiClockwise.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load rotateAntiClockwise button");
        }
    }

    /**
     * Method to create the flipHorizontal button and set its icon and instantiate
     * an action listener.
     */
    public void flipHorizontalButton() {
        try {
            flipHorizontal = new JButton(resizeImage("./src/toolbarIcons/flipHorizontal.png"));
            flipHorizontal.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load flipHorizontal button");
        }
    }

    /**
     * Method to create the flipVertical button and set its icon and instantiate an
     * action listener.
     */
    public void flipVerticalButton() {
        try {
            flipVertical = new JButton(resizeImage("./src/toolbarIcons/flipVertical.png"));
            flipVertical.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load flipVertical button");
        }
    }

    public void macroStartButton() {
        try {
                macro = new JButton(resizeImage("./src/toolbarIcons/recBlack.png"));
                macro.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load macro button");
        }
    }

    public void macroStopButton() {
        try {
            macro = new JButton(resizeImage("./src/toolbarIcons/recRed.png"));
            macro.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load macro button");
        }
    }

    /**
     * Method which reads in an image, changes its size and then casts it to an icon
     * for use on JButtons.
     * 
     * @throws Exception If something goes wrong.
     */
    public ImageIcon resizeImage(String path) throws Exception {
        Image image = ImageIO.read(new File(path));
        image = image.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }

    /**
     * An action listener class to call the action methods when toolbar buttons are
     * pressed.
     */
    public class Listener implements ActionListener {
        /**
         * A method to call the function based off actions on the toolbar buttons.
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Calls the open function.
            if (e.getSource() == open) {
                fileAction.actions.get(0).actionPerformed(e);
            }
            // Calls the save function.
            if (e.getSource() == save) {
                fileAction.actions.get(1).actionPerformed(e);
            }
            // Calls the export function.
            if (e.getSource() == export) {
                fileAction.actions.get(3).actionPerformed(e);
            }
            // Calls the undo function.
            if (e.getSource() == undo) {
                editAction.actions.get(0).actionPerformed(e);
            }
            // Calls the redo function.
            if (e.getSource() == redo) {
                editAction.actions.get(1).actionPerformed(e);
            }
            // Calls the zoomIn function.
            if (e.getSource() == zoomIn) {
                viewAction.actions.get(0).actionPerformed(e);
            }
            // Calls the zoomOut function.
            if (e.getSource() == zoomOut) {
                viewAction.actions.get(1).actionPerformed(e);
            }
            // Calls the resize function.
            if (e.getSource() == resize) {
                imageAction.actions.get(2).actionPerformed(e);
            }
            // Calls the rotateClockwise function.
            if (e.getSource() == rotateClockwise) {
                imageAction.actions.get(0).actionPerformed(e);
            }
            // Calls the rotateAntiClockwise function.
            if (e.getSource() == rotateAntiClockwise) {
                imageAction.actions.get(1).actionPerformed(e);
            }
            // Calls the flipHorizontal function.
            if (e.getSource() == flipHorizontal) {
                imageAction.actions.get(3).actionPerformed(e);
            }
            // Calls the flipVertical function.
            if (e.getSource() == flipVertical) {
                imageAction.actions.get(4).actionPerformed(e);
            }
            if (e.getSource() == macro) {
                if (EditableImage.macroRunning) {
                    macroAction.actions.get(1).actionPerformed(e);
                    try {
                        Andie.resetToolbar();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    macroAction.actions.get(0).actionPerformed(e);
                    try {
                        Andie.resetToolbar();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } 
        }
    }
}
