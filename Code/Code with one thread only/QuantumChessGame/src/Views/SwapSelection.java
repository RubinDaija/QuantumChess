package Views;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwapSelection extends JFrame {

    public String selection;
    private JButton btQueen;
    private JButton btKing;
    private JButton btRook;
    private JButton btBishop;
    JPanel buttons;
    MouseListener mls;
    public SwapSelection(){
        initComponents();
    }

    public void initComponents(){
        btQueen = new JButton("Queen");
        btKing = new JButton("King");
        btRook = new JButton("Rook");
        btBishop = new JButton("Bishop");
        buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(300,200));
        buttons.add(btBishop);
        buttons.add(btKing);
        buttons.add(btQueen);
        buttons.add(btRook);

        this.add(buttons);
        this.pack();
        this.setVisible(true);

        setButtonListeners();

    }
    //get selection from buttons to pass with selection property to board
    public void setButtonListeners(){
        mls = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btQueen){
                    selection = "queen";
                    showSurePopup(selection);

                }
                else if(e.getSource() == btBishop){
                    selection = "bishop";
                    showSurePopup(selection);
                }
                else if (e.getSource() == btKing){
                    selection = "king";
                    showSurePopup(selection);
                }
                else if (e.getSource() == btRook){
                    selection = "rook";
                    showSurePopup(selection);
                }
                else {
                    selection = "nullSelection";
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
        btQueen.addMouseListener(mls);
        btBishop.addMouseListener(mls);
        btRook.addMouseListener(mls);
        btKing.addMouseListener(mls);
    }
    public void showSurePopup(String selection){
        Object[] options = {"Yes,sure",
                "Cancel"};
        int n = JOptionPane.showOptionDialog(this,
                "Swap pawn  "
                        + "with" + selection+"?",
                "Selection Sure?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

            if(n == 0){
                this.dispose();
            }

    }
    public static void main(String args[]){
        JFrame main =new JFrame();
        main.setPreferredSize(new Dimension(300,500));
        SwapSelection swp = new SwapSelection();
        while(swp.isActive()){}
        String sp = swp.selection;
        JPanel asd = new JPanel();
        System.out.println(sp);
    }
}

