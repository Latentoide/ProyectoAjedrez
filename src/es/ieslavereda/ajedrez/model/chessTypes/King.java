package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static es.ieslavereda.ajedrez.model.chessTypes.Bishop.getMovementsAsBishop;
import static es.ieslavereda.ajedrez.model.chessTypes.Rook.getMovementsAsRook;

public abstract class King extends Piece {
    private boolean haveMove;

    public King(Cell cell, ChessType chessType) {
        super(cell, chessType);
        haveMove = false;
    }
    /**
     * Método que proporciona una lista de coordenadas dependiendo de donde estés en este caso como un rey
     * @return Lista de coordenadas
     */
    public List<Coord> getNextMovements(){
        List<Coord> nextMovements = new ArrayList<>();
        Coord position = cell.getCoord();
        Coord aux = position.up();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.left();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }

        aux = position.up().left();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.right();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.up().right();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }

        aux = position.down();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.down().left();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.down().right();
        if(canMoveTo(aux)){
            nextMovements.add(aux);
        }


        return nextMovements;
    }

    /**
     * Método que nos permite saber si nuestro rey se ha movido o no
     * @return devuelve un booleano
     */
    public  boolean getHaveMove(){
        return haveMove;
    }

    /**
     * Función que redefine como es el movimiento en el rey, en este caso es solo para cambiar la variable haveMove
     * @param c coordenada a la cual se mueve
     * @return devuelve un booleano la confirmación de que se haya movido
     */
    @Override
    public boolean moveTo(Coord c){
        if(!haveMove){
            haveMove = true;
        }
        return super.moveTo(c);
    }

}
