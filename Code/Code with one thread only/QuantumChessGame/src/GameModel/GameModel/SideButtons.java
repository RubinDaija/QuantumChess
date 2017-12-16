package GameModel;

import EntityModel.King;
import EntityModel.Pawn;
import EntityModel.Piece;

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
    private JPanel turnPlayer = new JPanel(new BorderLayout());
    private JLabel turnInfo = new JLabel("AAAAAAAAAAAAAAAAAAA");


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

        buttons.setPreferredSize(new Dimension(widht,height));
        buttons.setBackground(Color.white);
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.add(supperpos);
        buttons.add(entanglement);
        buttons.add(tunneling);
        buttons.add(observe);
        buttons.add(surrender);
        buttons.add(swap);
        turnInfo.setText("AAAAA");
        turnPlayer.add(turnInfo,BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(buttons,BorderLayout.NORTH);
        add(turnPlayer,BorderLayout.SOUTH);
        disableEverything();
    }

    public void update(Piece piece,int entanglement,int tunneling,boolean superposallowed,boolean issuperpos){
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
