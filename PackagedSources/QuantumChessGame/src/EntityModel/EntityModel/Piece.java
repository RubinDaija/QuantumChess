package EntityModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Piece {
    protected boolean superPosBol;
    protected Color color;
    protected boolean taken;
    protected int posX;
    protected int posY;
    protected int superPosX;
    protected int superPosY;
    protected boolean superPosAllowed; //if the user observes it there will be no more super pos
    protected BufferedImage fullImage;
    protected BufferedImage superPosImage;
    protected int player;
    protected ArrayList<Point> positions;
    private int iterator;
    protected Iterator trueIterator;

    public Piece(int initialX, int initialY, String fullPicName, String halfPicName,int sizex, int sizey, Color color, int player){
        superPosBol = false;
        this.color = color;
        taken = false;
        posX = initialX;
        posY = initialY;
        superPosAllowed = true;

        fullImage = new BufferedImage(sizex,sizey,BufferedImage.TYPE_INT_ARGB_PRE);
        superPosImage = new BufferedImage(sizex,sizey,BufferedImage.TYPE_INT_ARGB_PRE);
        fullImage = loadPicture(fullPicName);
        superPosImage = loadPicture(halfPicName);

        this.player = player;
        positions = new ArrayList<Point>();
        iterator = 0;
        trueIterator = positions.iterator();
    }

    public Piece(Piece copy){
        superPosAllowed = true;
        taken = false;
        superPosBol = false;
        this.color = copy.color;
        posX = copy.posX;
        posY = copy.posY;
        fullImage = copy.getFullImage();
        superPosImage = copy.getSuperPosImage();
        player = copy.player;
        positions =new ArrayList<Point>();
        iterator = 0;
    }

    public boolean validPos(int x, int y, Piece[][] piecesOnBoard){
        System.out.println("in Valid pos");
        trueIterator = positions.iterator();
        if (positions.size() > 0) {
            Point tmp;
            boolean furtherBlocked = false;
             do{
                tmp = (Point) trueIterator.next();

                if (tmp == null) {
                    furtherBlocked = false;
                } else if (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null ) {
                    if(piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())].isOpponent(this))//if not same color piece could be taken
                        furtherBlocked = false;
                    else{
                        furtherBlocked = true;
                        System.out.println("Same Color" +tmp.getX() + tmp.getY());
                    }
                }
                if ((tmp != null)&&(x == tmp.getX()) && (y == tmp.getY()) && !furtherBlocked) {
                    trueIterator = positions.iterator();
                    return true;
                }
            }while (trueIterator.hasNext());
            trueIterator = positions.iterator();
        }
        return false;
    }


//    public boolean validPos(int x, int y){
//        Point tmp;
//        while (hasNext()){
//            tmp = getNext();
//            if((tmp != null) && (x == tmp.getX()) && (y == tmp.getY() )){
//                return true;
//            }
//        }
//        return false;
//    }

   // public abstract void move(int x, int y);
    protected void calculatePossibleMoves(){
        positions = new ArrayList<Point>();
    }


    private BufferedImage loadPicture(String fileName){
        try{
            return ImageIO.read(new File(fileName));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void updatePiecePos(int x, int y) {
        posY = y;
        posX = x;
        calculatePossibleMoves();

    }

    public void updatePieceSuperPos(int x, int y){
        superPosY = y;
        superPosX = x;
    }

    public boolean isOpponent(Piece piece){
        return piece != null && !piece.color.equals(this.color);
    }
    public int getPosx(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int getSuperPosX(){
        return  superPosX;
    }

    public int getSuperPosY(){
        return superPosY;
    }

    public BufferedImage getFullImage() {
        return fullImage;
    }

    public BufferedImage getSuperPosImage() {
        return fullImage;
    }

    public boolean hasNext(){
        while((iterator < positions.size()) && (positions.get(iterator) == null) ){
            iterator++;
        }
        if (iterator < positions.size()){
            return true;
        }else{
            iterator = 0;
            return false;
        }
    }

    public Point getNext() {
        return positions.get(iterator++);
    }
    public void pushIteratorToNull(){
        while ((iterator < positions.size()) && (positions.get(iterator) != null)){
            iterator++;
        }
    }
    private Point getNextExcNull(){
        iterator = iterator + 1;
        return positions.get(iterator - 1);
    }

    public int getPlayer(){
        return player;
    }


}
