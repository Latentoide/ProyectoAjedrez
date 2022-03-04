package es.ieslavereda.ajedrez.model.chessTypes;

import es.ieslavereda.ajedrez.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Rook extends Piece implements Serializable {
    public static boolean continua;
    private boolean haveMove;
    public Rook(Cell cell, ChessType chessType) {
        super(cell, chessType);
        continua = true;
        haveMove = false;
    }

    /**
     * Llama a una función que proporciona una lista de coordenadas dependiendo de donde estés
     * @return Lista de coordenadas
     */
    @Override
    public List<Coord> getNextMovements(){
        return getMovementsAsRook(this);
    }

    /**
     * Es una función que proporciona una lista de coordenadas que serán los movimientos depiendo de donde estes, este método permite hacer que una pieza pueda ser como un castillo
     * @param p le paso una pieza para que pueda conocer con que pieza va a mirar los movimientos que tiene como un castillo
     * @return Devuelve una lista de coordenadas que son movimientos como un castillo
     */
    public static List<Coord> getMovementsAsRook(Piece p) {
        List<Coord> nextMovements = new ArrayList<>();
        Coord position = p.getCell().getCoord();
        continua = true;
        Coord aux = position;
        aux = aux.up();
        while (canMoveTo(aux, p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add(aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.up();
        }
        continua = true;
        aux = position;
        aux = aux.left();
        while (canMoveTo(aux, p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add( aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.left();
        }
        continua = true;
        aux = position;
        aux = aux.right();
        while (canMoveTo(aux, p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add(aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.right();
        }
        continua = true;
        aux = position;
        aux = aux.down();
        while (canMoveTo(aux,p)){
            if(canMoveTo(aux, p) && !continua){
                nextMovements.add(aux);
                break;
            }
            nextMovements.add(aux);
            aux = aux.down();
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

    /**
     * Método que nos permite saber si el castillo se ha movido o no
     * @return devuelve un booleano
     */
    public boolean getHaveMove() {
        return haveMove;
    }

    /**
     * Función que redefine como es el movimiento en el castillo, en este caso es solo para cambiar la variable haveMove
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
