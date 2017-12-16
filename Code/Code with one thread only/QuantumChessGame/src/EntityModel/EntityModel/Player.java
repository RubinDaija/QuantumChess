package EntityModel;

import javax.swing.*;
import java.util.ArrayList;

public class Player {
    ArrayList<Piece> piecesTaken;
    private String playerName;
    private int playerNo;

    public Player(String pName,int pNo){
        playerName = pName;
        playerNo = pNo;


        setPiecesTaken();
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public void getPiecesTaken() {
        for (Piece takenPiece: piecesTaken){
            // to see which piece is taken.
            System.out.println(takenPiece.getClass());
        }
    }
    //Kaan this would return taken pictures to right panel at the bottom of the buttons.
    public void getPiecesPicturesTaken() {

        /*
        JFrame panel = new JFrame();
        for (Piece takenPiece: piecesTaken){
            // to see which piece is taken.
            JLabel pic = new JLabel((ImageIcon) takenPiece.getFullImage());
            panel.add(pic);
        }

        panel.pack();
        panel.show();
        panel.setVisible(true);
        */
    }

    public void setPiecesTaken() {
        this.piecesTaken = new ArrayList<Piece>();
    }
    public void insertPieceTaken(Piece piece){
        piecesTaken.add(piece);
    }



}
