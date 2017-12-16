
import javax.swing.*;
import java.awt.*;

/*
same manner getcreditsmenu should be here so we have menus like diagram.
*/

public class CreditsMenu extends JPanel{
  JButton backButton1;
  JPanel creditsPanel;
    
  public JPanel initComponents()
    {
        JLabel background= new JLabel(new ImageIcon("background.jpg"));
        JPanel backPanel = new JPanel();
        JLabel creditsLogo= new JLabel(new ImageIcon("credits.gif"));

        backButton1 = new JButton("Back");
        backPanel.setLayout(new GridLayout());
        backPanel.setBounds(670,600, 100,  100 );
        backPanel.setSize(100,50);
        backPanel.add(backButton1);
        background.add(backPanel);

        creditsLogo.setBounds(70,20, 200,  100 );
        background.add(creditsLogo);

        creditsPanel = new JPanel();
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));
        creditsPanel.add(background);

        return creditsPanel;

    }
  
  
}




