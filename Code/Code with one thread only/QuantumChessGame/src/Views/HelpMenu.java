package Views;

import javax.swing.*;
import java.awt.*;
/*getAboutMenu should be here in the class. */
public class HelpMenu extends JPanel {

    JButton backButton2;
    JPanel  aboutPanel;


    
    public JPanel initComponents()
    {
        JLabel background= new JLabel(new ImageIcon("background.jpg"));
        JLabel aboutLogo= new JLabel(new ImageIcon("about.gif"));
        JPanel backPanel2 = new JPanel();

        backButton2 = new JButton("Back");
        backPanel2.setLayout(new GridLayout());
        backPanel2.setBounds(670,600, 100,  100 );
        backPanel2.setSize(100,50);
        backPanel2.add(backButton2);
        background.add(backPanel2);

        aboutLogo.setBounds(70,20, 200,  100 );
        background.add(aboutLogo);

        aboutPanel = new JPanel();
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        aboutPanel.add(background);

        return aboutPanel;



    }

    
        public static void main(String []args){
        new HelpMenu();
    }
    

}
