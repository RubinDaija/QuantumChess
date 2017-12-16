package Views;

import EntityModel.GameManager;
import EntityModel.Player;
import EntityModel.QuantumChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WinnerPopUp extends JFrame{
    ImageIcon bck = new ImageIcon("congratulations.png"); /* to get Image size */
    JLabel background = new JLabel(bck);
    JPanel winnerName = new JPanel();
    JLabel playerInfo = new JLabel();
    JButton ok = new JButton("OK");
    JButton quit = new JButton("Quit");
    MouseListener mls;
    public  WinnerPopUp(Player player){

        this.playerInfo.setText("<html><center>Congratz to player"+player.getPlayerNo() + "<br>"+player.getPlayerName()+" </center></html>"); //use html for multi lines
        winnerName.add(playerInfo);
        background.add(playerInfo);
        this.setContentPane(background);
        this.setPreferredSize(new Dimension(bck.getIconWidth(),bck.getIconHeight()));

        this.setTitle("Congratz to Winner");
        this.pack();
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
