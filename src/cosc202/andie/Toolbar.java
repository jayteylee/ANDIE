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
    Listener actionListener = new Listener();
    Toolbar() {
        FileActions fileaction = new FileActions();
        ColourActions colourAction = new ColourActions();
        EditActions editAction = new EditActions();
        FilterActions filterAction = new FilterActions();
        saveButton();
        }
    
    public JToolBar createToolBar(){
        JToolBar toolbar = new JToolBar(BorderLayout.WEST);
        toolbar.setFloatable(false);
        toolbar.add(save);
        return toolbar;
    }

    public void saveButton(){
        try {
            Icon icon = new ImageIcon("./src/toolbarIcons/save.png");
            save = new JButton(icon); 
            save.addActionListener(actionListener);
        } catch (Exception e) {
            System.out.println("failed to load");
        }
     
    }
    public class Listener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            e.getSource();
         }

}
}
