package es.ieslavereda.ajedrez.model;

import es.ieslavereda.ajedrez.model.chessTypes.*;
import es.ieslavereda.ajedrez.model.dynamicStructure.MyLinkedList;
import es.ieslavereda.ajedrez.model.dynamicStructure.MyList;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Board implements Serializable {
    private Map<Coord, Cell> cells;
    private IDeletedPieceManager store;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    private Stack<Movements> before;
    private Stack<Movements> after;


    public Board(){
        before = new Stack<>();
        after = new Stack<Movements>();
        cells = new HashMap<>();
        store = new MyLinkedList();
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();
        Coord coord;
        Cell cell;
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 8; j++) {
                coord =  new Coord((char)('A'+j), i);
                cell = new Cell(this, coord);
                cells.put(coord, cell);
            }
        }
        placePieces();
    }

    /**
     * Método que nos permite recoger el stack de movimientos anteriores
     * @return stack de movimientos anteriores
     */
    public Stack<Movements> getBefore(){
        return before;
    }

    /**
     * Método que nos permite recoger el stack de movimientos de después
     * @return stack de movimientos de después
     */
    public Stack<Movements> getAfter(){
        return after;
    }

    /**
     * Método que nos devuelve el manager de guardar piezas
     * @return manager de guardar piezas
     */
    public IDeletedPieceManager getStore() {
        return store;
    }

    /**
     * Método que añade piezas y crea dentro de una lista, estas se ponen directamente en el board
     */
    private void placePieces(){
        Cell c;
        for (int i = 0; i < 8; i++) {
            Coord co = new Coord((char)('A'+i), 7);
            c = getCellAt(co);
            blackPieces.add(new BlackPawn(c));
            whitePieces.add(new WhitePawn(getCellAt(new Coord((char)('A'+i), 2))));
        }
        whitePieces.add(new WhiteKnight(getCellAt(new Coord('B', 1))));
        whitePieces.add(new WhiteKnight(getCellAt(new Coord('G', 1))));
        blackPieces.add(new BlackKnight(getCellAt(new Coord('B', 8))));
        blackPieces.add(new BlackKnight(getCellAt(new Coord('G', 8))));
        whitePieces.add(new WhiteRook(getCellAt(new Coord('A', 1))));
        whitePieces.add(new WhiteRook(getCellAt(new Coord('H', 1))));
        blackPieces.add(new BlackRook(getCellAt(new Coord('A', 8))));
        blackPieces.add(new BlackRook(getCellAt(new Coord('H', 8))));
        whitePieces.add(new WhiteBishop(getCellAt(new Coord('C', 1))));
        whitePieces.add(new WhiteBishop(getCellAt(new Coord('F', 1))));
        blackPieces.add(new BlackBishop(getCellAt(new Coord('C', 8))));
        blackPieces.add(new BlackBishop(getCellAt(new Coord('F', 8))));
        whitePieces.add(new WhiteKing(getCellAt(new Coord('E', 1))));
        blackPieces.add(new BlackKing(getCellAt(new Coord('E', 8))));
        whitePieces.add(new WhiteQueen(getCellAt(new Coord('D', 1))));
        blackPieces.add(new BlackQueen(getCellAt(new Coord('D', 8))));

    }

    /**
     * Método que permite conocer si puede hacer enroque largo o no
     * @param turn variable para poder mirar por blancas o negras
     * @return true/false de si puede hacer el enroque
     */
    public boolean castlingL(PieceColor turn){

        if(turn == PieceColor.WHITE){
            Set<Coord> movementsOfOtherPiece = getMovementsFromColor(turn.next());
            Rook r;
            King k;
            if(getCellAt(new Coord('A', 1)).getPiece() != null &&
                    getCellAt(new Coord('A', 1)).getPiece().getChessType() == ChessType.WHITE_ROOK &&
                        getCellAt(new Coord('E', 1)).getPiece() != null
                            && getCellAt(new Coord('E', 1)).getPiece().getChessType() == ChessType.WHITE_KING){
                r = (Rook) getCellAt(new Coord('A', 1)).getPiece();
                k = (King) getCellAt(new Coord('E', 1)).getPiece();
                if(!r.getHaveMove() && !k.getHaveMove()){
                    List<Coord> isntInThisCoords = new LinkedList<Coord>();
                    isntInThisCoords.add(new Coord('D', 1));
                    isntInThisCoords.add(new Coord('C', 1));
                    isntInThisCoords.add(new Coord('B', 1));
                    for (Coord c : isntInThisCoords){
                        if(getCellAt(c).getPiece() != null){
                            return false;
                        }
                        if(movementsOfOtherPiece.contains(c)){
                            return false;
                        }
                    }
                    getCellAt(new Coord('E', 1)).setPiece(null);
                    getCellAt(new Coord('C', 1)).setPiece(new WhiteKing(getCellAt(new Coord('C', 1))));
                    getCellAt(new Coord('A', 1)).setPiece(null);
                    getCellAt(new Coord('D', 1)).setPiece(new WhiteRook(getCellAt(new Coord('D', 1))));

                    return true;
                }
            }
        }else{
            Set<Coord> movementsOfOtherPiece = getMovementsFromColor(turn.next());
            Set<Coord> whereAreMyPieces = getCoordsOfMyPieces(turn);
            Rook r;
            King k;
            if(getCellAt(new Coord('A', 8)).getPiece() != null &&
                    getCellAt(new Coord('A', 8)).getPiece().getChessType() == ChessType.BLACK_ROOK &&
                    getCellAt(new Coord('E', 8)).getPiece() != null
                    && getCellAt(new Coord('E', 8)).getPiece().getChessType() == ChessType.BLACK_KING){
                r = (Rook)getCellAt(new Coord('A', 8)).getPiece();
                k = (King) getCellAt(new Coord('E', 8)).getPiece();
                if(!r.getHaveMove() && !k.getHaveMove()){
                    List<Coord> isntInThisCoords = new LinkedList<Coord>();
                    isntInThisCoords.add(new Coord('D', 8));
                    isntInThisCoords.add(new Coord('C', 8));
                    isntInThisCoords.add(new Coord('B', 8));
                    for (Coord c : isntInThisCoords){
                        if(getCellAt(c).getPiece() != null){
                            return false;
                        }
                        if(movementsOfOtherPiece.contains(c)){
                            return false;
                        }
                    }
                    getCellAt(new Coord('E', 8)).setPiece(null);
                    getCellAt(new Coord('C', 8)).setPiece(new WhiteKing(getCellAt(new Coord('C', 8))));
                    getCellAt(new Coord('A', 8)).setPiece(null);
                    getCellAt(new Coord('D', 8)).setPiece(new WhiteRook(getCellAt(new Coord('D', 8))));
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * Método que permite conocer si puede hacer enroque corto o no
     * @param turn variable para poder mirar por blancas o negras
     * @return true/false de si puede hacer el enroque
     */
    public boolean castlingS(PieceColor turn){

        if(turn == PieceColor.WHITE){
            Set<Coord> movementsOfOtherPiece = getMovementsFromColor(turn.next());
            Set<Coord> whereAreMyPieces = getCoordsOfMyPieces(turn);
            Rook r;
            King k;
            if(getCellAt(new Coord('H', 1)).getPiece() != null &&
                    getCellAt(new Coord('H', 1)).getPiece().getChessType() == ChessType.WHITE_ROOK &&
                    getCellAt(new Coord('E', 1)).getPiece() != null
                    && getCellAt(new Coord('E', 1)).getPiece().getChessType() == ChessType.WHITE_KING){
                r = (Rook)getCellAt(new Coord('H', 1)).getPiece();
                k = (King) getCellAt(new Coord('E', 1)).getPiece();
                if(!r.getHaveMove() && !k.getHaveMove()){
                    List<Coord> isntInThisCoords = new LinkedList<Coord>();
                    isntInThisCoords.add(new Coord('F', 1));
                    isntInThisCoords.add(new Coord('G', 1));
                    for (Coord c : isntInThisCoords){
                        if(getCellAt(c).getPiece() != null){
                            return false;
                        }
                        if(movementsOfOtherPiece.contains(c)){
                            return false;
                        }
                    }
                    getCellAt(new Coord('E', 1)).setPiece(null);
                    getCellAt(new Coord('G', 1)).setPiece(new WhiteKing(getCellAt(new Coord('G', 1))));
                    getCellAt(new Coord('H', 1)).setPiece(null);
                    getCellAt(new Coord('F', 1)).setPiece(new WhiteRook(getCellAt(new Coord('F', 1))));
                    return true;
                }
            }
        }else{
            Set<Coord> movementsOfOtherPiece = getMovementsFromColor(turn.next());
            Set<Coord> whereAreMyPieces = getCoordsOfMyPieces(turn);
            Rook r;
            King k;
            if(getCellAt(new Coord('H', 8)).getPiece() != null &&
                    getCellAt(new Coord('H', 8)).getPiece().getChessType() == ChessType.BLACK_ROOK &&
                    getCellAt(new Coord('E', 8)).getPiece() != null
                    && getCellAt(new Coord('E', 8)).getPiece().getChessType() == ChessType.BLACK_KING){
                r = (Rook)getCellAt(new Coord('H', 8)).getPiece();
                k = (King) getCellAt(new Coord('E', 8)).getPiece();
                if(!r.getHaveMove() && !k.getHaveMove()){
                    List<Coord> isntInThisCoords = new LinkedList<Coord>();
                    isntInThisCoords.add(new Coord('F', 8));
                    isntInThisCoords.add(new Coord('G', 8));
                    for (Coord c : isntInThisCoords){
                        if(getCellAt(c).getPiece() != null){
                            return false;
                        }
                        if(movementsOfOtherPiece.contains(c)){
                            return false;
                        }
                    }
                    getCellAt(new Coord('E', 8)).setPiece(null);
                    getCellAt(new Coord('G', 8)).setPiece(new WhiteKing(getCellAt(new Coord('G', 8))));
                    getCellAt(new Coord('H', 8)).setPiece(null);
                    getCellAt(new Coord('F', 8)).setPiece(new WhiteRook(getCellAt(new Coord('F', 8))));
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * Método que nos recoger las posiciones del jugador
     * @param turn variable para saber el jugador
     * @return lista de coordenadas del jugador
     */
    public Set<Coord> getCoordsOfMyPieces(PieceColor turn){
        return getAllCells().stream().filter(c -> c.getPiece() != null && c.getPiece().getColor() == turn)
                .flatMap(c -> c.getPiece().getNextMovements().stream()).collect(Collectors.toSet());
    }

    /**
     * Método que nos recoger los movimientos del jugador
     * @param pc variable para saber el jugador
     * @return lista de coordenadas del jugador
     */
    public Set<Coord> getMovementsFromColor(PieceColor pc){
        return getAllCells().stream()
                .filter(c -> c.getPiece() != null && c.getPiece().getColor() == pc)
                .map(Cell::getPiece)
                .flatMap(piece -> piece.getNextMovements().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Método que nos recoger una pieza en concreto
     * @param pc variable para saber el jugador
     * @param cs variable para saber la pieza
     * @return pieza que se está buscando que puede ser null
     */
    public Optional<Coord> getOneDesiredPiece(ChessType cs, PieceColor pc){
        return getAllCells().stream()
                .filter(c -> c.getPiece() != null && c.getPiece().getColor() == pc)
                .filter(c -> c.getPiece().getChessType() == cs)
                .map(c -> c.getPiece().getCell().getCoord())
                .findFirst();
    }

    /**
     * Método para saber si un jugador está en jaque o no
     * @param color turno para saber que jugador es el que hay que comprobar
     * @return true/false si está en jaque o no
     */
    public boolean isJaque(PieceColor color){
        if(color == PieceColor.BLACK){
            Set<Coord> coordsJaque =  getMovementsFromColor(PieceColor.WHITE);

            Optional<Coord> king = getOneDesiredPiece(ChessType.BLACK_KING, PieceColor.BLACK);
            if(!king.isEmpty() && coordsJaque.remove(king.get())){
                return true;
            }else{
                return false;
            }
        }else{
            Set<Coord> coordsJaque =  getMovementsFromColor(PieceColor.BLACK);

            Optional<Coord> king = getOneDesiredPiece(ChessType.WHITE_KING, PieceColor.WHITE);

            if(!king.isEmpty() && coordsJaque.contains(king.get())){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * método que nos permite recoger todas las celdas de nuestro tablero
     * @return una colleción de las celdas
     */
    public Collection<Cell> getAllCells(){
        return cells.values();
    }

    /**
     * método que nos permite conocer si hay una celda específica en el tablero
     * @param coord coordenada que vamos a comprobar
     * @return true/false si está o no
     */
    public boolean containsCellAt(Coord coord){

        return cells.containsKey(coord);
    }

    /**
     * método que nos permite conocer si hay una celda específica y si contiene pieza en el tablero
     * @param coord coordenada que vamos a comprobar
     * @return true/false si tiene pieza o no
     */
    public boolean containsPieceAt(Coord coord){
        Cell cell = getCellAt(coord);
        if(cell == null){
            return false;
        }
        return (cell.getPiece() != null);
    }

    /**
     * método que nos deuelve una celda específica en el tablero
     * @param coord coordenada que vamos a comprobar
     * @return devuelve esa celda
     */
    public Cell getCellAt(Coord coord){
        return cells.get(coord);
    }

    /**
     * método que cambia de color las coordenadas que le pasemos
     * @param coords lista de cordenadas que se van a cambiar de color
     */
    public void highLight(List<Coord> coords){
        for(Coord coord : coords){
            this.getCellAt(coord).highlight();
        }
    }

    /**
     * método que mueve una coordenada a otra coordenada
     * @param c coordenada para moverse
     * @param go coordenada destino
     * @return true/false de si se ha realizado o no
     */
    public boolean moveToThatPosition(Coord c, Coord go){
        Cell cell = getCellAt(c);
        if(getCellAt(go).getPiece() != null){
            before.add(new Movements(c, go, getCellAt(go).getPiece()));
        }else{
            before.add(new Movements(c, go));
        }

        if(cell.getPiece().moveTo(go)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * método que resetea el color al tablero entero
     */
    public void resetColor(){
        for(Cell cell : cells.values()){
            cell.resetColor();
        }
    }

    /**
     * método que añade un movimiento a la stack de movimientos
     * @param mov movimiento a añadir
     */
    public void addMovement(Movements mov){
        before.push(mov);
    }

    /**
     * método que permite ir antes de los movimientos
     */
    public void goBeforeMovements(){
        if(!before.empty()){
            Movements mov = before.peek();
            moveToThatPosition(mov.getDestino(), mov.getOrigen());
            if(mov.getPiece() != null){
                getCellAt(mov.getDestino()).setPiece(mov.getPiece());
            }
            after.add(before.pop());
        }
    }

    /**
     * método que permite ir después de los movimientos
     */
    public void goAfterMovements(){
        if(!after.empty()){
            Movements mov = before.peek();
            moveToThatPosition(mov.getOrigen(), mov.getDestino());
            if(mov.getPiece() != null){
                getCellAt(mov.getOrigen()).setPiece(mov.getPiece());
            }
            before.add(after.pop());
        }
    }

    /**
     * método que borra los movimientos de después
     */
    public void deleteMovementsAfter(){
        after.clear();
    }

    /**
     * método que copia el tablero actual
     * @return una copia del tablero actual
     */
    public Board getCopyOfBoard(){
        Board aux = new Board();
        List<Movements> listAux = new LinkedList<Movements>(before);
        for (int i = 0; i < listAux.size() ; i++) {
            aux.moveToThatPosition(listAux.get(i).getOrigen(), listAux.get(i).getDestino());
        }
        return aux;
    }

}
