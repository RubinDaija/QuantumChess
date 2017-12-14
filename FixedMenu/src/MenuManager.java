import javax.swing.*;
import java.awt.*;

public class MenuManager extends JFrame{
    JButton btHelp;
    JButton btCredits;
    JButton btPlayGame;
    JButton btBack;

    CardLayout card;
    JPanel cardPanel;

    final int FRAME_DIMENSION_WIDTH = 800;
    final int FRAME_DIMENSION_HEIGHT = 700;

    public MenuManager(){}
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

        cardPanel.add(new MainMenu(),"Menu");
        //cardPanel.add(new HelpMenu("background.jpg"),"About");
        //cardPanel.add(new HelpMenu("background.jpg"),"Credits");
        //cardPanel.add(new HelpMenu("background.jpg"),"StartGame");


        this.add(cardPanel);
        // menuFrame.pack();
        this.setVisible(true);

        card.show(cardPanel,"Menu");
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
