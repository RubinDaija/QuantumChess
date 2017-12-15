package EntityModel;

import java.awt.*;

public class Knight extends Piece {
    public Knight(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
        super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
        calculatePossibleMoves();
    }

    //@Override
    public void move(int x, int y) {

    }

    @Override
    public boolean validPos(int x, int y, Piece[][] piecesOnBoard) {
        Point tmp;
        while (hasNext()){
            tmp = getNext();
            if((tmp != null) && (x == tmp.getX()) && (y == tmp.getY() )){
                return true;
            }
        }
        return false;
    }

    protected void calculatePossibleMoves(){
        super.calculatePossibleMoves();

        if ((posX != 7) && (posY > 1) ) {
            positions.add(new Point(posX + 1, posY - 2));
            positions.add(null);
        }
        if ((posX < 6) && (posY != 0) ) {
            positions.add(new Point(posX + 2, posY - 1));
            positions.add(null);
        }
        if ((posX < 6) && (posY != 7) ) {
            positions.add(new Point(posX + 2, posY + 1));
            positions.add(null);
        }
        if ((posX != 7) && (posY < 6) ) {
            positions.add(new Point(posX + 1, posY + 2));
            positions.add(null);
        }
        if ((posX != 0) && (posY < 6) ) {
            positions.add(new Point(posX - 1, posY + 2));
            positions.add(null);
        }
        if ((posX > 1) && (posY != 7) ) {
            positions.add(new Point(posX - 2, posY + 1));
            positions.add(null);
        }
        if ((posX > 1) && (posY != 0) ) {
            positions.add(new Point(posX - 2, posY - 1));
            positions.add(null);
        }
        if ((posX != 0) && (posY > 1) ) {
            positions.add(new Point(posX - 1, posY - 2));
        }
    }
}
