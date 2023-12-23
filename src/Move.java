import Pieces.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Move {
    Piece[][] pieces;
    ArrayList<Piece> possibleMoves;
    public Move(Piece[][] pieces){
        this.pieces = pieces;
        this.possibleMoves = new ArrayList<>();
    }

    private void generateVerticalMoves(Piece piece){
        for (int i = 0; i<8; i++){
            // Self tile
            if(i==piece.positionOnBoardY){
                continue;
            }
            else {
                this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
            }
        }
    }

    private void generateHorizontalMoves(Piece piece){
        for (int i = 0; i<8; i++){
            // Self tile
            if(i==piece.positionOnBoardX){
                continue;
            }
            else {
                this.possibleMoves.add(pieces[i][piece.positionOnBoardY]);
            }
        }
    }

    private void generateDiagonalMoves(Piece piece){

        // Top-left diagonal
        for (int x = piece.positionOnBoardX - 1, y = piece.positionOnBoardY - 1; x >= 0 && y >= 0; x--, y--) {
            possibleMoves.add(pieces[x][y]);
        }

        // Top-right diagonal
        for (int x = piece.positionOnBoardX + 1, y = piece.positionOnBoardY - 1; x < 8 && y >= 0; x++, y--) {
            possibleMoves.add(pieces[x][y]);
        }

        // Bottom-left diagonal
        for (int x = piece.positionOnBoardX - 1, y = piece.positionOnBoardY + 1; x >= 0 && y < 8; x--, y++) {
            possibleMoves.add(pieces[x][y]);
        }

        // Bottom-right diagonal
        for (int x = piece.positionOnBoardX + 1, y = piece.positionOnBoardY + 1; x < 8 && y < 8; x++, y++) {
            possibleMoves.add(pieces[x][y]);
        }
    }

    public ArrayList<Piece> GenerateAllMoves(Piece piece){
        var team = piece.team;
        var pieceType = piece.getClass();
        var moveLimit = piece.moveLimit;

        // Black pawns
        if (piece instanceof Pawn && piece.team == 'b'){
            this.possibleMoves.clear();
            for (int i = piece.positionOnBoardY+1; i<=piece.positionOnBoardY+moveLimit; i++){
                this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
            }
            return this.possibleMoves;
        }

        // White pawns
        else if (piece instanceof Pawn && piece.team == 'w'){
            this.possibleMoves.clear();
            for (int i = piece.positionOnBoardY-1; i>=piece.positionOnBoardY-moveLimit; i--){
                possibleMoves.add(pieces[piece.positionOnBoardX][i]);
            }
            return this.possibleMoves;
        }

        // King moves
        else if (piece instanceof King){
            this.possibleMoves.clear();
            int x = piece.positionOnBoardX;
            int y = piece.positionOnBoardY;
            int[][] moveOffsets = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}
            };

            for (int[] offset : moveOffsets) {
                try {
                    this.possibleMoves.add(pieces[x + offset[0]][y + offset[1]]);
                }

                catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
                return this.possibleMoves;
            }

        // Knight moves
        else if (piece instanceof Knight){
            this.possibleMoves.clear();
            int x = piece.positionOnBoardX;
            int y = piece.positionOnBoardY;
            int[][] moveOffsets = {
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
            };

            for (int[] offset : moveOffsets) {
                try {
                    if(pieces[x + offset[0]][y + offset[1]].team != piece.team){
                        this.possibleMoves.add(pieces[x + offset[0]][y + offset[1]]);
                    }
                }

                catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
                return this.possibleMoves;
            }

        // Other pieces
        else{
            this.possibleMoves.clear();
            if(piece.moveType.contains("VERTICAL")){
                generateVerticalMoves(piece);
            }

            if (piece.moveType.contains("HORIZONTAL")){
                generateHorizontalMoves(piece);
            }

            if (piece.moveType.contains("DIAGONAL")){
                generateDiagonalMoves(piece);
            }
            DetectCollision(piece);
            return this.possibleMoves;
        }
    }

    public void DetectCollision(Piece piece){
        System.out.println("DetectCollision called");
        if(piece.moveType.contains("DIAGONAL")){

        }
        if (piece.moveType.contains("HORIZONTAL")){
            int x = piece.positionOnBoardX+1;
            int y = piece.positionOnBoardY;
            boolean flag = false;
            while (x < 8){
                if (flag){
                    this.possibleMoves.remove(pieces[x][y]);
                }
                else if(!(pieces[x][y] instanceof Empty)){
                    this.possibleMoves.remove(pieces[x][y]);
                    flag = true;
                }
                x++;
            }

            x = piece.positionOnBoardX-1;
            y = piece.positionOnBoardY;
            flag = false;
            while (x >= 0){
                if (flag){
                    this.possibleMoves.remove(pieces[x][y]);
                }
                else if(!(pieces[x][y] instanceof Empty)){
                    this.possibleMoves.remove(pieces[x][y]);
                    flag = true;
                }
                x--;
            }
        }
        if(piece.moveType.contains("VERTICAL")){
            int x = piece.positionOnBoardX;
            int y = piece.positionOnBoardY+1;
            boolean flag = false;
            while (y < 8){
                if (flag){
                    this.possibleMoves.remove(pieces[x][y]);
                }
                else if(!(pieces[x][y] instanceof Empty)){
                    this.possibleMoves.remove(pieces[x][y]);
                    flag = true;
                }
                y++;
            }

            x = piece.positionOnBoardX;
            y = piece.positionOnBoardY-1;
            flag = false;
            while (y >= 0){
                if (flag){
                    this.possibleMoves.remove(pieces[x][y]);
                }
                else if(!(pieces[x][y] instanceof Empty)){
                    this.possibleMoves.remove(pieces[x][y]);
                    flag = true;
                }
                y--;
            }
        }
    }

    public void movePiece(Piece piece, Piece target){
        var piece_x_index = piece.positionOnBoardX;
        var piece_y_index = piece.positionOnBoardY;
        var piece_pos_x = piece.posX;
        var piece_pos_y = piece.posY;

        var target_x_index = target.positionOnBoardX;
        var target_y_index = target.positionOnBoardY;
        var target_pos_x = target.posX;
        var target_pos_y = target.posY;

        piece.positionOnBoardX = target_x_index;
        piece.positionOnBoardY = target_y_index;
        piece.posX = target_pos_x;
        piece.posY = target_pos_y;

        target.positionOnBoardX = piece_x_index;
        target.positionOnBoardY = piece_y_index;
        target.posX = piece_pos_x;
        target.posY = piece_pos_y;

        pieces[target_x_index][target_y_index] = piece;
        pieces[piece_x_index][piece_y_index] = target;


    }
}
