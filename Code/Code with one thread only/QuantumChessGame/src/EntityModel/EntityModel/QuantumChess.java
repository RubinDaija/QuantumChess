package EntityModel;

import GameModel.Board;
import GameModel.SideButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferStrategy;

public class QuantumChess extends JFrame implements Runnable,WindowStateListener{

    private volatile  boolean running;
    private Thread thread;
    private volatile BufferStrategy boardBufferStrategy;

    private Board board;

    private SideButtons buttons; //side buttons

    public QuantumChess(int width,int height,String playerOne, String playerTwo) {
        setLayout(new BorderLayout()); //for the buttons too
        board = new Board(width, height);

        buttons = new SideButtons(200,height,board);//side buttons
        board.getButtonClass(buttons);
        board.player1.setPlayerName(playerOne);
        board.player2.setPlayerName(playerTwo);

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
        board.boardGraphics();
        //thread = new Thread(this);
        //thread.start();
    }

    public void run(){
        running = true;
        while(running) {
            board.boardGraphics();
        }
    }

    public void onWindowClosing(){
//        try{
//            running = false;
//            thread.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        System.exit(0);
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        board.boardGraphics();
    }
}
