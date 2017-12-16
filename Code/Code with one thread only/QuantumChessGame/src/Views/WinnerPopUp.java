package Views;

import EntityModel.Player;

import javax.swing.*;
import java.awt.*;

public class WinnerPopUp extends JFrame{
    ImageIcon bck = new ImageIcon("congratulations.png"); /* to get Image size */
    JLabel background = new JLabel(bck);
    JPanel winnerName = new JPanel();
    JLabel playerInfo = new JLabel();
    JButton ok = new JButton("OK");
    public  WinnerPopUp(Player player){

        this.playerInfo.setText("<html><center>Congratz to player"+player.getPlayerNo() + "<br>"+player.getPlayerName()+" </center></html>"); //use html for multi lines
        winnerName.add(playerInfo);
        background.add(playerInfo);
        this.setContentPane(background);
        this.setPreferredSize(new Dimension(bck.getIconWidth(),bck.getIconHeight()));
        this.setTitle("Congratz to Winner");
        this.pack();
        this.setVisible(true);
    }
    public void initComponents(){}

    public static void main(String []args)
    {
        Player serhat = new Player("Serhat",1);
        new WinnerPopUp(serhat);
    }
}
