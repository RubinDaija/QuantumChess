package EntityModel;

import GameModel.Board;
import GameModel.SideButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class QuantumChess extends JFrame implements Runnable{

    private volatile  boolean running;
    private Thread thread;
    private volatile BufferStrategy boardBufferStrategy;

    private Board board;

    private SideButtons buttons; //side buttons

    public QuantumChess(int width,int height) {
        setLayout(new BorderLayout()); //for the buttons too
        board = new Board(width, height);

        buttons = new SideButtons(190,height,board);//side buttons
    }

    public void create(){


        board.create();
        getContentPane().add(board , BorderLayout.CENTER);
        getContentPane().add(buttons,BorderLayout.LINE_END);
        validate();
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
