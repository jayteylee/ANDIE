package cosc202.andie;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import javax.imageio.*;

// test 
/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {
    protected static JFrame frame = new JFrame("ANDIE");
    protected static Toolkit tk = Toolkit.getDefaultToolkit();  
    protected static int xSize = ((int) tk.getScreenSize().getWidth());  
    protected static int ySize = ((int) tk.getScreenSize().getHeight()); 
    protected static JToolBar tbar = new JToolBar();
    Toolbar toolbar = new Toolbar();
    // protected static JMenuBar menuBar = new JMenuBar();
    // protected static FileActions fileActions = new FileActions();
    // protected static ImageTransformations imageTransformations = new ImageTransformations();
    // protected static EditActions editActions = new EditActions();
    // protected static ViewActions viewActions = new ViewActions();
    // protected static FilterActions filterActions = new FilterActions();
    // protected static ColourActions colourActions = new ColourActions();
    // protected static MacroActions macroActions = new MacroActions();
    // protected static MouseActions mouseActions = new MouseActions();
    protected static ImagePanel imagePanel = new ImagePanel();
    // protected static JScrollPane scrollPane = new JScrollPane(imagePanel);
    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save, edit, etc. 
     * These operations are implemented {@link ImageOperation}s and triggerd via 
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        // Set up the main GUI frame
        

        try{
        Image image = ImageIO.read(new File("./src/icon.png"));
        frame.setIconImage(image);
        System.err.println("Successfully loaded ANDIE icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch (Exception e) {
            System.err.println("Failed to load ANDIE icon");
        }
        
        // The main content area is an ImagePanel
        //ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        //ImageImplementations apply actions to images such as rotate, resize etc
        ImageTransformations imageTransformations = new ImageTransformations();
        menuBar.add(imageTransformations.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());
        //
        MacroActions macroActions = new MacroActions();
        menuBar.add(macroActions.createMenu());
        //
        MouseActions mouseActions = new MouseActions();
        menuBar.add(mouseActions.createMenu());
        //Creates a toolbar
        Toolbar toolbar = new Toolbar();
        tbar = toolbar.createToolBar();
        frame.setMinimumSize(new Dimension(520,450));
        frame.add(tbar, BorderLayout.NORTH);
        frame.setJMenuBar(menuBar);
        imagePanel.getImage().open("bin/whiteboard.jpg");
        frame.pack();
        frame.setVisible(true);
        
       
    }
    protected static void resetToolbar() throws Exception{
        frame.remove(tbar);
        Toolbar toolbar = new Toolbar();
        tbar = toolbar.createToolBar();
        frame.add(tbar, BorderLayout.NORTH);
        frame.setVisible(true);
        resizeFrame();
    }
    /**
     * A method which resizes the frame according to the new image size. If the image size is larger than the
     * screen size, maximise frame.
     * @author Jake
     */
    protected static void resizeFrame(){
        frame.pack();
        if(frame.getSize().getWidth()> xSize || frame.getSize().getHeight()> ySize){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    } 
    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    System.out.println(ex);
                    System.exit(1);
                }
            }
        });
    }
}
