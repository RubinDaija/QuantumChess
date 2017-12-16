import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;


public class gameScreen //extends JFrame
{
  
  public static void main(String[] args) 
  {
    String player1Name = "";
    String player2Name = "";
    
    JLabel playOneLabel = new JLabel();
    JLabel playTwoLabel = new JLabel();
    
    playOneLabel.setText(player1Name);
    playTwoLabel.setText(player2Name);
    
    
    
    
    
    JFrame frame = new JFrame("JLabel");
    
    JLabel background=new JLabel(new ImageIcon("background.jpg"));
    
    background.add(playOneLabel);
    background.add(playTwoLabel);
    
    int frameSizeX = 1000;
    int frameSizeY = 1000;
    
    frame.setSize(frameSizeX, frameSizeY);
    
    //Putting the main frame into center of the window 
    
    Dimension windowSize = frame.getSize();
    
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Point centerPoint = ge.getCenterPoint();
    
    int dx = centerPoint.x - windowSize.width / 2;
    int dy = centerPoint.y - windowSize.height / 2;  
    
    //Tunneling    
    
    Panel tunnellingPanel=new Panel();
    Button tunnelingButton=new Button("Tunneling");
    
    tunnellingPanel.setBounds(frameSizeX-200,100, dx,  50 );
    tunnellingPanel.setLayout(new BoxLayout(tunnellingPanel, BoxLayout.Y_AXIS));
    
    tunnellingPanel.setSize(100,50);
    JLabel tunnelingLogo=new JLabel(new ImageIcon("tunneling.jpg"));
    tunnelingLogo.setBounds(frameSizeX-600,0, frameSizeY-100,  100);
    tunnellingPanel.add(tunnelingButton);
    
    background.add(tunnelingLogo);
    background.add(tunnellingPanel);


    
    //Entanglement
    
    Panel engtanglementPanel=new Panel();
    Button engtanglementButton=new Button("Entanglement");
    
    engtanglementPanel.setBounds(frameSizeX-200,260, dx,  50 );
    engtanglementPanel.setLayout(new BoxLayout(engtanglementPanel, BoxLayout.Y_AXIS));
    
    engtanglementPanel.setSize(120,50);
    JLabel entanglementLogo=new JLabel(new ImageIcon("entanglement.jpg"));
    entanglementLogo.setBounds(frameSizeX-600,0, frameSizeY-100,  410);
    engtanglementPanel.add(engtanglementButton);
    
    background.add(entanglementLogo);
    background.add(engtanglementPanel);
    
    //Superposition 
    
    Panel superpositionPanel=new Panel();
    Button superpositionButon=new Button("Superposition");
    
    superpositionPanel.setBounds(frameSizeX-200,430, dx,  50 );
    superpositionPanel.setLayout(new BoxLayout(superpositionPanel, BoxLayout.Y_AXIS));
    
    
    superpositionPanel.setSize(120,50);
    JLabel superPositionLogo=new JLabel(new ImageIcon("superposition.jpg"));
    superPositionLogo.setBounds(frameSizeX-600,0, frameSizeY-100,  740);
    superpositionPanel.add(superpositionButon);
    
    background.add(superPositionLogo);
    background.add(superpositionPanel);
    
    
    //Cancel
    
    Panel cancelPanel=new Panel();
    Button cancelButton=new Button("Cancel");
    
    cancelPanel.setBounds(frameSizeX-200,530, dx,  50 );
    cancelPanel.setLayout(new BoxLayout(cancelPanel, BoxLayout.Y_AXIS));  
    cancelPanel.setSize(120,50);
    cancelPanel.add(cancelButton);
    
    background.add(cancelPanel);
    
    
    //Surrender
    Panel surrenderPanel=new Panel();
    Button surrenderButton=new Button("Surrender");
    
    surrenderPanel.setBounds(frameSizeX-200,600, dx,  50 );
    surrenderPanel.setLayout(new BoxLayout(surrenderPanel, BoxLayout.Y_AXIS));  
    surrenderPanel.setSize(120,50);
    surrenderPanel.add(surrenderButton);
    
    background.add(surrenderPanel);
    
    

    frame.add(background);    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(dx, dy); 
    frame.setVisible(true);
    
  }
  
}
  