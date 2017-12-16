package Views;

import EntityModel.GameManager;
import EntityModel.Player;
import EntityModel.QuantumChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WinnerPopUp extends JFrame{
    ImageIcon bck = new ImageIcon("con.gif"); /* to get Image size */
    JLabel background = new JLabel(bck);
    JPanel mainP = new JPanel();
    JPanel winnerName = new JPanel();
    JLabel playerInfo = new JLabel();
    JButton ok = new JButton("OK");
    JButton quit = new JButton("Quit");





    MouseListener mls;
    public  WinnerPopUp(Player player){
        this.setTitle("Congratz to Winner");
        this.pack();
        this.setSize(300,230);
        this.setResizable(false);

        //Getting the Dimension of the Screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() -this.getWidth()) / 2);
        int y = (int) (((dimension.getHeight()) - this.getHeight()) / 2 );
        this.setLocation(x, y);

        ok.setForeground(Color.BLUE);
        ok.setBackground(Color.BLUE);

        quit.setForeground(Color.BLUE);

        JPanel quitButtonPanel = new JPanel();
        quitButtonPanel.setBounds(180,170, 50, 50 );
        quitButtonPanel.setLayout(new GridLayout());
        quitButtonPanel.setSize(70,30);
        quitButtonPanel.add(quit);


        JPanel okButtonPanel = new JPanel();
        okButtonPanel.setBounds(20,170, 50, 50 );
        okButtonPanel.setLayout(new GridLayout());
        okButtonPanel.setSize(60,30);
        okButtonPanel.add(ok);


        this.playerInfo.setText("<html><center> TO PLAYER  "+player.getPlayerNo() + "  " +player.getPlayerName()+" </center></html>"); //use html for multi lines
        playerInfo.setBounds(100,10, 100, 300 );
        playerInfo.setForeground(Color.RED);
        playerInfo.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        //winnerName.setLayout(new GridLayout());
        //  winnerName.setSize(100,100);
        // winnerName.add(playerInfo);
        //background.add(playerInfo);
        background.add(playerInfo);
        // this.add(playerInfo);
        background.add(okButtonPanel);
        background.add(quitButtonPanel);
        // background.add(quit);
        mainP.add(background);
        this.add(mainP);
        this.setContentPane(mainP);
        //this.setPreferredSize(new Dimension(bck.getIconWidth(),bck.getIconHeight()));
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    void setButtonListeners(){
        mls = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource().equals(ok)){
                    new MenuManager();
                }
                else if(e.getSource().equals(quit)){
                    dispose();
                    //GameManager.dispose();
                }
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
        };
        ok.addMouseListener(mls);
        quit.addMouseListener(mls);
    }

    public static void main(String []args)
    {
        Player serhat = new Player("Serhat",1);
        new WinnerPopUp(serhat);
    }
}
