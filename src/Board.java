import Pieces.*;
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
    private Piece lastClickedTile;
    private int arrayPointerX, arrayPointerY;
    public Board(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

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

        // Defining empty spaces
        for (int i = 2; i<6; i++){
            for (int j = 0; j<8; j++){
                pieces[i][j] = new Empty(tiles[i][j].getPosX(),tiles[i][j].getPosY(),'e');
            }
        }

        // Insane logic incoming
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Get index of pieces[y][x]
                int x = e.getX()/100;
                int y = e.getY()/100;

                // Check if last clicked area contains a piece and next click will be on Empty,
                // This is currently prevents capturing, will be replaced later.
                if (clickedOnPiece && pieces[y][x] instanceof Empty){

                    // Empty the last clicked tile
                    pieces[arrayPointerY][arrayPointerX] = new Empty(lastClickedTile.posX, lastClickedTile.posY, 'e');

                    // Update the clicked pieces position
                    lastClickedPiece.posX = pieces[y][x].posX;
                    lastClickedPiece.posY = pieces[y][x].posY;

                    // Update it in array
                    pieces[y][x] = lastClickedPiece;

                    // Swap click operation
                    clickedOnPiece = false;
                }

                else if (!(pieces[y][x] instanceof Empty)) { // If clicked on piece

                    lastClickedPiece = pieces[y][x];    // Reference for clicked piece
                    lastClickedTile = lastClickedPiece; // Make a copy of it for swap operation
                    arrayPointerX = x;                  // Reference for the array index x and y
                    arrayPointerY = y;
                    clickedOnPiece = true;              // Swap click operation
                }

                System.out.println("Clicked on: " + pieces[y][x]);
                System.out.println("y: " + y + ", x: " + x);

                // Repaint the board and pieces
                repaint();
            }
        });
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

