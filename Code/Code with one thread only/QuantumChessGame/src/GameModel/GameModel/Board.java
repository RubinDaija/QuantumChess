package GameModel;


import EntityModel.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;



public class Board extends Canvas implements ActionListener {

    public enum State {superpos, tunneling, entangled, none};
    State status;

    private Color white = new Color(255,250,204); //very light brown color
    private Color black = new Color (153,76,0); // dark brown color
    private Color green = new Color(102, 255, 51,150); //green?
    private Color red = new Color(250, 0, 0,150);
    private Color blue =  new Color(0, 0, 250,150);
    private Color yellow = new Color(255,255,0,150);

    private int width;  //width of the  canvas
    private int height; //height of the canvas
    private int unitX; //the width of a single unit square
    private int unitY; //the height of a single unit square

    private static int cordXOfMouseClick = 3; //the x coordinate [0-7]
    private static int cordYOfMouseClick = 3;  //the y coordinate [0-7]
    private static boolean mouseHasClicked = false; //if the area where there was a click is selected or de-selected

    private volatile MouseListenerBoard mouseListenerBoard; //move to the quantum chess part , to much concentrated on the board job

    private BufferStrategy bufferStrategy; // the buffer strategy to draw activly the frames

    private volatile Piece[][] piecesOnBoard;

    int turn = 1; //represents who's turn it is

    //these keep the coredinates of the piece selected and if it is selected
    private int pieceSelx;
    private int pieceSely;
    private boolean pieceSel;
    private SideButtons buttons;
    public Player player1,player2;

    //these keep the cordinates of the entangled piece selected and it is selected
    private int entangledPieceSelx;
    private int entangledPieceSely;
    private boolean entangledPieceSel;

    public Board (int width, int height){
        pieceSelx = 3;
        pieceSely = 3;
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

        /* prototype pattern can be implemented maybe?*/
        player1 = new Player("AAAA",1);
        player2 = new Player("BBBB",2);
    }

    public void create(){  //create the  initial canvas
        setSize(width,height);
        setBackground(white);
        setIgnoreRepaint(true);
//        addMouseListener(mouseListenerBoard);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttons.disableEverything();
                if (((cordXOfMouseClick == (e.getX() / unitX)) && (cordYOfMouseClick == (e.getY() / unitY)))) {
                    mouseHasClicked = !mouseHasClicked;
                } else {
                    mouseHasClicked = true;
                    print("Cordx of click: " + e.getX() + "Cordy of click: " + e.getY());
                    cordXOfMouseClick = e.getX() / unitX;
                    cordYOfMouseClick = e.getY() / unitY;
                    print("Cordx.sys: " + cordXOfMouseClick + " Cordy.sys: " + cordYOfMouseClick);
                }
                if ((piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick] != null)&& pieceSel&&(!piecesOnBoard[pieceSelx][pieceSely].isSupperPos())&&!entangledPieceSel && (status != State.entangled) && (!piecesOnBoard[pieceSelx][pieceSely].canTake(cordXOfMouseClick,cordYOfMouseClick,piecesOnBoard,status))&&(piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].getPlayer() != turn)){
                    pieceSel = false;
                }
                if (pieceSel && (status != State.entangled)) {
                    movePiece(cordXOfMouseClick, cordYOfMouseClick);
//                    pieceSel = false;
                }

                //checking if it is entangeling another piece
                else if((status == State.entangled) && piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick])){
                    entangledPieceSel = true;
                    entangledPieceSelx = cordXOfMouseClick;
                    entangledPieceSely = cordYOfMouseClick;
                }

                else if ((piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick] != null)&&(piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].getPlayer() == turn)) {
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

    public void getButtonClass(SideButtons b){
        buttons = b;
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
        buttons.disableEverything();
        do{
            do{
                Graphics g = null;
                try{
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0,0,width,height);
                    if (pieceSel){ //Bug fix because when it was entagled it would remove the yellow mark selection just show green
                        renderSquareGraphic(g,pieceSelx,pieceSely,yellow);
                    }
                    for (int y = 0 ; y < 8; y++){
                        for (int x = 0; x < 8; x++){
                            if ((y == cordYOfMouseClick && x == cordXOfMouseClick) && mouseHasClicked){
                                renderSquareGraphic(g,x,y,Color.yellow);
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
                    if(pieceSel && (piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick] != null)){
                        if (piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].getPlayer() == 1){
                            buttons.update(piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick],player1.getEntanglement(), player1.getTunneling(),piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].isSuperPosAllowed(),piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].isSupperPos(),player1);
                        }
                        else{
                            buttons.update(piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick],player2.getEntanglement(), player2.getTunneling(),piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].isSuperPosAllowed(),piecesOnBoard[cordXOfMouseClick][cordYOfMouseClick].isSupperPos(),player2);
                        }
                    }
                    if (pieceSel && (piecesOnBoard[pieceSelx][pieceSely] != null) && !piecesOnBoard[pieceSelx][pieceSely].isDummy() && !piecesOnBoard[pieceSelx][pieceSely].isSupperPos()){
                        Point tmp;
                        int passPiece = 0;
                        int opponentsEncountered = 0;
                        while(piecesOnBoard[pieceSelx][pieceSely].hasNext()) {
                            tmp = piecesOnBoard[pieceSelx][pieceSely].getNext();
                            if (tmp != null) {
                                if (piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()] != null) {
                                    if ((piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[(int) tmp.getX()][(int) tmp.getY()])) && (opponentsEncountered == 0) && (status != State.tunneling)) {
                                        opponentsEncountered++;
                                        if((!entangledPieceSel ) && (status != State.entangled) && (piecesOnBoard[pieceSelx][pieceSely].getClass() != Pawn.class)) {
                                            renderSquareGraphic(g, (int) tmp.getX(), (int) tmp.getY(), red);
                                        }
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
                                    renderSquareGraphic(g, (int) tmp.getX(), (int) tmp.getY(), green);

                                }
                            }
                            else{
                                passPiece = 0;
                                opponentsEncountered = 0;
                            }

                        }

                    }
                    //This if is to show the red squares for the paws, To graphically show if the pawn can take a piece or not
                    if (!entangledPieceSel && pieceSel && (piecesOnBoard[pieceSelx][pieceSely] != null)&&( piecesOnBoard[pieceSelx][pieceSely].getClass() == Pawn.class )&& !piecesOnBoard[pieceSelx][pieceSely].isDummy() && !piecesOnBoard[pieceSelx][pieceSely].isSupperPos()){
                        int tmpPosX = piecesOnBoard[pieceSelx][pieceSely].getPosx();
                        int tmpPosy = piecesOnBoard[pieceSelx][pieceSely].getPosY();
                        if ((piecesOnBoard[pieceSelx][pieceSely].getPlayer() == 1) && ( tmpPosy<= 6) && (tmpPosy >= 1)){
                            if( (piecesOnBoard[pieceSelx][pieceSely].getPosx() < 7) && (piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[tmpPosX+1][tmpPosy-1])) ) {//check if you can draw the right red square
                                renderSquareGraphic(g, tmpPosX + 1, tmpPosy - 1, red);
                            }
                            if( (piecesOnBoard[pieceSelx][pieceSely].getPosx() > 0)&&(piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[tmpPosX-1][tmpPosy-1]))){ //check if you can draw the left red square
                                renderSquareGraphic(g, tmpPosX - 1, tmpPosy - 1, red);
                            }
                        }
                        else if((piecesOnBoard[pieceSelx][pieceSely].getPlayer() == 2) && (tmpPosy >= 1) && ( tmpPosy<= 6) ) {
                            if ((piecesOnBoard[pieceSelx][pieceSely].getPosx() < 7) && (piecesOnBoard[pieceSelx][pieceSely].getPosx() > 0)){
                                if ((piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[tmpPosX-1][tmpPosy+1]))){
                                    renderSquareGraphic(g, tmpPosX - 1, tmpPosy + 1, red);
                                }
                                if ((piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[tmpPosX+1][tmpPosy+1]))){
                                    renderSquareGraphic(g, tmpPosX + 1, tmpPosy + 1, red);
                                }
                            }
                            else if( (piecesOnBoard[pieceSelx][pieceSely].getPosx() == 7) && (piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[tmpPosX-1][tmpPosy+1]))) {//check if you can draw the left red square
                                renderSquareGraphic(g, tmpPosX - 1, tmpPosy + 1, red);
                            }
                            else if( (piecesOnBoard[pieceSelx][pieceSely].getPosx() == 0) && (piecesOnBoard[pieceSelx][pieceSely].isOpponent(piecesOnBoard[tmpPosX+1][tmpPosy+1]))){ //check if you can draw the right red square
                                renderSquareGraphic(g, tmpPosX + 1, tmpPosy + 1, red);
                            }
                        }
                    }
                    //This draws entanglements for the selected pieces
                    if(entangledPieceSel){
                        renderSquareGraphic(g, entangledPieceSelx, entangledPieceSely, blue);
                        renderSquareGraphic(g, pieceSelx, pieceSely, blue);
                    }
                    //this draws the pieces
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
            if (pieceSel && ((pieceSelx != x) || (pieceSely != y))) {//entanglement is happening
                if (entangledPieceSel){
                    int tmpx = pieceSelx - x;
                    int tmpy = pieceSely - y;
                    tmpx += entangledPieceSelx;
                    tmpy += entangledPieceSely;

                    Piece tmp = deepCopy(piecesOnBoard[pieceSelx][pieceSely]);

                    tmp.updatePiecePos(entangledPieceSelx,entangledPieceSely);

                    if ((tmp.validPos(tmpx,tmpy,piecesOnBoard,State.none))&&((tmpx != x) || (tmpy != y))){ //entangled pieces can not take one another
                        piecesOnBoard[x][y] = piecesOnBoard[pieceSelx][pieceSely];
                        piecesOnBoard[x][y].updatePiecePos(x, y);
                        piecesOnBoard[pieceSelx][pieceSely] = null;

                        piecesOnBoard[tmpx][tmpy] = piecesOnBoard[entangledPieceSelx][entangledPieceSely];
                        piecesOnBoard[tmpx][tmpy].updatePiecePos(tmpx, tmpy);
                        piecesOnBoard[entangledPieceSelx][entangledPieceSely] = null;
                        entangledPieceSel = false;
                        if(piecesOnBoard[x][y].getPlayer() == 1){ //decrementing the entanglement on the player
                            player1.decrementEntanglement();
                        }
                        else{
                            player2.decrementEntanglement();
                        }
                    }
                    else{
                        entangledPieceSel = false;
                        mouseHasClicked = false;
                        pieceSel = false;
                        status= State.none;
                        boardGraphics();
                        return false;
                    }

                }
                else if (status != State.superpos) {
                    piecesOnBoard[x][y] = piecesOnBoard[pieceSelx][pieceSely];
                    piecesOnBoard[x][y].updatePiecePos(x, y);
                    piecesOnBoard[pieceSelx][pieceSely] = null;
                    if(status == State.tunneling){ //decrementing when tunneling is used
                        if(piecesOnBoard[x][y].getPlayer() == 1){
                            player1.decrementTunneling();
                        }
                        else{
                            player2.decrementTunneling();
                        }
                    }
                    // piecesOnBoard[pieceSelx][pieceSely] = null;
                }
                else{ //if superpos is selected this will be done
                    piecesOnBoard[x][y] = new Piece(x,y,pieceSelx,pieceSely,piecesOnBoard[pieceSelx][pieceSely].getPlayer()) {
                        @Override
                        public boolean validPos(int x, int y, Piece[][] piecesOnBoard,State state) {
                            return super.validPos(x, y, piecesOnBoard,State.none);
                        }
                    };
                    piecesOnBoard[pieceSelx][pieceSely].setSuperPosX(x);
                    piecesOnBoard[pieceSelx][pieceSely].setSuperPosY(y);
                    piecesOnBoard[pieceSelx][pieceSely].setToSuperPos();
                }
                entangledPieceSel = false;
                mouseHasClicked = false;
                pieceSel = false;
                status= State.none;
                changeTurn();
                boardGraphics();
                return true;
            }
            //the piece is being taken
        }else if(!piecesOnBoard[pieceSelx][pieceSely].isDummy() && !entangledPieceSel && (piecesOnBoard[pieceSelx][pieceSely].canTake(x,y,piecesOnBoard,status))) { //entangled pieces should not be able to take
            System.out.println("MovePiece can take?");
            observe(true,x,y);
            Piece taken = piecesOnBoard[x][y];
            if(taken == null){
                piecesOnBoard[x][y] = piecesOnBoard[pieceSelx][pieceSely];
                piecesOnBoard[x][y].updatePiecePos(x, y);
                piecesOnBoard[pieceSelx][pieceSely] = null;
            }
            else {
                if(taken.getClass() == King.class){// <+++++++++++++++++++++++HEre is a place for winner pop up
                    if(taken.getPlayer() == 1){
                        winnerPopup(player2);
                    }
                    else {
                        winnerPopup(player1);
                    }
                    System.exit(0);
                }
                if (piecesOnBoard[x][y].getPlayer() == 1){
                    player1.insertPieceTaken(taken);
                }else{
                    player2.insertPieceTaken(taken);
                }


                piecesOnBoard[x][y] = piecesOnBoard[pieceSelx][pieceSely];
                piecesOnBoard[x][y].updatePiecePos(x, y);
                piecesOnBoard[pieceSelx][pieceSely] = null;
            }

            mouseHasClicked = false;
            pieceSel = false;
            status= State.none;
            boardGraphics();
//            player1.getPiecesTaken(); //for just player1 need to handle with gameTurn
            //player1.getPiecesPicturesTaken(); it will be uncommented when inside of method is done.

            changeTurn();
            return true;
        }else {
            print("the movement is not allowed on that place");

            if (x != pieceSelx || y != pieceSely) {
                entangledPieceSel = false;
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
            observe(false,pieceSelx,pieceSely);
            pieceSel = false;

        }
        else if("Tunneling".equals(e.getActionCommand())){
            status = State.tunneling;
        }
        else if("Entanglement".equals(e.getActionCommand())){
            status = State.entangled;
        }
        else if ("Surrender".equals(e.getActionCommand())){//<++++++++++++Here is the place to show the winner
            if (piecesOnBoard[pieceSelx][pieceSely].getPlayer() == 1){
                winnerPopup(player2);
            }
            else{
                winnerPopup(player1);
            }
            System.exit(0);
        }
        else if ("Swap".equals(e.getActionCommand())) {
            Player tmpPlayer;
            if (piecesOnBoard[pieceSelx][pieceSely].getPlayer() == 1) {
                tmpPlayer = player1;
            } else {
                tmpPlayer = player2;
            }
            if (tmpPlayer.getNumberOfPieces() > 0) {
                ArrayList<Piece> tmpList;
                tmpList = tmpPlayer.getPiecesTaken();
                //JFrame pieceSelection = new JFrame("Select a piece to evolve pawn");

                int selection = -2;

                ArrayList<String> optionsA = new ArrayList<String>();
                int locQueen = 0, locKnight = 0, locRook = 0, locBishop = 0;
                //gets all the pieces that have been taken but only one of them
                for (int i = 0; i < tmpList.size(); i++) {
                    if ((tmpList.get(i).getClass() == Queen.class) && !optionsA.contains("Queen")) {
                        locQueen = i;
                        optionsA.add("Queen");
                    } else if ((tmpList.get(i).getClass() == Knight.class) && !optionsA.contains("Knight")) {
                        locKnight = i;
                        optionsA.add("Knight");
                    } else if ((tmpList.get(i).getClass() == Rook.class) && (!optionsA.contains("Rook"))) {
                        locRook = i;
                        optionsA.add("Rook");
                    } else if ((tmpList.get(i).getClass() == Bishop.class) && (!optionsA.contains("Bishop"))) {
                        optionsA.add("Bishop");
                        locBishop = i;
                    }
                }

                Object[] options = optionsA.toArray();
                int n = JOptionPane.showOptionDialog(null,
                        "What piece do you want to chose?",
                        "Chose a piece",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[optionsA.size() - 1]);

                if (n >= 0) {
                    if (optionsA.get(n).equals("Queen")) {
                        selection = locQueen;
                    } else if (optionsA.get(n).equals("Knight")) {
                        selection = locKnight;
                    } else if (optionsA.get(n).equals("Rook")) {
                        selection = locRook;
                    } else if (optionsA.get(n).equals("Bishop")) {
                        selection = locBishop;
                    }
                } else {
                    selection = n;
                }

                if (selection >= 0) {
                    if (piecesOnBoard[pieceSelx][pieceSely].getPlayer() == 1) {
                        piecesOnBoard[pieceSelx][pieceSely] = player1.getPiecesTaken().get(selection);
                        piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx, pieceSely);
                    } else {
                        piecesOnBoard[pieceSelx][pieceSely] = player2.getPiecesTaken().get(selection);
                        piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx, pieceSely);
                    }

                }
//            if ((pieceSely== 0 |pieceSely == 7) && (piecesOnBoard[pieceSelx][pieceSely].getClass().equals(Pawn.class))){
//                Piece oldPawn = piecesOnBoard[pieceSelx][pieceSely];
//                piecesOnBoard[pieceSelx][pieceSely] = null;
//                if (oldPawn.getColor().equals(Color.BLACK)){
//                    piecesOnBoard[pieceSelx][pieceSely]=   new Bishop(pieceSelx,pieceSely,"piece_pictures/black_"+selection+"_full.png","piece_pictures/black_"+ selection +"_superpos.png",unitX,unitY,oldPawn.getColor(),oldPawn.getPlayer());
//                }
//                else{
//                    piecesOnBoard[pieceSelx][pieceSely]=   new Bishop(pieceSelx,pieceSely,"piece_pictures/white"+selection+"_full.png","piece_pictures/white_"+selection+"_superpos.png",unitX,unitY,oldPawn.getColor(),oldPawn.getPlayer());
//                }
//            }
                //pieceSelection.dispose();
            }
        }
        boardGraphics();
    }

    private int collapse(){
        return 1;//(int)(Math.random() + 0.49876543210);
    }

    //deep copies the piece however it supposes it is the piece  of the other player
    private Piece deepCopy(Piece piece){
        if(piece.getClass() == Pawn.class){
            return new Pawn(piece,((piece.getPlayer()%2)+1));
        }
        else if(piece.getClass() == Bishop.class){
            return new Bishop(piece,((piece.getPlayer()%2)+1));
        }
        else if(piece.getClass() == King.class){
            return new King(piece,((piece.getPlayer()%2)+1));
        }
        else if(piece.getClass() == Knight.class){
            return new Knight(piece,((piece.getPlayer()%2)+1));
        }
        else if(piece.getClass() == Queen.class){
            return new Queen(piece,((piece.getPlayer()%2)+1));
        }
        else if(piece.getClass() == Rook.class){
            return new Rook(piece,((piece.getPlayer()%2)+1));
        }
        return null;
    }

    public void changeTurn(){
        turn = (turn%2) + 1;
    }

    public void winnerPopup(Player player){
        JOptionPane.showConfirmDialog(
                this,player.getPlayerName() +" won the game","Congratz",JOptionPane.OK_CANCEL_OPTION
        );

    }
    public void observe(boolean enemy,int pieceSelx,int pieceSely){
        int tmp = collapse();
        print("The Collapse Function Resulted in " + tmp);
        if(tmp == 1){
            if (piecesOnBoard[pieceSelx][pieceSely].isDummy()){
                tmp = piecesOnBoard[pieceSelx][pieceSely].getPosx();
                int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getPosY();
                piecesOnBoard[pieceSelx][pieceSely] = null;
                pieceSelx = tmp;
                pieceSely = tmp2;
                piecesOnBoard[pieceSelx][pieceSely].setToNormal(enemy);
                piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);

            }
            else{
                tmp = piecesOnBoard[pieceSelx][pieceSely].getSuperPosX();
                int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getSuperPosY();
                piecesOnBoard[tmp][tmp2] = null;
                piecesOnBoard[pieceSelx][pieceSely].setToNormal(enemy);
                piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);
            }

        }
        else{
            if (piecesOnBoard[pieceSelx][pieceSely].isDummy()){
                tmp = piecesOnBoard[pieceSelx][pieceSely].getPosx();
                int tmp2 = piecesOnBoard[pieceSelx][pieceSely].getPosY();
                piecesOnBoard[pieceSelx][pieceSely] = piecesOnBoard[tmp][tmp2];
                piecesOnBoard[pieceSelx][pieceSely].setToNormal(enemy);
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
                piecesOnBoard[pieceSelx][pieceSely].setToNormal(enemy);
                piecesOnBoard[pieceSelx][pieceSely].updatePiecePos(pieceSelx,pieceSely);
            }
        }
    }
}
