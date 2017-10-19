import java.awt.*;

public class Pawn extends Piece {

    public Pawn(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
        super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
        calculatePossibleMoves();
    }


    @Override
    public void move(int x, int y) {

    }


    protected void calculatePossibleMoves() {
        super.calculatePossibleMoves();
        if (player == 1) {
            positions.add(new Point(posX, posY -1));
        } else {
            positions.add(new Point(posX, posY + 1));

        }
    }
}
