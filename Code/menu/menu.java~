import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class menu
{
  JFrame menuFrame;
  menuPanel menuPanelObj;
  
  CardLayout card;
  JPanel cardPanel;
  
  public menu()
  {
    card = new CardLayout();
    cardPanel = new JPanel(card);
    
    menuPanelObj = new menuPanel();
    menuFrame = new JFrame("Main Menu");

    menuFrame.setSize(800,700);
    menuFrame.setResizable(false);
    menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Getting the Dimension of the Screen
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - menuFrame.getWidth()) / 2);
    int y = (int) (((dimension.getHeight()) - menuFrame.getHeight()) / 2 );
    menuFrame.setLocation(x, y);
    
    menuFrame.setVisible(true);
    
    cardPanel.add(menuPanelObj.getMenuPanel(),"Menu");
    cardPanel.add(menuPanelObj.getAboutPanel(),"About");
    cardPanel.add(menuPanelObj.getCreditsPanel(),"Credits");
    cardPanel.add(menuPanelObj.getStartGamePanel(),"StartGame");
  
     
    menuFrame.add(cardPanel);
   // menuFrame.pack();
    menuFrame.setVisible(true);
    
    card.show(cardPanel,"Menu");
    
    menuPanelObj.backButton.addActionListener(new listener());
    menuPanelObj.backButton1.addActionListener(new listener());
    menuPanelObj.backButton2.addActionListener(new listener());
    menuPanelObj.aboutButton.addActionListener(new listener());
    menuPanelObj.creditsButton.addActionListener(new listener());
    menuPanelObj.newGameButton.addActionListener(new listener());
    
  //  menuFrame.setVisible(true);
  }
  
  class listener implements ActionListener
  {
   
    public void actionPerformed(ActionEvent e)
    {
      JComponent selecetedButton = (JComponent) e.getSource();
      CardLayout card = (CardLayout)(cardPanel.getLayout());
     
     if(selecetedButton == menuPanelObj.backButton)
     {
       card.show(cardPanel,"Menu");
       
     }
      else if(selecetedButton == menuPanelObj.backButton1)
     {
       card.show(cardPanel,"Menu");
       
     }
      else  if(selecetedButton == menuPanelObj.backButton2)
     {
       card.show(cardPanel,"Menu");
       
     }
     else if(selecetedButton == menuPanelObj.creditsButton)
     {
       card.show(cardPanel,"Credits");

     }
     else if(selecetedButton == menuPanelObj.newGameButton)
     {
       card.show(cardPanel,"StartGame");

     }
     else if(selecetedButton == menuPanelObj.aboutButton)
     {
       card.show(cardPanel,"About");
       
     }

     
      
    }
      
    
    
  }
  
  
  public static void main(String[]args)
  {
    menu start = new menu();
  }
  
}
  