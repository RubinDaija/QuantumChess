package GameModel;

import EntityModel.Piece;

import java.awt.*;

public class PieceBoardPrototype {
     Color color;
    String fullPicName;
    String type;

    public PieceBoardPrototype(String type,Color color){
        this.color = color;
        this.type = type;
        this.fullPicName = color + "_"+type+"_full.png";
    }

    @Override
    protected PieceBoardPrototype clone() throws CloneNotSupportedException {
        return (PieceBoardPrototype)super.clone();
    }
}
