package EntityModel;

import java.awt.*;

public class Bishop extends Piece{
        public Bishop(int initialX, int initialY, String fullPicName, String halfPicName, int sizex, int sizey, Color color, int player){
            super(initialX,initialY,fullPicName,halfPicName,sizex,sizey,color,player);
            calculatePossibleMoves();
        }

        @Override
        public void move(int x, int y) {

        }

        protected void calculatePossibleMoves(){
            super.calculatePossibleMoves();

            int tmp;

            for (int i = 0; i <= 7; i++){
                tmp = posY - i;
                if ( tmp >= 0) {
                    positions.add(new Point(posX + i, tmp));
                } else{
                    break;
                }
            }
            positions.add(null);

            for (int i = 0; i <= 7; i++){
                tmp = posX + i;
                if (tmp < 8) {
                    positions.add(new Point(tmp, posY + i));
                } else{
                    break;
                }
            }
            positions.add(null);

            for (int i = 0; i <= 7; i++){
                tmp = posY + i;
                if (tmp < 8) {
                    positions.add(new Point(posX - i , tmp));
                } else{
                    break;
                }
            }
            positions.add(null);

            for (int i = 0; i <= 7; i++){
                tmp = posX - i;
                if (tmp >= 0) {
                    positions.add(new Point(tmp, posY - i));
                } else{
                    break;
                }
            }




        }
}
