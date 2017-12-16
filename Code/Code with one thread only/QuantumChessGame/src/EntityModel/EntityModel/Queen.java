package EntityModel;

import java.awt.*;

public class Queen extends Piece {
    public Queen(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
        super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
        calculatePossibleMoves();
    }

    public Queen(Piece copy, int player){
        super(copy,player);
    }

    //@Override
    public void move(int x, int y) {

    }

    protected void calculatePossibleMoves(){
        super.calculatePossibleMoves();

        int tmp;
        int tmp2;

        for (int i = 1; i <= 7; i++){
            tmp = posY - i;
            if ( tmp >= 0) {
                positions.add(new Point(posX, tmp));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posY - i;
            tmp2 = posX + i;
            if ( (tmp >= 0) && (tmp2 <= 7)) {
                positions.add(new Point(tmp2, tmp));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posX + i;
            if (tmp <= 7) {
                positions.add(new Point(tmp, posY));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posX + i;
            tmp2 = posY + i;
            if ((tmp <= 7) && (tmp2 <= 7)) {
                positions.add(new Point(tmp, tmp2));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posY + i;
            if (tmp <= 7) {
                positions.add(new Point(posX , tmp));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posY + i;
            tmp2 = posX - i;
            if ((tmp <= 7) && (tmp2 >= 0)) {
                positions.add(new Point( tmp2, tmp));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posX - i;
            if (tmp >= 0) {
                positions.add(new Point(tmp, posY));
            } else{
                break;
            }
        }
        positions.add(null);

        for (int i = 1; i <= 7; i++){
            tmp = posX - i;
            tmp2 = posY - i;
            if ((tmp >= 0) && (tmp2 >= 0)) {
                positions.add(new Point(tmp,tmp2 ));
            } else{
                break;
            }
        }




    }
}
