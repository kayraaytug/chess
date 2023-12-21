import Pieces.Piece;

import java.util.ArrayList;

public class NotationConverter {
    static String ConvertToNotaion(Piece[][] pieces){
        String notationString = "";
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                notationString += pieces[j][i].label;
            }
        }
        return notationString;
    }
}
