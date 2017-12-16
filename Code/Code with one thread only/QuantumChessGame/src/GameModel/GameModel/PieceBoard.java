package GameModel;

import javax.swing.*;
import java.awt.*;

public class PieceBoard extends JPanel{
    ImageIcon b_biShop = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\black_bishop_icon.png");
    ImageIcon b_queen = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\black_queen_icon.png");
    ImageIcon b_knight = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\black_knight_icon.png");
    ImageIcon b_rook = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\black_rook_icon.png");
    //ImageIcon b_king = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\black_bishop_icon.png");
    ImageIcon w_biShop = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\white_bishop_icon.png");
    ImageIcon w_queen = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\white_queen_icon.png");
    ImageIcon w_knight= new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\white_knight_icon.png");
    ImageIcon w_rook = new ImageIcon("C:\\Users\\jRkb6\\Documents\\GitHub\\QuantumChess\\Code\\Code with one thread only\\QuantumChessGame\\piece_icons\\white_rook_icon.png");


    JLabel lb_biShop = new JLabel(b_biShop);
    JLabel lb_queen = new JLabel(b_queen);
    JLabel lb_knight = new JLabel(b_knight);
    JLabel lb_rook = new JLabel(b_rook);
    JLabel lw_biShop = new JLabel(w_biShop);
    JLabel lw_queen = new JLabel(w_queen);
    JLabel lw_knight = new JLabel(w_knight);
    JLabel lw_rook = new JLabel(w_rook);
    JPanel whites = new JPanel();
    JPanel blacks = new JPanel();
    public PieceBoard(){
        initComponents();
    }

    public void initComponents(){
        whites.add(lw_biShop);
        whites.add(lw_knight);
        whites.add(lw_queen);
        whites.add(lw_rook);

        blacks.add(lb_biShop);
        blacks.add(lb_knight);
        blacks.add(lb_queen);
        blacks.add(lb_rook);
        
        this.setLayout(new BorderLayout());
        this.add(blacks,BorderLayout.SOUTH);
        this.add(whites,BorderLayout.NORTH);
        this.setVisible(true);
    }

    public static void main(String []args){
        PieceBoard pc = new PieceBoard();
        JFrame jf = new JFrame();
        jf.setPreferredSize(new Dimension(190,200));
        jf.add(pc);
        jf.pack();
        jf.setVisible(true);
    }
}
