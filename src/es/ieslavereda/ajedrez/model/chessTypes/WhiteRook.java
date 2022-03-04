package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.Cell;
import es.ieslavereda.ajedrez.model.ChessType;
import es.ieslavereda.ajedrez.model.Coord;

import java.io.Serializable;

public class WhiteRook extends Rook implements Serializable {
    public WhiteRook(Cell cell) {
        super(cell, ChessType.WHITE_ROOK);
        place();
    }
}
