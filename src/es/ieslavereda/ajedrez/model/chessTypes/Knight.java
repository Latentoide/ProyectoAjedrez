package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Knight extends Piece implements Serializable {
    public Knight(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Método que proporciona una lista de coordenadas dependiendo de donde estés en este caso como un rey
     * @return Lista de coordenadas
     */
    @Override
    public List<Coord> getNextMovements() {
        List<Coord> nextMovements = new ArrayList<>();
        Coord position = cell.getCoord();
        Coord aux;

        //Up
        aux = position.up().up().left();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        aux = position.up().up().right();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        //Down
        aux = position.down().down().left();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        aux = position.down().down().right();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        //Right
        aux = position.right().right().up();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        aux = position.right().right().down();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        //Left
        aux = position.left().left().up();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        aux = position.left().left().down();
        if(canMoveTo(aux)) {
            nextMovements.add(aux);
        }

        return nextMovements;
    }
}
