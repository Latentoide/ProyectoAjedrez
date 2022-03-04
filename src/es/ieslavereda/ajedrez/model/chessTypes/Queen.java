package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static es.ieslavereda.ajedrez.model.chessTypes.Bishop.getMovementsAsBishop;
import static es.ieslavereda.ajedrez.model.chessTypes.Rook.getMovementsAsRook;

public abstract class Queen extends Piece implements Serializable {
    public Queen(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Método que devuelve una lista sumando los movimientos como alfíl y castillo, esos son los movimientos de la reina
     * @return devuelve una lista de los movimientos de la reina
     */
    @Override
    public List<Coord> getNextMovements(){
        List<Coord> nextMovements = new ArrayList<>();
        List<Coord> asBishop = getMovementsAsBishop(this);
        List<Coord> asRook = getMovementsAsRook(this);
        nextMovements.addAll(asBishop);
        nextMovements.addAll(asRook);
        return nextMovements;
    }
}
