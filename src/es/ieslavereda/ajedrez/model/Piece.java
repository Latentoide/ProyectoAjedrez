package es.ieslavereda.ajedrez.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

public abstract class Piece implements Serializable {
    private ChessType chessType;
    protected Cell cell;

    public Piece(Cell cell, ChessType chessType){
        this.cell = cell;
        this.chessType = chessType;
    }

    /**
     * método para poder mostrar la pieza con color
     * @return string con el color cambiado
     */
    public String toString(){
        Attribute[] myFormat = new Attribute[]{cell.getColor().getColor(), chessType.getColor().getAttribute()};

        return colorize(" " + chessType.getShape() + " ", myFormat);
    }

    /**
     * método que setea la pieza en el tablero
     */
    public void place(){
        cell.setPiece(this);
    }

    /**
     * método que recoge la celda donde se encuentra la pieza
     * @return celda de la pieza
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * método que coge el color de la pieza
     * @return color de la pieza
     */
    public PieceColor getColor(){
        return chessType.getColor();
    }

    /**
     * método abstractp de movimiento que obliga a sus "hijas" a implementarlo
     * @return lista de cordenadas de movmiento
     */
    public abstract List<Coord> getNextMovements();

    /**
     * Es un método que define si una coordenada es valida para el movmiento
     * @param aux coordenada a comprobar
     * @return devuelve un booleano que permite conocer si esa coordenada es valida para moverse o no
     */
    protected boolean canMoveTo(Coord aux)
    {
        Board board = cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) &&
                        board.containsPieceAt(aux) &&
                        board.getCellAt(aux).getPiece().getColor() != getColor());
    }

    /**
     * método para saber que tipo de pieza es
     * @return tipo de pieza
     */
    public ChessType getChessType() {
        return chessType;
    }

    /**
     * método para que se mueva esta pieza a una cordenada
     * @param c cordenada a moverse
     * @return true/false si se ha hecho o no
     */
    public boolean moveTo(Coord c){
        if(cell == null || !cell.getBoard().containsCellAt(c)){
            return false;
        }

        if(getNextMovements().contains(c)){
            Board b = cell.getBoard();
            if(b.containsPieceAt(c)){
                Piece piece = b.getCellAt(c).getPiece();
                piece.cell = null;
                b.getStore().add(piece);
            }
            cell.setPiece(null);
            cell = b.getCellAt(c);
            place();
            return true;
        }else
            return false;
        }
}
