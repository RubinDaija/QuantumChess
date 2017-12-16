import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Piece {
    private boolean superPosBol;
    private Color color;
    private boolean taken;
    private int posX;
    private int posY;
    private int superPosX;
    private int superPosY;
    private boolean superPosAllowed; //if the user observes it there will be no more super pos
    private BufferedImage fullImage;
    private BufferedImage superPosImage;
    private int player;
    private ArrayList<Point> positions;

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
        positions = copy.positions;

    }

    public abstract boolean validPos(int x, int y);
    public abstract void move(int x, int y);


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

    public BufferedImage getFullImage() {
        return fullImage;
    }

    public BufferedImage getSuperPosImage() {
        return fullImage;
    }



}
