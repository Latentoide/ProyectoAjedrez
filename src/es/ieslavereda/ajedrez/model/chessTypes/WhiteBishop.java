package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;

public class WhiteBishop extends Bishop implements Serializable {
    public WhiteBishop(Cell cell) {
        super(cell, ChessType.WHITE_BISHOP);
        place();
    }
}
