package Views;

import javax.swing.*;
import java.awt.*;

public class playerNamesPanel extends JPanel {

    JButton okay = new JButton("OK");
    JLabel  label;
    JButton backButton3;
    JPanel backPanel3;
    JPanel namesPanel;
    JTextField player1Input;
    JTextField player2Input;
    String player1Name = "player1";
    String player2Name= "player2";
    JButton startBtn;
    JPanel startBtnPanel;


    public JPanel initComponents()
    {
        JLabel background= new JLabel(new ImageIcon("background.jpg"));
        JPanel backPanel3 = new JPanel();

        startBtn = new JButton("Start Game");
        startBtnPanel = new JPanel();
        startBtnPanel.setLayout(new GridLayout());
        startBtnPanel.setBounds(450,600, 200,  200 );
        startBtnPanel.setSize(100,50);
        startBtnPanel.add(startBtn);


        player1Input = new JTextField("Player One");
        player2Input = new JTextField("Player Two");

        player1Input.setBounds(370,170,100,20);
        player2Input.setBounds(560,170,100,20);

        label = new JLabel("ENTER NAME              Player1:            Player2:");
        label.setBounds(100,100, 700,  100 );
        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 24));

        backButton3 = new JButton("Back");
        backPanel3.setLayout(new GridLayout());
        backPanel3.setBounds(670,600, 200,  200 );
        backPanel3.setSize(100,50);
        backPanel3.add(backButton3);
        background.add(backPanel3);
        background.add(label);
        background.add(player1Input);
        background.add(player2Input);
        background.add(startBtnPanel);



        namesPanel = new JPanel();
        namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.Y_AXIS));
        namesPanel.add(background);

        return namesPanel;



    }


    public static void main(String []args){

        JFrame f = new JFrame("Names");

        f.setTitle("QuantumChess Game");

        f.setSize(800,700);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Getting the Dimension of the Screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() -f.getWidth()) / 2);
        int y = (int) (((dimension.getHeight()) - f.getHeight()) / 2 );
        f.setLocation(x, y);

        f.add(new playerNamesPanel().initComponents());

        f.setVisible(true);

    }





}
