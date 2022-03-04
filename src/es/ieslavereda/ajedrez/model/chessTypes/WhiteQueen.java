package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.Cell;
import es.ieslavereda.ajedrez.model.ChessType;

import java.io.Serializable;

public class WhiteQueen extends Queen implements Serializable {
    public WhiteQueen(Cell cell) {
        super(cell, ChessType.WHITE_QUEEN);
        place();
    }
}
