package EntityModel;

import java.awt.*;

public class King extends Piece {

    public King(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
        super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
        calculatePossibleMoves();
    }

    //@Override
    public void move(int x, int y) {

    }

    protected void calculatePossibleMoves(){
        super.calculatePossibleMoves();

        if(posY != 0 ) {
            positions.add(new Point(posX, posY - 1));
            positions.add(null);
        }
        if ((posX != 7) && (posY != 0)){
            positions.add(new Point(posX + 1, posY - 1));
            positions.add(null);
        }
        if (posX != 7) {
            positions.add(new Point(posX + 1, posY));
            positions.add(null);
        }
        if ((posX != 7) && (posY != 7)){
            positions.add(new Point(posX + 1, posY + 1));
            positions.add(null);
        }
        if (posY != 7) {
            positions.add(new Point(posX, posY + 1));
            positions.add(null);
        }
        if ((posX != 0) && (posY != 7)){
            positions.add(new Point(posX - 1, posY + 1));
            positions.add(null);
        }
        if (posX != 0) {
            positions.add(new Point(posX - 1, posY));
            positions.add(null);
        }
        if ((posX != 0) && (posY != 0)){
            positions.add(new Point(posX - 1, posY - 1));
        }
    }
}
