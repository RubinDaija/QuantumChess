import javax.swing.*;
import java.awt.*;

public class menuPanel extends JPanel
{
  //Panels
  JPanel mainMenuPanel;
  JPanel creditsPanel;
  JPanel aboutPanel;
  JPanel playPanel;
  
  //Buttons
  JButton aboutButton;
  JButton creditsButton;
  JButton newGameButton;
  JButton backButton;
     
  JButton backButton1;
  JButton backButton2;
  
  public menuPanel()
  {
     /*   backButton = new JButton("Back");
   backButton1 = new JButton("Back");
  backButton2 = new JButton("Back");*/
  }
  
  //Returns menuPanel
  public  JPanel getMenuPanel()
  {
  
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

      
    background.add(startButtonPanel);
    background.add(aboutButtonPanel);
    background.add(creditsButtonPanel);
    
    chessLogo.setBounds(30,40, 350,  300 );
    background.add(chessLogo);

    mainMenuPanel = new JPanel();
         
    mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
    mainMenuPanel.add(background);
    
   
     
    return mainMenuPanel;   
      
  }
  
  //Returns Credits Panel
  public JPanel getCreditsPanel()
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
  
  public JPanel getAboutPanel()
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
  
   public JPanel getStartGamePanel()
  {
      JLabel background= new JLabel(new ImageIcon("background.jpg"));
      JPanel backPanel3 = new JPanel();
      
      backButton = new JButton("Back");
      backPanel3.setLayout(new GridLayout());
      backPanel3.setBounds(670,600, 100,  100 );
      backPanel3.setSize(100,50);
      backPanel3.add(backButton);
      background.add(backPanel3);
      
      playPanel = new JPanel();
      playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.Y_AXIS));
      playPanel.add(background);
      
      return playPanel;
      
      
    
  }
  
  
  /*
  public static void main(String[]args)
  {
     menuPanel  panelObj = new menuPanel();
    //Creating Main Frame
     JFrame main = new JFrame("Main Menu");

     main.setSize(800,700);
     main.setResizable(false);
     main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
          
     //Getting the Dimension of the Screen
     Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
     int x = (int) ((dimension.getWidth() - main.getWidth()) / 2);
     int y = (int) (((dimension.getHeight()) - main.getHeight()) / 2 );
      main.setLocation(x, y);
     
     
  //   main.setBackground(Color.blue);
   //  main.setLayout(new FlowLayout());
     
     
  //   main.add(panelObj.getMenuPanel()) ;
  //     main.add(panelObj.getCreditsPanel());
      main.add(panelObj.getAboutPanel());
     
      main.setVisible(true);

    
  }
  */
}