package es.ieslavereda.ajedrez.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Cell implements Serializable {
    private Coord coord;
    private CellColor color;
    private final CellColor originalColor;
    private Piece piece;
    private Board board;

    public Cell(Board board, Coord coord){
        this.board = board;
        this.coord = coord;
        if(((coord.getLetter()-'A')+coord.getNumber())%2==0)
            originalColor = CellColor.BLACK_CELL;
        else
            originalColor = CellColor.WHITE_CELL;

        color = originalColor;
    }

    /**
     * método que podemos recoger el tablero desde una cela
     * @return tablero actual
     */
    public Board getBoard() {
        return board;
    }

    /**
     * método que nos permite recoger la cordenada asociada
     * @return coordenada asociada
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * método que nos permite recoger la pieza asociada
     * @return pieza asociada
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * método que devuelve la celda con su color
     * @return string de su color
     */
    public String toString(){
        Attribute[] myFormat = new Attribute[]{color.getColor()};
        if(piece != null){
            return piece.toString();
        }else{
            return colorize("   ", myFormat);
        }
    }

    /**
     * método que cambia de color la celda actual
     */
    public void highlight(){
        if(piece == null){
            if(originalColor == Cell.CellColor.WHITE_CELL){
                color = Cell.CellColor.WHITEYELLOW;
            }else{
               color = Cell.CellColor.BLACKYELLOW;
            }
        }else{
            if(originalColor == Cell.CellColor.WHITE_CELL){
               color = Cell.CellColor.WHITERED;
            }else{
                color = Cell.CellColor.BLACKRED;
            }
        }
    }

    /**
     * método que resetea al color original
     */
    public void resetColor(){
        color = originalColor;
    }

    /**
     * método que pone una pieza en esta celda
     * @param piece la pieza a poner
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * método para poder recoger el color de la celda
     * @return color de la celda
     */
    public CellColor getColor() {
        return color;
    }

    public enum CellColor{
        WHITE_CELL(Attribute.BACK_COLOR(180,180,180)),
        BLACK_CELL(Attribute.BACK_COLOR(100,100,100)),
        WHITERED(Attribute.BACK_COLOR(190,90,84)),
        BLACKRED(Attribute.BACK_COLOR(165,67,61)),
        WHITEYELLOW(Attribute.BACK_COLOR(205,205,100)),
        BLACKYELLOW(Attribute.BACK_COLOR(155,129, 36));

        private Attribute color;
        CellColor(Attribute color){
            this.color = color;
        }

        public Attribute getColor() {
            return color;
        }
    }


}

