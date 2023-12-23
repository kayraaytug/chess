import Pieces.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import utils.mp3Player;

public class Board extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int TILE_SIZE = WIDTH/8;


    private Tile[][] tiles = new Tile[8][8];
    private Piece[][] pieces = new Piece[8][8];
    private boolean clickedOnPiece = false;
    private Piece lastClickedPiece;
    ArrayList<Piece> highlightedTiles;


    public Board() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        mp3Player walkman = new mp3Player();

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

        // Insane logic incoming
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Get index of pieces[x][y]
                int x = e.getX() / TILE_SIZE;
                int y = e.getY() / TILE_SIZE;

                if (lastClickedPiece != null && pieces[x][y].team != lastClickedPiece.team){

                    // If clicked tile in the possible moves list, allow to move
                    if (highlightedTiles.contains(pieces[x][y])) {
                        if (pieces[x][y] instanceof Empty){
                            walkman.playMoveSound();
                        }
                        else {
                            walkman.playCaptureSound();;
                        }
                        mover.movePiece(lastClickedPiece, pieces[x][y]);

                    }
                    highlightedTiles = null;
                    lastClickedPiece = null;
                }

                // If clicked on piece generate all possible moves and show it, store the clicked piece
                else if (!(pieces[x][y] instanceof Empty)) {
                    lastClickedPiece = pieces[x][y];
                    var moves = mover.GenerateAllMoves(lastClickedPiece);
                    highlightedTiles = moves;
                }

                System.out.println("Piece: " + pieces[x][y]);
                System.out.println(NotationConverter.ConvertToNotaion(pieces));
                // Repaint the board and pieces
                repaint();
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

