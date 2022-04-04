package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar{
    JButton save;
    JButton greyscale;
    //Listener actionListener = new Listener();
    FileActions fileaction = new FileActions();
    ColourActions colourAction = new ColourActions();
    EditActions editAction = new EditActions();
    FilterActions filterAction = new FilterActions();
    Toolbar() {
        // FileActions fileaction = new FileActions();
        // ColourActions colourAction = new ColourActions();
        // EditActions editAction = new EditActions();
        // FilterActions filterAction = new FilterActions();
        saveButton();
        greyscaleButton();
        }
    
    public JToolBar createToolBar(){
        JToolBar toolbar = new JToolBar(BorderLayout.WEST);
        toolbar.setFloatable(false);
        toolbar.add(save);
        toolbar.add(greyscale);
        return toolbar;
    }

    public void saveButton(){
        try {
            Icon icon = new ImageIcon("./src/toolbarIcons/save.png");
            save = new JButton(icon); 
            save.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load");
        }
    }
    public void greyscaleButton(){
        try {
            Icon icon = new ImageIcon("./src/toolbarIcons/greyscale.png");
            greyscale = new JButton(icon); 
            greyscale.addActionListener(new Listener());
        } catch (Exception e) {
            System.out.println("failed to load");
        }
    }
    // public void saveButton(){
    //     try {
    //         Icon icon = new ImageIcon("./src/toolbarIcons/save.png");
    //         save = new JButton(icon); 
    //         save.addActionListener(actionListener);
    //     } catch (Exception e) {
    //         System.out.println("failed to load");
    //     }
    // }
    public class Listener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == save){
                fileaction.actions.get(1).actionPerformed(e);
                //System.out.println(fileaction.actions.get(1));
            }
            if(e.getSource() == greyscale){
                colourAction.actions.get(0).actionPerformed(e);
            }
         }

}
}