import Pieces.*;
import utils.Range;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public Tile[][] tiles = new Tile[8][8];
    public Piece[][] pieces = new Piece[8][8];
    public boolean clickedOnPiece = false;
    public Piece lastClickedPiece;
    public Tile lastClickedTile;

    public Board(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        // Insane logic incoming
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if(clickedOnPiece){
                    for (int i = 0; i<8; i++){
                        for (int j = 0; j<8; j++){
                            if (tiles[i][j].clickArea.contains(x,y)){
                                lastClickedTile = tiles[i][j];
                            }
                        }
                    }
                    lastClickedPiece.posX = lastClickedTile.getPosX();
                    lastClickedPiece.posY = lastClickedTile.getPosY();
                    lastClickedPiece.clickArea.update(lastClickedTile.getPosX(),lastClickedTile.getPosY());
                    clickedOnPiece = false;
                }
                else {
                    for (int i = 0; i<8; i++){
                        for (int j = 0; j<8; j++){
                            if (pieces[i][j].clickArea.contains(x,y) && pieces[i][j].team != 'e'){
                                lastClickedPiece = pieces[i][j];
                                clickedOnPiece = true;
                            }
                        }
                    }
                }
                repaint();
            }
        });

        // Initialize tiles
        int xStart = 0, yStart = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                tiles[i][j] = new Tile(xStart, yStart);
                xStart += 100;
            }
            xStart = 0;
            yStart += 100;
        }

        // Initialize board
        // Black pieces
        pieces[0][0] = new Rook(tiles[0][0].getPosX(), tiles[0][0].getPosY(),'b');
        pieces[0][1] = new Knight(tiles[0][1].getPosX(), tiles[0][1].getPosY(),'b');
        pieces[0][2] = new Bishop(tiles[0][2].getPosX(), tiles[0][2].getPosY(),'b');
        pieces[0][3] = new King(tiles[0][3].getPosX(), tiles[0][3].getPosY(),'b');
        pieces[0][4] = new Queen(tiles[0][4].getPosX(), tiles[0][4].getPosY(),'b');
        pieces[0][5] = new Bishop(tiles[0][5].getPosX(), tiles[0][5].getPosY(),'b');
        pieces[0][6] = new Knight(tiles[0][6].getPosX(), tiles[0][6].getPosY(),'b');
        pieces[0][7] = new Rook(tiles[0][7].getPosX(), tiles[0][7].getPosY(),'b');

        for (int i = 0; i < 8; i++){
            pieces[1][i] = new Pawn(tiles[1][i].getPosX(), tiles[1][i].getPosY(),'b');
        }

        // White pieces
        pieces[7][0] = new Rook(tiles[7][0].getPosX(), tiles[7][0].getPosY(),'w');
        pieces[7][1] = new Knight(tiles[7][1].getPosX(), tiles[7][1].getPosY(),'w');
        pieces[7][2] = new Bishop(tiles[7][2].getPosX(), tiles[7][2].getPosY(),'w');
        pieces[7][3] = new King(tiles[7][3].getPosX(), tiles[7][3].getPosY(),'w');
        pieces[7][4] = new Queen(tiles[7][4].getPosX(), tiles[7][4].getPosY(),'w');
        pieces[7][5] = new Bishop(tiles[7][5].getPosX(), tiles[7][5].getPosY(),'w');
        pieces[7][6] = new Knight(tiles[7][6].getPosX(), tiles[7][6].getPosY(),'w');
        pieces[7][7] = new Rook(tiles[7][7].getPosX(), tiles[7][7].getPosY(),'w');

        for (int i = 0; i < 8; i++){
            pieces[6][i] = new Pawn(tiles[6][i].getPosX(), tiles[6][i].getPosY(),'w');
        }

        // Defining empty spaces because of null pointer exception
        for (int i = 2; i<6; i++){
            for (int j = 0; j<8; j++){
                pieces[i][j] = new Empty(tiles[i][j].getPosX(),tiles[i][j].getPosY(),'e');
            }
        }

    }

    public void drawBoard(Graphics g){
        int c = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j < 8; j++){
                if(c == 0){
                    g.setColor(new Color(184,139,74));
                    c = 1;
                }
                else {
                    g.setColor(new Color(227,193,111));
                    c = 0;
                }
                g.fillRect(tiles[i][j].getPosX(), tiles[i][j].getPosY(), 100, 100);
            }
            if (c == 0){
                c = 1;
            }
            else{
                c = 0;
            }
        }
    }

    public void drawPieces(Graphics g){

        for (int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if (pieces[i][j].image != null){
                    g.drawImage(pieces[i][j].image, pieces[i][j].posX+10, pieces[i][j].posY+10,80, 80,
                            null);
                }
            }
        }
        System.out.println("Pieces drawn");
    }
    public void paintComponent(Graphics g){
        drawBoard(g);
        drawPieces(g);
    }
}

