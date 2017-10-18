import javax.swing.*;
import java.awt.image.BufferStrategy;

public class QuantumChess extends JFrame implements Runnable{

    private volatile  boolean running;
    private Thread thread;
    private volatile BufferStrategy boardBufferStrategy;

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

        boardBufferStrategy = board.setBufferStrategy();
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        running = true;
        while(running) {
            board.boardGraphics();
        }
    }

    public void onWindowClosing(){
        try{
            running = false;
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }


}
