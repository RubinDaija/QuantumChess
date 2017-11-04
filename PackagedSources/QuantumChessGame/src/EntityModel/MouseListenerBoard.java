package EntityModel;

import GameModel.Board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerBoard implements MouseListener {
    private volatile boolean mouseClicked;
    private volatile int cordX;
    private volatile int cordY;

    private int unitx;
    private int unity;


    public MouseListenerBoard(int unitx, int unity){
        mouseClicked = false;
        cordY = -69;
        cordX = -69;
        this.unitx = unitx;
        this.unity = unity;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((cordX == (e.getX() / unitx) && cordY == (e.getY() / unity))){
            mouseClicked = !mouseClicked;
//            cordY = -69;
//            cordX = -69;
        }
        else {
            mouseClicked = true;
            cordX = e.getX() / unitx;
            cordY = e.getY() / unity;
        }

        Board.updateClick(cordX,cordY,mouseClicked);
    }

    public int getCordX(){
        return cordX;
    }

    public int getCordY(){
        return cordY;
    }

    public boolean getMouseClick(){
        return mouseClicked;
    }



    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }


}
