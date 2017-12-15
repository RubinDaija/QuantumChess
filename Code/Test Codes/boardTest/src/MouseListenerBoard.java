import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerBoard implements MouseListener {
    private boolean mouseClicked;
    private int cordX;
    private int cordY;
    private int unitx;
    private int unity;
    private boolean mouseReleased;

    public MouseListenerBoard(int unitx, int unity){
        mouseClicked = false;
        mouseReleased = false;
        cordY = -69;
        cordX = -69;
        this.unitx = unitx;
        this.unity = unity;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((cordX == (e.getX() / unitx) && cordY == (e.getY() / unity))){
            mouseClicked = false;
            cordY = -69;
            cordX = -69;
        }
        else {
            mouseClicked = true;
            cordX = e.getX() / unitx;
            cordY = e.getY() / unity;
        }
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

    public void cleanClick(){
        mouseClicked = false;
    }

        @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleased = true;
    }

    public boolean getMouseReleasedStatus(){
        return mouseReleased;
    }

    public void mouseReleasedStatusToFalse(){
        mouseReleased = false;
    }
    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }


}
