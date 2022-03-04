package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;

public class WhiteKnight extends Knight implements Serializable {
    public WhiteKnight(Cell cell) {
        super(cell, ChessType.WHITE_KNIGHT);
        place();
    }
}
