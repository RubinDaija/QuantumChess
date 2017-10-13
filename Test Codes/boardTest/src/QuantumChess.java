import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuantumChess extends JFrame{


    private Board board;

    public QuantumChess(int width,int height) {
        board = new Board(width, height);
    }

    public void create(){
        board.create();
        getContentPane().add(board);
        setTitle("Quantum Chess");
        setIgnoreRepaint(true);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        board.setBufferStrategy();
    }



    public void onWindowClosing(){
        board.onClose();
        System.exit(0);
    }

    public static void main(String[] args){
        final QuantumChess quantumChess = new QuantumChess(400,400);
        quantumChess.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quantumChess.onWindowClosing();
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                quantumChess.create();
            }
        });
    }
}
