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

        setPreferredSize(new Dimension(widht,height));
        setBackground(Color.white);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(supperpos);
        add(entanglement);
        add(tunneling);
        add(observe);
        add(surrender);
        add(swap);
<<<<<<< HEAD
        this.setVisible(true);
=======

        disableEverything();
    }

    public void update(Piece piece,int entanglement,int tunneling,boolean superposallowed,boolean issuperpos){
        if (piece.isDummy()){
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
>>>>>>> 263ba5f3874e94511bc784dd1d28198150be7b93
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
