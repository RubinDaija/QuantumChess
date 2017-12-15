import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuManager extends JFrame{
    private MouseListener mls;
    
    JButton btHelp;
    JButton btCredits;
    JButton btPlayGame;
    JButton btBack;

    CardLayout card;
    JPanel cardPanel;
    
    MainMenu mainMenuObj = new MainMenu();
    CreditsMenu creditsMenuObj = new CreditsMenu();
    HelpMenu helpMenuObj = new HelpMenu();


    final int FRAME_DIMENSION_WIDTH = 800;
    final int FRAME_DIMENSION_HEIGHT = 700;

    public MenuManager(){
    this.initComponents();
    this.setButtonListeners();
    }
    
    public void initComponents(){
        card = new CardLayout();
        cardPanel = new JPanel(card);


        this.setTitle("QuantumChess Game");

        this.setSize(800,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Getting the Dimension of the Screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() -this.getWidth()) / 2);
        int y = (int) (((dimension.getHeight()) - this.getHeight()) / 2 );
        this.setLocation(x, y);

       this.setVisible(true);
     

        cardPanel.add(mainMenuObj.initComponents(),"Menu"); //-----
        cardPanel.add(creditsMenuObj.initComponents(),"Credits");   //-----
        cardPanel.add(helpMenuObj.initComponents(),"Help");   //-----
        //cardPanel.add(new HelpMenu("background.jpg"),"About");
        //cardPanel.add(new HelpMenu("background.jpg"),"Credits");
        //cardPanel.add(new HelpMenu("background.jpg"),"StartGame");
        
        this.add(cardPanel);
        // menuFrame.pack();
        
        this.setVisible(true);
        card.show(cardPanel,"Menu");
        
       
        
    }
    
    
    public void setButtonListeners() {
    mls = (new MouseListener() {
        @Override  
        public void mouseClicked(MouseEvent e) {


            showFrame(e);   //send event to get source which button is clicked

        }


        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    });
    // initilaze listener to buttons

   /* buttons should be only here. */

    
    mainMenuObj.aboutButton.addMouseListener(mls);
    mainMenuObj.creditsButton.addMouseListener(mls);
    creditsMenuObj.backButton1.addMouseListener(mls);
    helpMenuObj.backButton2.addMouseListener(mls);
   // menuPanelObj.backButton.addMouseListener(mls);
    mainMenuObj.newGameButton.addMouseListener(mls);

}
    public void showFrame(MouseEvent e) {
        JComponent selecetedButton = (JComponent) e.getSource();
        CardLayout card = (CardLayout)(cardPanel.getLayout());
      //  if(selecetedButton == menuPanelObj.backButton)
      //  {
      //      card.show(cardPanel,"Menu");

        //}
         if(selecetedButton == creditsMenuObj.backButton1)
        {
            card.show(cardPanel,"Menu");

        }
        else  if(selecetedButton == helpMenuObj.backButton2)
        {
            card.show(cardPanel,"Menu");

        }
        else if(selecetedButton == mainMenuObj.creditsButton)
        {
            card.show(cardPanel,"Credits");

        }
      //  else if(selecetedButton == new MainMenu().newGameButton)
      //  {
      //      card.show(cardPanel,"StartGame");

     //   }
        else if(selecetedButton == mainMenuObj.aboutButton)
        {
            card.show(cardPanel,"Help");

        }

    
    } 

    public static void main(String[] args) {


        Runnable r = new Runnable() {
            public void run() {

                new MenuManager();
            }
        };
        SwingUtilities.invokeLater(r);

    }
}
