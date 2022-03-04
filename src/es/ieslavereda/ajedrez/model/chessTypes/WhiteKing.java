package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.Cell;
import es.ieslavereda.ajedrez.model.ChessType;

import java.io.Serializable;

public class WhiteKing extends King implements Serializable {
    public WhiteKing(Cell cell) {
        super(cell, ChessType.WHITE_KING);
        place();
    }
}
