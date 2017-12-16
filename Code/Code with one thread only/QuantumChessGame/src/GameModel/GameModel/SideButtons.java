package GameModel;

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
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
