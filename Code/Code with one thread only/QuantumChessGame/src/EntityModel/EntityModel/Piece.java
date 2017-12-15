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
    //protected boolean taken;
    private boolean dummy;
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
       // taken = false;
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

        dummy = false;
    }

//    public Piece(Piece copy){
//        superPosAllowed = true;
//        //taken = false;
//        superPosBol = false;
//        this.color = copy.color;
//        posX = copy.posX;
//        posY = copy.posY;
//        fullImage = copy.getFullImage();
//        superPosImage = copy.getSuperPosImage();
//        player = copy.player;
//        positions =new ArrayList<Point>();
//        iterator = 0;
//    }

    public Piece(int superPosX, int superPosY, int parentx, int parentY){ //constructor for dummy piece
        this.superPosX = superPosX;
        this.superPosY = superPosY;
        posX = parentx;
        posY = parentY;
        dummy = true;
        superPosBol = true;
    }
    public boolean validPos(int x, int y, Piece[][] piecesOnBoard){
        trueIterator = positions.iterator();
        if (positions.size() > 0) {

            Point tmp;
            boolean furtherBlocked = false;
             do{
                tmp = (Point) trueIterator.next();

                if (tmp == null) {
                    furtherBlocked = false;
                } else if (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null) {
                    furtherBlocked = true;
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
    public boolean isDummy(){
        return dummy;
    }
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

    public void setSuperPosX(int x){
        superPosX = x;
    }

    public void setSuperPosY(int y){
        superPosY = y;
    }

    public boolean isSupperPos(){
        return superPosBol;
    }

    public boolean isSuperPosAllowed(){
        return superPosAllowed;
    }

    public void setToSuperPos(){
        superPosBol = true;
    }

    public void setToNormal(){
        superPosBol = false;
        superPosAllowed = false;
    }
    public BufferedImage getFullImage() {
        return fullImage;
    }

    public BufferedImage getSuperPosImage() {
        return superPosImage;
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


}
