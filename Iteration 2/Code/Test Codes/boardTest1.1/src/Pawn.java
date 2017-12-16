import java.awt.*;

public class Pawn extends Piece {

    public Pawn(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
        super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
    }

    @Override
    public boolean validPos(int x, int y) {
        return false;
    }

    @Override
    public void move(int x, int y) {

    }
}
