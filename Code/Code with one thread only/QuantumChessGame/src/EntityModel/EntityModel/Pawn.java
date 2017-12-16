package EntityModel;

import java.awt.*;

public class Pawn extends Piece {

    public Pawn(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
        super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
        calculatePossibleMoves();

    }

    public Pawn(Piece copy, int player){
        super(copy,player);
    }


    //@Override
    public void move(int x, int y) {

    }

//    @Override
//    public boolean validPos(int x, int y, Piece[][] piecesOnBoard){
//        trueIterator = positions.iterator();
//        if (positions.size() > 0) {
//
//            Point tmp;
//            boolean furtherBlocked = false;
//            do{
//                tmp = (Point) trueIterator.next();
//
//                if (tmp == null) {
//                    furtherBlocked = false;
//                } else if (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null) {
//                    furtherBlocked = true;
//                }
//                if ((x == tmp.getX()) && (y == tmp.getY()) && !furtherBlocked) {
//                    trueIterator = positions.iterator();
//                    return true;
//                }
//            }while (trueIterator.hasNext());
//            trueIterator = positions.iterator();
//        }
//        return false;
//    }


    protected void calculatePossibleMoves() {
        super.calculatePossibleMoves();
        if ((player == 1) && (posY != 0)) {
            positions.add(new Point(posX, posY -1));
            if (!this.moved){
                positions.add(new Point(posX,posY -2 ));
            }
        } else if (posY != 7) {
            positions.add(new Point(posX, posY + 1));
            if (!this.moved){
                positions.add(new Point(posX, posY + 2));
            }


        }
        moved = true;
    }
}
