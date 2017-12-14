import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

/*this should be MenuManager */
public class menu extends JFrame
{

    menuPanel menuPanelObj;

    CardLayout card;
    JPanel cardPanel;

    JButton btHelp;
    JButton btCredits;
    JButton btPlayGame;
    JButton btBack;
    private MouseListener mls;
    final int FRAME_DIMENSION_WIDTH = 800;
    final int FRAME_DIMENSION_HEIGHT = 700;
    String BACKGROUND_PATH ="background.jpg";
    String LOGO_PATH = "chess.gif";


    public menu()
    {
        initComponents();

        setButtonListeners();
    }
    public void initComponents(){
        card = new CardLayout();
        cardPanel = new JPanel(card);

        menuPanelObj = new menuPanel();
        this.setTitle("QuantumChess Game");

        this.setSize(FRAME_DIMENSION_WIDTH,FRAME_DIMENSION_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Getting the Dimension of the Screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() -this.getWidth()) / 2);
        int y = (int) (((dimension.getHeight()) - this.getHeight()) / 2 );
        this.setLocation(x, y);

        this.setVisible(true);

        cardPanel.add(menuPanelObj.getMenuPanel(),"Menu");
        cardPanel.add(menuPanelObj.getAboutPanel(),"About");
        cardPanel.add(menuPanelObj.getCreditsPanel(),"Credits");
        cardPanel.add(menuPanelObj.getStartGamePanel(),"StartGame");
        cardPanel.add(new MainMenu(),"Click");


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
    menuPanelObj.aboutButton.addMouseListener(mls);
    menuPanelObj.creditsButton.addMouseListener(mls);
    menuPanelObj.backButton1.addMouseListener(mls);
    menuPanelObj.backButton2.addMouseListener(mls);
    menuPanelObj.backButton.addMouseListener(mls);
    menuPanelObj.newGameButton.addMouseListener(mls);

}
    public void showFrame(MouseEvent e) {
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
    public static void main(String[]args)
    {

        Runnable r = new Runnable() {
            public void run() {

                new menu();
            }
        };
        SwingUtilities.invokeLater(r);



    }

}
  