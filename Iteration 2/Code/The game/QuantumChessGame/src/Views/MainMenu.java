package Views;

import javax.swing.*;
import java.awt.*;
/*Does not show somehow */
public class MainMenu extends JPanel {


    JButton aboutButton;
    JButton creditsButton;
    JButton newGameButton;
    JPanel mainMenuPanel;

    public JPanel initComponents(){  ///------

        JLabel background= new JLabel(new ImageIcon("background.jpg"));
        JLabel chessLogo= new JLabel(new ImageIcon("chess.gif"));

        //Adding About button and its panel on menu panel
        JPanel aboutButtonPanel = new JPanel();
        aboutButtonPanel.setBounds(250,400, 100, 100 );
        aboutButtonPanel.setLayout(new GridLayout());
        aboutButtonPanel.setSize(300,50);
        aboutButton = new JButton("Help");
        aboutButtonPanel.add(aboutButton);

        //Adding Credits button and its panel on menu panel
        JPanel creditsButtonPanel = new JPanel();
        creditsButtonPanel.setBounds(250,500, 100, 100 );
        creditsButtonPanel.setLayout(new GridLayout());
        creditsButtonPanel.setSize(300,50);
        creditsButton = new JButton("Credits");
        creditsButtonPanel.add(creditsButton);

        //Adding Start button and its panel on menu panel
        JPanel startButtonPanel = new JPanel();
        newGameButton = new JButton("New Game");
        startButtonPanel.setLayout(new GridLayout());
        startButtonPanel.setBounds(250,300, 100,  100 );
        startButtonPanel.setSize(300,50);
        startButtonPanel.add(newGameButton);


        background.add(startButtonPanel);  //-----
        background.add(aboutButtonPanel);   //-----
        background.add(creditsButtonPanel);  //----

        chessLogo.setBounds(30,40, 350,  300 );
        background.add(chessLogo);

        mainMenuPanel = new JPanel();

        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.add(background);
        mainMenuPanel.setVisible(true);
        this.add(mainMenuPanel);
        
        return mainMenuPanel;  ///-----

    }


}
