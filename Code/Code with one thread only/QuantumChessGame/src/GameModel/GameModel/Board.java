package GameModel;


import EntityModel.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;



public class Board extends Canvas implements ActionListener {

    public enum State {superpos, tunneling, entangled, none};
    State status;
    private Color white = new Color(255,250,204); //very light brown color
    private Color black = new Color (153,76,0); // dark brown color

    private int width;  //width of the  canvas
    private int height; //height of the canvas
    private int unitX; //the width of a single unit square
    private int unitY; //the height of a single unit square

    private static int cordXOfMouseClick = -1; //the x coordinate [0-7]
    private static int cordYOfMouseClick = -1;  //the y coordinate [0-7]
    private static boolean mouseHasClicked = false; //if the area where there was a click is selected or de-selected

    private volatile MouseListenerBoard mouseListenerBoard; //move to the quantum chess part , to much concentrated on the board job

    private BufferStrategy bufferStrategy; // the buffer strategy to draw activly the frames

    private volatile Piece[][] piecesOnBoard;

    //these keep the coredinates of the piece selected and if it is selected
    private int pieceSelx;
    private int pieceSely;
    private boolean pieceSel;

    //these keep the cordinates of the entangled piece selected and it is selected
    private int entangledPieceSelx;
    private int entangledPieceSely;
    private boolean entangledPieceSel;

    public Board (int width, int height){
        this.width = width;
        this.height = height;
        unitX = width / 8;
        unitY = height / 8;
        print("Board has been created with unitx: " + unitX + " unity: "+ unitY);
        mouseListenerBoard = new MouseListenerBoard(unitX,unitY);
        piecesOnBoard = new Piece[8][8];

        //this is temporary until all the pieces are made so we can initialize them properly
        for (int i = 0; i < piecesOnBoard.length;i++){
            for (int j = 0; j < piecesOnBoard[i].length; j++){
                piecesOnBoard[i][j] = null;
            }
        }
        createBoardPieces();

        status = State.none;
    }

    public void create(){  //create the  initial canvas
        setSize(width,height);
        setBackground(white);
        setIgnoreRepaint(true);
//        addMouseListener(mouseListenerBoard);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (((cordXOfMouseClick == (e.getX() / unitX)) && (cordYOfMouseClick == (e.getY() / unitY)))) {
                    mouseHasClicked = !mouseHasClicked;
                }
                else {
                    mouseHasClicked = true;
                    print("Cordx of click: " + e.getX() + "Cordy of click: " + e.getY());
                    cordXOfMouseClick = e.getX() / unitX;
                    cordYOfMouseClick = e.getY() / unitY;
                    print("Cordx.sys: " + cordXOfMouseClick + " Cordy.sys: " + cordYOfMouseClick);
                }
                if (pieceSel) {
                    movePiece(cordXOfMouseClick, cordYOfMouseClick);
//                    pieceSel = false;
                }else{
                    selectAPiece(cordXOfMouseClick, cordYOfMouseClick);
                }
                status = State.none;
                boardGraphics();

            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }

    public BufferStrategy setBufferStrategy(){ //create the buffer startegy
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        return bufferStrategy;      //the  return is just a precautions measure if we actually need it
    }

    //Creates the colored square with the chosen color and loc
    private void renderSquareGraphic(Graphics g, int locX, int locY, Color color){ //draws the chess board squares
        g.setColor(color);
        g.fillRect((locX * unitX),(locY * unitY), unitX, unitY);
    }

    private void renderSelection(Graphics g, int locX, int locY, Color color){
        g.setColor(color);
        g.drawRect((locX * unitX),(locY * unitY), unitX, unitY);
    }

    //draws the board with everything in it
    public void boardGraphics(){//does the rendering of the graphics

        do{
            do{
                Graphics g = null;
                try{
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0,0,width,height);
                    for (int y = 0 ; y < 8; y++){
                        for (int x = 0; x < 8; x++){
                            if ((y == cordYOfMouseClick && x == cordXOfMouseClick) && mouseHasClicked){
                                renderSquareGraphic(g,x,y,Color.YELLOW);
                            }
                            else if (y  % 2 == 0) {
                                    if (x % 2 == 0) {
                                        renderSquareGraphic(g, x, y, black);
                                    }
                            }
                            else if (y % 2 == 1){
                                if (x % 2 == 1) {
                                    renderSquareGraphic(g, x, y, black);
                                }
                            }
                        }
                    }
                    if (pieceSel &&  !piecesOnBoard[pieceSelx][pieceSely].isDummy() && !piecesOnBoard[pieceSelx][pieceSely].isSupperPos()){
                        Point tmp;
                        int passPiece = 0;
                        int opponentsEncountered = 0;
                        while(piecesOnBoard[pieceSelx][pieceSely].hasNext()) {
                            tmp = piecesOnBoard[pieceSelx][pieceSely].getNext();
                            if (tmp != null) {
                                if (piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()] != null) {
                                    if ((piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()])) && (opponentsEncountered == 0) && (status != State.tunneling)) {
                                        opponentsEncountered++;
                                        renderSquareGraphic(g, (int) tmp.getX(), (int) tmp.getY(), Color.RED);
//                                    } else if ((piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()])) && (opponentsEncountered <= 1) && (status == State.tunneling) && passPiece) {
//                                        opponentsEncountered++;
//                                        renderSquareGraphic(g, (int) tmp.getX(), (int) tmp.getY(), Color.RED);
                                    }
                                    if ((status == State.tunneling) && (piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()])) ){
                                        piecesOnBoard[pieceSelx][pieceSely].pushIteratorToNull(State.none);
                                    }
                                    else if (passPiece == 0) {
                                        passPiece++;
                                        piecesOnBoard[pieceSelx][pieceSely].pushIteratorToNull(status);
//                                    } else if (!piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()])) {
//                                        passPiece = true;
//                                        piecesOnBoard[pieceSelx][pieceSely].pushIteratorToNull(State.none);
//                                    } else {
//                                        passPiece = true;
                                    }else {
                                        passPiece++;
                                    }

                                } else if(passPiece <= 1) {
                                    renderSquareGraphic(g, (int) tmp.getX(), (int) tmp.getY(), Color.GREEN);

                                }
                            }
                            else{
                                    passPiece = 0;
                                    opponentsEncountered = 0;
                                }

                            }

                    }


                    for (int y = 0 ; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            if (piecesOnBoard[x][y] != null) {
                                if (piecesOnBoard[x][y].isDummy()) {
                                    int tmp1, tmp2;
                                    tmp1 = piecesOnBoard[x][y].getPosx();
                                    tmp2 = piecesOnBoard[x][y].getPosY();
                                    g.drawImage(piecesOnBoard[tmp1][tmp2].getSuperPosImage(), x * unitX, y * unitY, unitX, unitY, null);
                                } else {
                                    if(piecesOnBoard[x][y].isSupperPos()){
                                        g.drawImage(piecesOnBoard[x][y].getSuperPosImage(), x * unitX, y * unitY, unitX, unitY, null);
                                    }else {
                                        g.drawImage(piecesOnBoard[x][y].getFullImage(), x * unitX, y * unitY, unitX, unitY, null);
                                    }
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



    public static void updateClick(int cordx, int cordy , boolean mouseClick){//is called by the mouse listener so that these values are updated upon the click
        cordXOfMouseClick = cordx;
        cordYOfMouseClick = cordy;
        mouseHasClicked = mouseClick;
    }

    public void selectAPiece(int x,int y){
        if (piecesOnBoard[x][y] != null){
            pieceSel = true;
            pieceSely = y;
            pieceSelx = x;
            mouseHasClicked = true;
            System.out.println("Piece selected");
        }
        else {
            pieceSel = false;
            System.out.println("Piece not selected");
        }
        boardGraphics();
    }

    public boolean movePiece(int x, int y) {
        print("--Moving Piece--");
        if ((piecesOnBoard[x][y] == null) && !piecesOnBoard[pieceSelx][pieceSely].isSupperPos()  && (piecesOnBoard[pieceSelx][pieceSely]).validPos(x,y,piecesOnBoard,status) ) {
            System.out.println("Piece sel: " + pieceSel);
            System.out.println("Piece sel moving: " + ((pieceSelx != x) || (pieceSely != y)) + "\n" + "x: " + pieceSelx + "  y: " + pieceSely);
            if (pieceSel && ((pieceSelx != x) || (pieceSely != y))) {
                if (status != State.superpos) {
                    piecesOnBoard[x][y] = piecesOnBoard[pieceSelx][pieceSely];
                    piecesOnBoard[x][y].updatePiecePos(x, y);
                    piecesOnBoard[pieceSelx][pieceSely] = null;
                   // piecesOnBoard[pieceSelx][pieceSely] = null;
                }
                else{ //if superpos is selected this will be done
                    piecesOnBoard[x][y] = new Piece(x,y,pieceSelx,pieceSely) {
                        @Override
                        public boolean validPos(int x, int y, Piece[][] piecesOnBoard,State state) {
                            return super.validPos(x, y, piecesOnBoard,State.none);
                        }
                    };
                    piecesOnBoard[pieceSelx][pieceSely].setSuperPosX(x);
                    piecesOnBoard[pieceSelx][pieceSely].setSuperPosY(y);
                    piecesOnBoard[pieceSelx][pieceSely].setToSuperPos();
                }
                mouseHasClicked = false;
                pieceSel = false;
                status= State.none;
                boardGraphics();
                return true;
            }

        }else if( piecesOnBoard[pieceSelx][pieceSely].canTake(x,y,piecesOnBoard,status) ) {
            System.out.println("MovePiece can take?");
            piecesOnBoard[x][y] = piecesOnBoard[pieceSelx][pieceSely];
            piecesOnBoard[x][y].updatePiecePos(x, y);
            piecesOnBoard[pieceSelx][pieceSely] = null;
            mouseHasClicked = false;
            pieceSel = false;
            status= State.none;
            boardGraphics();
            return true;
        }else {
            print("the movement is not allowed on that place");
            if (x != pieceSelx || y != pieceSely) {
                selectAPiece(x, y);
            }else{
                pieceSel = false;
            }
        }
        boardGraphics();
        return false;
    }

    private void createBoardPieces(){
        piecesOnBoard[0][7] = new Rook(0,7,"piece_pictures/white_rook_full.png","piece_pictures/white_rook_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[1][7] = new Knight(1,7,"piece_pictures/white_knight_full.png","piece_pictures/white_knight_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[2][7] = new Bishop(2,7,"piece_pictures/white_bishop_full.png","piece_pictures/white_bishop_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[3][7] = new King(3,7,"piece_pictures/white_king_full.png","piece_pictures/white_king_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[4][7] = new Queen(4,7,"piece_pictures/white_queen_full.png","piece_pictures/white_queen_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[5][7] = new Bishop(5,7,"piece_pictures/white_bishop_full.png","piece_pictures/white_bishop_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[6][7] = new Knight(6,7,"piece_pictures/white_knight_full.png","piece_pictures/white_knight_superpos.png",unitX,unitY,Color.WHITE,1);
        piecesOnBoard[7][7] = new Rook(7,7,"piece_pictures/white_rook_full.png","piece_pictures/white_rook_superpos.png",unitX,unitY,Color.WHITE,1);

        for (int pawnP1 = 0; pawnP1 < 8; pawnP1++){//this creates the row of pawns for the first player
            piecesOnBoard[pawnP1][6] = new Pawn(pawnP1,6,"piece_pictures/white_pawn_full.png","piece_pictures/white_pawn_superpos.png",unitX,unitY,Color.WHITE,1);
        }

        piecesOnBoard[0][0] = new Rook(0,0,"piece_pictures/black_rook_full.png","piece_pictures/black_rook_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[1][0] = new Knight(1,0,"piece_pictures/black_knight_full.png","piece_pictures/black_knight_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[2][0] = new Bishop(2,0,"piece_pictures/black_bishop_full.png","piece_pictures/black_bishop_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[3][0] = new King(3,0,"piece_pictures/black_king_full.png","piece_pictures/black_king_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[4][0] = new Queen(4,0,"piece_pictures/black_queen_full.png","piece_pictures/black_queen_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[5][0] = new Bishop(5,0,"piece_pictures/black_bishop_full.png","piece_pictures/black_bishop_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[6][0] = new Knight(6,0,"piece_pictures/black_knight_full.png","piece_pictures/black_knight_superpos.png",unitX,unitY,Color.BLACK,2);
        piecesOnBoard[7][0] = new Rook(7,0,"piece_pictures/black_rook_full.png","piece_pictures/black_rook_superpos.png",unitX,unitY,Color.BLACK,2);

        for (int pawnP1 = 0; pawnP1 < 8; pawnP1++){//this creates the row of pawns for the first player
            piecesOnBoard[pawnP1][1] = new Pawn(pawnP1,1,"piece_pictures/black_pawn_full.png","piece_pictures/black_pawn_superpos.png",unitX,unitY,Color.BLACK,2);
        }
    }
    private void print(String str){
        System.out.println(str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (("SupperPosition".equals(e.getActionCommand()))&& pieceSel){
            status = State.superpos;
            print("SupperPositionActivated");
        }
        else if (("Observe".equals(e.getActionCommand()))&& pieceSel) {
            int tmp = collapse();
            print("The Collapse Function Resulted in " + tmp);
            if(tmp == 1){
                if (piecesOnBoard[pieceSelx][pieceSely].isDummy()){
                    tmp = piecesOnBoard[pieceSelx][pieceSely].getPosx();
                    int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getPosY();
                    piecesOnBoard[pieceSelx][pieceSely] = null;
                    pieceSelx = tmp;
                    pieceSely = tmp2;
                    piecesOnBoard[pieceSelx][pieceSely].setToNormal();
                    piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);

                }
                else{
                    tmp = piecesOnBoard[pieceSelx][pieceSely].getSuperPosX();
                    int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getSuperPosY();
                    piecesOnBoard[tmp][tmp2] = null;
                    piecesOnBoard[pieceSelx][pieceSely].setToNormal();
                    piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);
                }

            }
            else{
                if (piecesOnBoard[pieceSelx][pieceSely].isDummy()){
                    tmp = piecesOnBoard[pieceSelx][pieceSely].getPosx();
                    int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getPosY();
                    piecesOnBoard[pieceSelx][pieceSely] = piecesOnBoard[tmp][tmp2];
                    piecesOnBoard[pieceSelx][pieceSely].setToNormal();
                    piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);
                    piecesOnBoard[tmp][tmp2] = null;

                }
                else{
                    tmp = piecesOnBoard[pieceSelx][pieceSely].getSuperPosX();
                    int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getSuperPosY();
                    piecesOnBoard[tmp][tmp2] =  piecesOnBoard[pieceSelx][pieceSely];
                    piecesOnBoard[pieceSelx][pieceSely] = null;
                    pieceSelx = tmp;
                    pieceSely = tmp2;
                    piecesOnBoard[pieceSelx][pieceSely].setToNormal();
                    piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);
                }
            }
        }
        else if("Tunneling".equals(e.getActionCommand())){
            status = State.tunneling;
        }
        boardGraphics();
    }

    private int collapse(){
        return (int)(Math.random() + 0.49);
    }
}
