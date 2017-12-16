package GameModel;

import EntityModel.King;
import EntityModel.Pawn;
import EntityModel.Piece;
import EntityModel.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SideButtons extends JPanel{
    JButton supperpos;
    JButton entanglement;
    JButton tunneling;
    JButton observe;
    JButton surrender;
    JButton swap;
    JPanel buttons = new JPanel();
    private JPanel turnPlayer = new JPanel(new GridLayout(2,0));
    private JLabel turnInfo = new JLabel(new ImageIcon("turn-logo.jpg"));
    private JLabel playerName = new JLabel("");



    public SideButtons(int widht, int height, Board board){
        supperpos = new JButton("SupperPosition");
        entanglement = new JButton("Entanglement");
        tunneling = new JButton("Tunneling");
        observe = new JButton("Observe");
        surrender = new JButton("Surrender");
        swap = new JButton("Swap");

        supperpos.addActionListener(board);
        entanglement.addActionListener(board);
        tunneling.addActionListener(board);
        observe.addActionListener(board);
        surrender.addActionListener(board);
        swap.addActionListener(board);



        playerName.setFont(new Font("Serif", Font.PLAIN, 25));

        buttons.setPreferredSize(new Dimension(widht,height/2));
        buttons.setBackground(Color.white);
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.add(supperpos);
        buttons.add(entanglement);
        buttons.add(tunneling);
        buttons.add(observe);
        buttons.add(surrender);
        buttons.add(swap);


        turnPlayer.setPreferredSize(new Dimension(widht,30));

        setPreferredSize(new Dimension(widht,height));
        setLayout(new BorderLayout());
        turnPlayer.setBackground(Color.WHITE);
        add(buttons,BorderLayout.NORTH);
        add(turnPlayer,BorderLayout.CENTER);
        disableEverything();
    }

    public void update(Piece piece, int entanglement, int tunneling, boolean superposallowed, boolean issuperpos, Player pl){
        if (piece.isDummy() || piece.isSupperPos()){
            observe.setVisible(true);
        }
        else {
            if (piece.getClass() == King.class) {
                surrender.setVisible(true);
            }
            if (piece.getClass() == Pawn.class) {
                if ((piece.getPlayer() == 1) && (piece.getPosY() == 0)) {
                    swap.setVisible(true);
                } else if ((piece.getPlayer() == 2) && (piece.getPosY() == 7)) {
                    swap.setVisible(true);
                }
            }
            if (superposallowed) {
                supperpos.setVisible(true);
            }
            if (tunneling > 0) {
                this.tunneling.setVisible(true);
            }
            if (entanglement > 0) {
                this.entanglement.setVisible(true);
            }
            if (issuperpos) {
                observe.setVisible(true);
            }
        }
        turnPlayer.add(turnInfo);
        turnInfo.setHorizontalAlignment(JLabel.CENTER);
        playerName.setText(pl.getPlayerName());
        playerName.setHorizontalAlignment(JLabel.CENTER);
        turnPlayer.add(playerName);
        turnPlayer.setVisible(true);

    }

    public void disableEverything(){
        supperpos.setVisible(false);
        entanglement.setVisible(false);
        tunneling.setVisible(false);
        observe.setVisible(false);
        surrender.setVisible(false);
        swap.setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
