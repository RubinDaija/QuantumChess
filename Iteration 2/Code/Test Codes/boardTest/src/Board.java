import java.awt.*;
import java.awt.image.BufferStrategy;

public class Board extends Canvas implements Runnable{

    private int width;
    private int height;
    private int unitX;
    private int unitY;
    private MouseListenerBoard mouseListenerBoard;

    private volatile  boolean running;
    private Thread thread;

    private BufferStrategy bufferStrategy;

    public Board (int width, int height){
        this.width = width;
        this.height = height;
        unitX = width / 8;
        unitY = width / 8;
        mouseListenerBoard = new MouseListenerBoard(unitX,unitY);
    }

    public void create(){
        setSize(width,height);
        setBackground(Color.WHITE);
        setIgnoreRepaint(true);
        addMouseListener(mouseListenerBoard);
    }

    public void setBufferStrategy(){
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        running = true;
        while(running) {
            boardGraphics();
            mouseListenerBoard.mouseReleasedStatusToFalse();
        }
    }

    public void onClose(){
        try{
            running = false;
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void renderSquareGraphic(Graphics g, int locX, int locY, Color color){
        g.setColor(color);
        g.fillRect((locX * unitX),(locY * unitY), unitX, unitY);
    }

    private void renderSelection(Graphics g, int locX, int locY, Color color){
        g.setColor(color);
        g.drawRect((locX * unitX),(locY * unitY), unitX, unitY);
    }

    public void boardGraphics(){
        do{
            do{
                Graphics g = null;
                try{
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0,0,width,height);
                    for (int y = 0 ; y < 8; y++){
                        for (int x = 0; x < 8; x++){
                            if (y == mouseListenerBoard.getCordY() && x == mouseListenerBoard.getCordX()){
                                if (mouseListenerBoard.getMouseClick()){
                                  renderSquareGraphic(g,x,y,Color.YELLOW);
                                }
                            }
                            else if (y  % 2 == 0) {
                                    if (x % 2 == 0) {
                                        renderSquareGraphic(g, x, y, Color.BLACK);
                                    }
                            }
                            else if (y % 2 == 1){
                                if (x % 2 == 1) {
                                    renderSquareGraphic(g, x, y, Color.BLACK);
                                }
                            }
                        }
                    }

                }finally {
                    if (g != null){
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());
            bufferStrategy.show();
        }while(bufferStrategy.contentsLost());
    }


}
