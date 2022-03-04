package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.Cell;
import es.ieslavereda.ajedrez.model.ChessType;
import es.ieslavereda.ajedrez.model.Coord;
import es.ieslavereda.ajedrez.model.Piece;

import java.io.Serializable;

public class BlackQueen extends Queen implements Serializable {
    public BlackQueen(Cell cell) {
        super(cell, ChessType.BLACK_QUEEN);
        place();;
    }
}
