package es.ieslavereda.ajedrez.model.dynamicStructure;

import es.ieslavereda.ajedrez.model.ChessType;
import es.ieslavereda.ajedrez.model.IDeletedPieceManager;
import es.ieslavereda.ajedrez.model.Piece;

import java.util.LinkedList;
import java.util.List;

public class MyLinkedList implements IDeletedPieceManager {

    List<Piece> ps;

    /**
     * crea una nueva lista genérica
     */
    public MyLinkedList(){
        this.ps = new LinkedList<>();
    }

    /**
     * Coge el primer nodo que tenga
     */
    public Piece getFirst(){
        return ps.get(0);
    }

    /**
     * Añade una pieza dentro de nuestra lista
     * @param piece pieza a añadir
     */
    @Override
    public void add(Piece piece) {
        ps.add(piece);
    }

    /**
     * Quita una pieza dentro de la lista
     * @param piece pieza a quitar
     */
    @Override
    public void remove(Piece piece) {
        ps.remove(piece);
    }

    /**
     * Nos devuelve una cuenta de piezas que le pasemos por el método
     * @param chessType tipo de ficha que queremos mirar
     * @return cuenta de las fichas
     */
    @Override
    public int count(ChessType chessType) {
        return (int) ps.stream().filter(p->p.getChessType()==chessType).count();
    }
}
