package EntityModel;

import GameModel.Board;

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
    private int iterator; //inhouse created iterator
    protected Iterator trueIterator; //using array lists iterator
    protected boolean moved;

    //the constructor
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
        moved = false;
    }

    public Piece(Piece copy,int player){
        superPosAllowed = true;
        //taken = false;
        superPosBol = false;
        this.color = copy.color;
        posX = copy.posX;
        posY = copy.posY;
        fullImage = copy.getFullImage();
        superPosImage = copy.getSuperPosImage();
        this.player = player;
        positions =new ArrayList<Point>();
        iterator = 0;
    }


    //i used to create a dummy piece
    public Piece(int superPosX, int superPosY, int parentx, int parentY,int player){ //constructor for dummy piece
        this.superPosX = superPosX;
        this.superPosY = superPosY;
        posX = parentx;
        posY = parentY;
        dummy = true;
        superPosBol = true;
        this.player = player;
    }

    //checks if the position selected x ,y is valid for this piece
    public boolean validPos(int x, int y, Piece[][] piecesOnBoard, Board.State state){
        trueIterator = positions.iterator();
        if (positions.size() > 0) {

            Point tmp;
            int piecesPassed = 0;
            boolean furtherBlocked = false;
             do{
                tmp = (Point) trueIterator.next();

                if (tmp == null) {
                    furtherBlocked = false;
                    piecesPassed = 0;
                } else if ((state == Board.State.tunneling) && (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null) && (piecesPassed == 0) && (!piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())].isOpponent(this))) {
                    piecesPassed++;
                } else if (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null) {
                    furtherBlocked = true;
                }
                if ((tmp != null)&&(x == tmp.getX()) && (y == tmp.getY()) && !furtherBlocked) {
                    trueIterator = positions.iterator();//it is not needed i think
                    return true;
                }
            }while (trueIterator.hasNext());
            trueIterator = positions.iterator();//it is not needed i think
        }
        return false;
    }

    /*
    Checks if there is a chance of taking a piece in valid positions. Same as valid pos just added isOpponent so piece can understand the way opponets.
     */
    public  boolean canTake(int destinationX, int destinationY, Piece[][] piecesOnBoard, Board.State state){

        if (state == Board.State.none) {

            if (this.getClass() == Pawn.class) {

                if ((player == 1) && (posY <= 6)){
                    if(((destinationX == posX-1)&&(destinationY == posY - 1))|| ((destinationX == posX+1)&&(destinationY == posY - 1))) {
                        if (this.isOpponent(piecesOnBoard[destinationX][destinationY])){
                           return true;
                        }
                    }
                    else {
                        return false;
                    }
                }
                else if((player == 2) && (posY >= 1)){
                    if(((destinationX == posX-1)&&(destinationY == posY + 1))|| ((destinationX == posX+1)&&(destinationY == posY + 1))) {
                        if (this.isOpponent(piecesOnBoard[destinationX][destinationY])){
                            return true;
                        }
                    }
                    else {
                        return false;
                    }
                }
                else{
                    return false;
                }
            }

            trueIterator = positions.iterator();
            int counter = 0;
            if (positions.size() > 0) {

                Point tmp;
                boolean furtherBlocked = false;

                do {
                    tmp = (Point) trueIterator.next();

                    if (tmp == null) {
                        furtherBlocked = false;
                        counter = 0;
                    } else {
                        if (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null && !piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())].isOpponent(this)) {
                            furtherBlocked = true;
                        } else if (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null && piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())].isOpponent(this) && (!furtherBlocked)) {
                            counter++;
                        }
                    }
                    if (tmp != null && (piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())] != null && (destinationX == tmp.getX()) && (destinationY == tmp.getY()) && !furtherBlocked && piecesOnBoard[((int) tmp.getX())][((int) tmp.getY())].isOpponent(this)) && (counter == 1)) {
                        return true;
                    }
                } while (trueIterator.hasNext());
                //trueIterator = positions.iterator();
            }
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

    //is in seperate pieces defined specially
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
//        while((iterator < positions.size()) && (positions.get(iterator) == null) ){
//            iterator++;
//        }
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


    public void pushIteratorToNull(Board.State state){
        if (state == Board.State.tunneling){
        }
        else {
            while ((iterator < positions.size()) && (positions.get(iterator) != null)) {
                iterator++;
            }
        }
    }

    public boolean isOpponent( Piece piece){
        return (piece != null) && (this.color != piece.color);
    }

    public int getPlayer(){
        return player;
    }

    public Color getColor() {
        return color;
    }
}
