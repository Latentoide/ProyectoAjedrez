package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BlackPawn extends Pawn implements Serializable {
    public BlackPawn(Cell cell) {
        super(cell, ChessType.BLACK_PAWN);
        place();
    }
    /**
     * Método que proporciona una lista de coordenadas para moverse como un peon
     * @return Lista de coordenadas
     */
    public List<Coord> getNextMovements() {
        List<Coord> nextMovements = new ArrayList<>();
        Coord position = cell.getCoord();
        Coord aux = position.down();
        if(position.getNumber() == 7){
            nextMovements.add(aux);
            nextMovements.add(aux.down());
        }else if(canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.right().down();
        if(attack(aux)){
            nextMovements.add(aux);
        }
        aux = position.left().down();
        if(attack(aux)){
            nextMovements.add(aux);
        }
        return nextMovements;
    }

    /**
     * metodo que crea una reina en la misma posición que el peon, en este caso una reina negra
     */
    @Override
    protected void createQueen() {
        Board b = cell.getBoard();
        b.getStore().add(this);
        cell.setPiece(null);
        cell.setPiece(new BlackQueen(cell));
    }

    /**
     * Método que nos permite conocer si se puede mover a esa posición
     * @param aux coordenada a la cual nos queremos mover
     * @return nos devuelve en formato bool la confirmación de si es apta o no
     */
    @Override
    protected boolean canMoveTo(Coord aux) {
        Board board = cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux));
    }

    /**
     * Método que nos permite conocer si puede atacar en sus esquinas
     * @param aux coordenada a conocer
     * @return nos devuelve en formato bool la confirmación de si es apta o no
     */
    private boolean attack(Coord aux){
        Board board = cell.getBoard();
        if( board.containsCellAt(aux) &&
                board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor() != getColor() && aux != this.cell.getCoord().down()){
            return true;
        }else{
            return false;
        }
    }
}
