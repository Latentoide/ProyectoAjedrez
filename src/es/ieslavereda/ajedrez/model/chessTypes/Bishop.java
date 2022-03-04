package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Bishop extends Piece implements Serializable {
    private static boolean continua;
    public Bishop(Cell cell, ChessType chessType) {
        super(cell, chessType);
        continua = true;
    }

    /**
     * Llama a una función que proporciona una lista de coordenadas dependiendo de donde estés
     * @return Lista de coordenadas
     */
    @Override
    public List<Coord> getNextMovements(){
        return getMovementsAsBishop(this);
    }

    /**
     * Es una función que proporciona una lista de coordenadas que serán los movimientos depiendo de donde estes, este método permite hacer que una pieza pueda ser como un alfil
     * @param p le paso una pieza para que pueda conocer con que pieza va a mirar los movimientos que tiene como un alfíl
     * @return Devuelve una lista de coordenadas que son movimientos como un alfil
     */
    public static List<Coord> getMovementsAsBishop(Piece p) {
        List<Coord> nextMovements = new ArrayList<>();
        Coord position = p.getCell().getCoord();
        Coord aux = position;
        continua = true;
        aux = aux.up().right();
        while (canMoveTo(aux, p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add(aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.up().right();
        }
        continua = true;
        aux = position;
        aux = aux.up().left();
        while (canMoveTo(aux, p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add( aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.up().left();
        }
        continua = true;
        aux = position;
        aux = aux.down().right();
        while (canMoveTo(aux, p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add(aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.down().right();
        }
        continua = true;
        aux = position;
        aux = aux.down().left();
        while (canMoveTo(aux,p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add(aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.down().left();
        }
        continua = true;
        return nextMovements;
    }

    /**
     * Es un método que define si una coordenada es valida para el movmiento
     * @param aux coordenada a comprobar
     * @param p pieza a conocer
     * @return devuelve un booleano que permite conocer si esa coordenada es valida para moverse o no
     */
    private static boolean canMoveTo(Coord aux, Piece p)
    {
        Board board = p.getCell().getBoard();
        if(board.containsCellAt(aux) && !board.containsPieceAt(aux) && continua){
            return true;
        }else if( board.containsCellAt(aux) &&
                board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor() != p.getColor()){
            continua = false;
            return true;
        }else{
            return false;
        }
    }

}
