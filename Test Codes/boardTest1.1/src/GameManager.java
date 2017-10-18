import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameManager {
    public static void main(String[] args){
        final QuantumChess quantumChess = new QuantumChess(480,480);
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
