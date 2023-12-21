import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;

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
            // Self tile and teammate tile
            if(i==piece.positionOnBoardY || piece.team == pieces[piece.positionOnBoardX][i].team){
                continue;
            }
            else {
                this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
            }
        }
    }

    private void generateHorizontalMoves(Piece piece){
        for (int i = 0; i<8; i++){
            // Self tile and teammate tile
            if(i==piece.positionOnBoardX || piece.team == pieces[i][piece.positionOnBoardY].team){
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
        ArrayList<Piece> possibleMoves = new ArrayList<>();

        // Black pawns
        if (piece instanceof Pawn && piece.team == 'b'){
            for (int i = piece.positionOnBoardY+1; i<=piece.positionOnBoardY+moveLimit; i++){
                possibleMoves.add(pieces[piece.positionOnBoardX][i]);
            }
            return possibleMoves;
        }

        // White pawns
        else if (piece instanceof Pawn && piece.team == 'w'){
            for (int i = piece.positionOnBoardY-1; i>=piece.positionOnBoardY-moveLimit; i--){
                possibleMoves.add(pieces[piece.positionOnBoardX][i]);
            }
            return possibleMoves;
        }

        // King moves
        else if (piece instanceof King){
            int x = piece.positionOnBoardX;
            int y = piece.positionOnBoardY;
            int[][] moveOffsets = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}
            };

            for (int[] offset : moveOffsets) {
                try {
                    possibleMoves.add(pieces[x + offset[0]][y + offset[1]]);
                }

                catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
                return possibleMoves;
            }

        // Knight moves
        else if (piece instanceof Knight){
            int x = piece.positionOnBoardX;
            int y = piece.positionOnBoardY;
            int[][] moveOffsets = {
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
            };

            for (int[] offset : moveOffsets) {
                try {
                    if(pieces[x + offset[0]][y + offset[1]].team != piece.team){
                        possibleMoves.add(pieces[x + offset[0]][y + offset[1]]);
                    }
                }

                catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
                return possibleMoves;
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
            return this.possibleMoves;
        }
    }

    public ArrayList<Piece> DetectCollision(ArrayList<Piece> possibleMoves){
        return possibleMoves;
    }

}
