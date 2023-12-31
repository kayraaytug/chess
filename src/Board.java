import Pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import utils.mp3Player;
import utils.GameRecorder;

public class Board extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int TILE_SIZE = WIDTH/8;

    private Tile[][] tiles = new Tile[8][8];
    private Piece[][] pieces = new Piece[8][8];
    private boolean clickedOnPiece = false;
    private Piece lastClickedPiece;
    ArrayList<Piece> highlightedTiles;

    Piece whiteKing;
    Piece blackKing;

    private boolean whiteKingInCheck;
    private boolean blackKingInCheck;
    private boolean gameContinues = true;

    private int turn = 0;

    public String boardAsString = "";

    public Board() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        mp3Player walkman = new mp3Player();
        GameRecorder gameRecorder = new GameRecorder();
        // Initialize tiles
        int xStart = 0, yStart = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[j][i] = new Tile(xStart, yStart);
                xStart += TILE_SIZE;
            }
            xStart = 0;
            yStart += TILE_SIZE;
        }

        // Initialize board
        // Black pieces
        pieces[0][0] = new Rook(tiles[0][0].getPosX(), tiles[0][0].getPosY(), 'b');
        pieces[1][0] = new Knight(tiles[1][0].getPosX(), tiles[1][0].getPosY(), 'b');
        pieces[2][0] = new Bishop(tiles[2][0].getPosX(), tiles[2][0].getPosY(), 'b');
        pieces[3][0] = new King(tiles[3][0].getPosX(), tiles[3][0].getPosY(), 'b');
        pieces[4][0] = new Queen(tiles[4][4].getPosX(), tiles[4][0].getPosY(), 'b');
        pieces[5][0] = new Bishop(tiles[5][0].getPosX(), tiles[5][0].getPosY(), 'b');
        pieces[6][0] = new Knight(tiles[6][0].getPosX(), tiles[6][0].getPosY(), 'b');
        pieces[7][0] = new Rook(tiles[7][0].getPosX(), tiles[7][0].getPosY(), 'b');
        blackKing = pieces[3][0];

        for (int i = 0; i < 8; i++) {
            pieces[i][1] = new Pawn(tiles[i][1].getPosX(), tiles[i][1].getPosY(), 'b');
        }

        // White pieces
        pieces[0][7] = new Rook(tiles[0][7].getPosX(), tiles[0][7].getPosY(), 'w');
        pieces[1][7] = new Knight(tiles[1][7].getPosX(), tiles[1][7].getPosY(), 'w');
        pieces[2][7] = new Bishop(tiles[2][7].getPosX(), tiles[2][7].getPosY(), 'w');
        pieces[3][7] = new King(tiles[3][7].getPosX(), tiles[3][7].getPosY(), 'w');
        pieces[4][7] = new Queen(tiles[4][7].getPosX(), tiles[4][7].getPosY(), 'w');
        pieces[5][7] = new Bishop(tiles[5][7].getPosX(), tiles[5][7].getPosY(), 'w');
        pieces[6][7] = new Knight(tiles[6][7].getPosX(), tiles[6][7].getPosY(), 'w');
        pieces[7][7] = new Rook(tiles[7][7].getPosX(), tiles[7][7].getPosY(), 'w');
        whiteKing = pieces[3][7];

        for (int i = 0; i < 8; i++) {
            pieces[i][6] = new Pawn(tiles[i][6].getPosX(), tiles[i][6].getPosY(), 'w');
        }

        // Defining empty spaces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[j][i] = new Empty(tiles[j][i].getPosX(), tiles[j][i].getPosY(), 'e');
            }
        }

        Move mover = new Move(pieces);
        Move mover2 = new Move(pieces);

        // Insane logic incoming
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameContinues){

                    // Get index of pieces[x][y]
                    int x = e.getX() / TILE_SIZE;
                    int y = e.getY() / TILE_SIZE;

                    // Game ends
                    if((whiteKingInCheck && mover2.GenerateAllMoves(whiteKing).isEmpty())
                            || blackKingInCheck && mover2.GenerateAllMoves(blackKing).isEmpty()){
                        System.out.println("Game ends");
                        gameContinues = false;
                    }

                    // If clicked tile in the possible moves list, allow to move
                    if (lastClickedPiece != null && pieces[x][y].team != lastClickedPiece.team){

                        if (highlightedTiles.contains(pieces[x][y])) {
                            if (pieces[x][y] instanceof Empty){
                                walkman.playMoveSound();
                            }
                            else {
                                walkman.playCaptureSound();
                            }

                            //if(whiteKingInCheck){
                            //    var nextBoard = pieces.clone();
                            //    var tempMover = new Move(nextBoard);
                            //    try {
                            //        var tempLastClickedPiece = lastClickedPiece.clone();
                            //        tempMover.movePiece(tempLastClickedPiece, nextBoard[x][y]);
                            //    } catch (Exception ex) {
                            //        throw new RuntimeException(ex);
                            //    }
//
                            //    if(tempMover.isInCheck(whiteKing)){
                            //        System.out.println("Move not allowed, white king in check.");
                            //        return;
                            //    }
                            //}
//
                            //else if (blackKingInCheck){
                            //    var tempPiece = lastClickedPiece;
                            //    var tempTarget = pieces[x][y];
                            //    mover.movePiece(lastClickedPiece, pieces[x][y]);
                            //    if(mover.isInCheck(blackKing)){
                            //        System.out.println("Move not allowed, black king in check.");
                            //        mover.movePiece(tempTarget, tempPiece);
                            //    }
//
                            //}

                            mover.movePiece(lastClickedPiece, pieces[x][y]);
                            String boardString = NotationConverter.ConvertToNotaion(pieces);
                            boardAsString += boardString + "\n";
                            System.out.println(boardAsString);

                            try {
                                gameRecorder.write(boardAsString);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            turn++;

                        }
                        highlightedTiles = null;
                        lastClickedPiece = null;
                    }

                    // If clicked on piece generate all possible moves and show it, store the clicked piece
                    else if (!(pieces[x][y] instanceof Empty) && turn%2 == 0 && pieces[x][y].team == 'w' ||
                    !(pieces[x][y] instanceof Empty) && turn%2 == 1 && pieces[x][y].team == 'b') {
                        lastClickedPiece = pieces[x][y];
                        var moves = mover.GenerateAllMoves(lastClickedPiece);
                        highlightedTiles = moves;
                    }

                    //System.out.println("Piece: " + pieces[x][y]);

                    whiteKingInCheck = mover2.isInCheck(whiteKing);
                    blackKingInCheck = mover2.isInCheck(blackKing);
                    System.out.println("White king in check: " + whiteKingInCheck);
                    System.out.println("Black king in check: " + blackKingInCheck);
                    // Repaint the board and pieces
                    repaint();
                }
            }
        });
    }

    public void drawBoard(Graphics g) {
        int c = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (c == 0) {
                    g.setColor(new Color(118,150,86));
                    c = 1;
                } else {
                    g.setColor(new Color(238,238,210));
                    c = 0;
                }
                g.fillRect(tiles[i][j].getPosX(), tiles[i][j].getPosY(), TILE_SIZE, TILE_SIZE);
            }
            if (c == 0) {
                c = 1;
            } else {
                c = 0;
            }
        }
    }

    public void drawPossibleMoves(Graphics g){
        if (highlightedTiles != null){
            for (var tile: highlightedTiles) {
                g.setColor(new Color(186,202,68));
                g.fillRect(lastClickedPiece.posX, lastClickedPiece.posY, TILE_SIZE,TILE_SIZE);
                g.setColor(new Color(255, 100, 100));
                g.fillRect(tile.posX, tile.posY, TILE_SIZE, TILE_SIZE);
                g.setColor(Color.darkGray);
                g.drawRect(tile.posX, tile.posY, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public void drawPieces(Graphics g) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    g.drawImage(pieces[i][j].image, pieces[i][j].posX + 10, pieces[i][j].posY + 10, 80, 80,
                            null);
                }
            }
        }
    }


    public void paintComponent(Graphics g){
        drawBoard(g);
        drawPossibleMoves(g);
        drawPieces(g);
    }
}

