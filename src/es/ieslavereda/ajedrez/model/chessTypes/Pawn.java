package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.Cell;
import es.ieslavereda.ajedrez.model.ChessType;
import es.ieslavereda.ajedrez.model.Coord;
import es.ieslavereda.ajedrez.model.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Pawn extends Piece implements Serializable {

    public Pawn(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Método que proporciona una lista de coordenadas dependiendo de donde estés en este caso como un rey
     * @return Lista de coordenadas
     */
    @Override
    public List<Coord> getNextMovements() {
        return new ArrayList<>();
    }

    /**
     * Función que redefine como es el movimiento del peon
     * @param c coordenada a la cual se mueve
     * @return devuelve un booleano la confirmación de que se haya movido
     */
    @Override
    public boolean moveTo(Coord c){
        boolean resul = super.moveTo(c);
        if(c.getNumber() == 1 || c.getNumber() == 8){
            createQueen();
        }
        return resul;
    }

    /**
     * Función que obliga a los peones a crear una función para crear reinas
     */
    protected abstract void createQueen();
}
