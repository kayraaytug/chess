import Pieces.*;
import java.util.ArrayList;

public class Move {
    Piece[][] pieces;
    ArrayList<Piece> possibleMoves;
    public Move(Piece[][] pieces){
        this.pieces = pieces;
        this.possibleMoves = new ArrayList<>();
    }

    private void generateVerticalMoves(Piece piece){
        for (int i = piece.positionOnBoardY; i<8; i++){
            // Self tile
            if(i==piece.positionOnBoardY){
            }
            else {
                if (pieces[piece.positionOnBoardX][i].team != piece.team){
                    this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
                }

                if(!(pieces[piece.positionOnBoardX][i] instanceof Empty)){
                    break;
                }
            }
        }
        for (int i = piece.positionOnBoardY; i>=0; i--){
            // Self tile
            if(i==piece.positionOnBoardY){
            }
            else {
                if (pieces[piece.positionOnBoardX][i].team != piece.team){
                    this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
                }
                if(!(pieces[piece.positionOnBoardX][i] instanceof Empty)){
                    break;
                }
            }
        }
    }

    private void generateHorizontalMoves(Piece piece){
        for (int i = piece.positionOnBoardX; i<8; i++){
            // Self tile
            if(i==piece.positionOnBoardX){

            }
            else {
                if (pieces[i][piece.positionOnBoardY].team != piece.team){
                    this.possibleMoves.add(pieces[i][piece.positionOnBoardY]);
                }

                if(!(pieces[i][piece.positionOnBoardY] instanceof Empty)){
                    break;
                }
            }
        }
        for (int i = piece.positionOnBoardX; i>=0; i--){
            // Self tile
            if(i==piece.positionOnBoardX){

            }
            else {
                if (pieces[i][piece.positionOnBoardY].team != piece.team){
                    this.possibleMoves.add(pieces[i][piece.positionOnBoardY]);
                }
                if(!(pieces[i][piece.positionOnBoardY] instanceof Empty)){
                    break;
                }
            }
        }
    }

    private void generateDiagonalMoves(Piece piece){

        // Top-left diagonal
        for (int x = piece.positionOnBoardX - 1, y = piece.positionOnBoardY - 1; x >= 0 && y >= 0; x--, y--) {
            if (piece.team != pieces[x][y].team){
                possibleMoves.add(pieces[x][y]);
            }
            if (!((pieces[x][y]) instanceof Empty)){
                break;
            }
        }

        // Top-right diagonal
        for (int x = piece.positionOnBoardX + 1, y = piece.positionOnBoardY - 1; x < 8 && y >= 0; x++, y--) {
            if (piece.team != pieces[x][y].team){
                possibleMoves.add(pieces[x][y]);
            }
            if (!((pieces[x][y]) instanceof Empty)){
                break;
            }
        }

        // Bottom-left diagonal
        for (int x = piece.positionOnBoardX - 1, y = piece.positionOnBoardY + 1; x >= 0 && y < 8; x--, y++) {
            if (piece.team != pieces[x][y].team){
                possibleMoves.add(pieces[x][y]);
            }
            if (!((pieces[x][y]) instanceof Empty)){
                break;
            }
        }

        // Bottom-right diagonal
        for (int x = piece.positionOnBoardX + 1, y = piece.positionOnBoardY + 1; x < 8 && y < 8; x++, y++) {
            if (piece.team != pieces[x][y].team){
                possibleMoves.add(pieces[x][y]);
            }
            if (!((pieces[x][y]) instanceof Empty)){
                break;
            }
        }
    }

    public ArrayList<Piece> GenerateAllMoves(Piece piece){

        // Black pawns
        if (piece instanceof Pawn && piece.team == 'b'){

            // If pawn not on initial square, reduce its move limit
            if (piece.positionOnBoardY != 1){
                piece.moveLimit = 1;
            }

            this.possibleMoves.clear();
            try{
                for (int i = piece.positionOnBoardY+1; i<=piece.positionOnBoardY+piece.moveLimit; i++){
                // Prevent vertical capturing
                    if (pieces[piece.positionOnBoardX][i] instanceof Empty){
                        this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
                    }
                }

                // Allow diagonal capturing
                if (!(pieces[piece.positionOnBoardX+1][piece.positionOnBoardY+1] instanceof Empty)){
                    this.possibleMoves.add(pieces[piece.positionOnBoardX+1][piece.positionOnBoardY+1]);
                }
                if (!(pieces[piece.positionOnBoardX-1][piece.positionOnBoardY+1] instanceof Empty)){
                    this.possibleMoves.add(pieces[piece.positionOnBoardX-1][piece.positionOnBoardY+1]);
                }
            }
            catch (IndexOutOfBoundsException ignore){

            }

            return this.possibleMoves;
        }

        // White pawns
        else if (piece instanceof Pawn && piece.team == 'w'){

            // If pawn not on initial square, reduce its move limit
            if (piece.positionOnBoardY != 6){
                piece.moveLimit = 1;
            }

            this.possibleMoves.clear();
            try{
                for (int i = piece.positionOnBoardY-1; i>=piece.positionOnBoardY-piece.moveLimit; i--){
                    // Prevent vertical capturing
                    if (pieces[piece.positionOnBoardX][i] instanceof Empty){
                        this.possibleMoves.add(pieces[piece.positionOnBoardX][i]);
                    }
                }
                // Allow diagonal capturing

                if (!(pieces[piece.positionOnBoardX+1][piece.positionOnBoardY-1] instanceof Empty)){
                    this.possibleMoves.add(pieces[piece.positionOnBoardX+1][piece.positionOnBoardY-1]);
                }
                if (!(pieces[piece.positionOnBoardX-1][piece.positionOnBoardY-1] instanceof Empty)){
                    this.possibleMoves.add(pieces[piece.positionOnBoardX-1][piece.positionOnBoardY-1]);
                }
            }
            catch (IndexOutOfBoundsException ignore){

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
            //DetectCollision(piece);
            return this.possibleMoves;
        }
    }

    public void movePiece(Piece piece, Piece target){
        //var piece_x_index = piece.positionOnBoardX;
        //var piece_y_index = piece.positionOnBoardY;
        //var piece_pos_x = piece.posX;
        //var piece_pos_y = piece.posY;
//
        //var target_x_index = target.positionOnBoardX;
        //var target_y_index = target.positionOnBoardY;
        //var target_pos_x = target.posX;
        //var target_pos_y = target.posY;
//
        //piece.positionOnBoardX = target_x_index;
        //piece.positionOnBoardY = target_y_index;
        //piece.posX = target_pos_x;
        //piece.posY = target_pos_y;
//
        //target.positionOnBoardX = piece_x_index;
        //target.positionOnBoardY = piece_y_index;
        //target.posX = piece_pos_x;
        //target.posY = piece_pos_y;
//
        //pieces[target_x_index][target_y_index] = piece;
        //pieces[piece_x_index][piece_y_index] = target;
        Piece tempTile = new Empty(piece.posX, piece.posY, 'e');
        tempTile.positionOnBoardX = piece.positionOnBoardX;
        tempTile.positionOnBoardY = piece.positionOnBoardY;
        piece.positionOnBoardX = target.positionOnBoardX;
        piece.positionOnBoardY = target.positionOnBoardY;
        piece.posX = target.posX;
        piece.posY = target.posY;
        pieces[target.positionOnBoardX][target.positionOnBoardY] = piece;
        pieces[tempTile.positionOnBoardX][tempTile.positionOnBoardY] = tempTile;


    }
}
