package es.ieslavereda.ajedrez.view;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.ajedrez.model.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Screen {
    /**
     * método que enseña el tablero dependiendo del color
     * @param board tablero a mostrar
     * @param color color por el cual se va mostrar
     */
    public static void show(Board board, PieceColor color){
        if(color == PieceColor.BLACK){
            showBlack(board);
        }else{
            showWhite(board);
        }
    }

    /**
     * método que enseña el tablero desde la perspectiva de las negras
     * @param board tablero a mostrar
     */
    private static void showBlack(Board board){
        String out = "";
        out += "   H  G  F  E  D  C  B  A  "+ "\n";
        for (int row = 1; row <= 8; row++) {
            out += row+" ";
            for (int j = 7; j >= 0; j--) {
                out += board.getCellAt(new Coord((char)('A'+j),row));
            }
            out += " " +row+"\n";
        }
        out += "   H  G  F  E  D  C  B  A  "+ "\n";
        System.out.println(out);
    }
    /**
     * método que enseña el tablero desde la perspectiva de las blancas
     * @param board tablero a mostrar
     */
    private static void showWhite(Board board){
        String out = "";
        out += "   A  B  C  D  E  F  G  H  "+ "\n";
        for (int row = 8; row >= 1; row--) {
            out += row+" ";
            for (int j = 0; j < 8; j++) {
                out += board.getCellAt(new Coord((char)('A'+j),row));
            }
            out += " " + row+  "\n";
        }
        out += "   A  B  C  D  E  F  G  H  "+ "\n";
        System.out.println(out);
    }

    /**
     * Método que cuenta las piezas dentro de un tablero
     * @param b tablero
     * @param cT pieza a mirar
     * @return cuantas piezas hay de ese mismo tipo
     */
    public static long showPieces(Board b, ChessType cT){
        return b.getAllCells().stream()
                .filter(c -> c.getPiece() != null && c.getPiece().getChessType() == cT)
                .count();
    }

    /**
     * método que muestra las piezas vivas dentro del tablero
     * @param b tablero a comprobar sus piezas
     */
    public static void showAlivePieces(Board b){
        String out = "";
        for (ChessType chessType : ChessType.values()){
            out += colorize( " "+ chessType.getShape()+" ", Cell.CellColor.BLACK_CELL.getColor(), chessType.getColor().getAttribute());
        }
        out += "\n";
        for (ChessType chessType : ChessType.values()){
            out += colorize( " "+ showPieces(b, chessType)+" ", Cell.CellColor.WHITE_CELL.getColor(), Attribute.TEXT_COLOR(150,150,150));
        }

        System.out.println(out);
    }

    /**
     * método que muestra las piezas que contiene, que en este caso serán las "muertas"
     * @param store interfaz que se necesita para poder contarlas
     */
    public static void showDeletePieces(IDeletedPieceManager store){

        String out = "";
        for (ChessType chessType : ChessType.values()){
            out += colorize( " "+ chessType.getShape()+" ", Cell.CellColor.BLACK_CELL.getColor(), chessType.getColor().getAttribute());
        }
        out += "\n";
        for (ChessType chessType : ChessType.values()){
            out += colorize( " "+ store.count(chessType)+" ", Cell.CellColor.WHITE_CELL.getColor(), Attribute.TEXT_COLOR(150,150,150));
        }

        System.out.println(out);
    }

}
