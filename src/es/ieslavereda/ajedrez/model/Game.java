package es.ieslavereda.ajedrez.model;

import es.ieslavereda.ajedrez.view.Screen;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    Scanner sc = new Scanner(System.in);
    private Board b;
    private String player1, player2;
    private PieceColor turn;

    public Game() {
        b = new Board();
        player1 = putTheName();
        turn = PieceColor.WHITE;
        player2 = putTheName();
        startTheGame();
    }

    /**
     * Métodoq ue pide un nombre y lo devuelve
     * @return un nombre
     */
    public String putTheName() {
        String str = "";
        if (player1 == null) {
            System.out.println("Give me the name of the player1 // White");
            str = sc.nextLine();
            return str;
        }
        System.out.println("Give me the name of the player2 // Black");
        str = sc.nextLine();
        return str;
    }

    /**
     * Método que empieza el juego
     */
    private void startTheGame() {
        boolean near = false;
        do {
            if(b.isJaque(turn)){
                System.out.println("--------------"+turn + " is in check--------------");
            }
                System.out.println(b.isJaque(turn));
                showTable();
                Cell cell = getCellForPlayer();
                highLightTheCells(cell);
                showTable();
                moveToThatPos(cell);
                b.resetColor();
                showTable();
        }while (!near || !isInCheckMate(turn));


    }

    /**
     * método que de una celda pregunta a donde quieres que se mueva la celda
     * @param cell celda de la cual voy a querer moverme hacia otra posicion
     */
    public void moveToThatPos(Cell cell) {
        List<Coord> coords = cell.getPiece().getNextMovements();
        System.out.println("The yellow cells are the cells that you can move");
        System.out.println("Put a coord to move into that position");
        String coordinate = sc.nextLine();
        char character = ' ';
        int number = 0;
        boolean isCorrect = false;
        do {
            if (coordinate.length() > 1) {
                character = getTheChar(coordinate);
                number = getTheNumber(coordinate);
                Coord c = new Coord(character, number);
                if (b.containsCellAt(c)) {
                    for (Coord aux : coords) {
                        if (aux.equals(c)) {
                            if(b.containsPieceAt(c)){
                                b.addMovement(new Movements(cell.getCoord(), c, b.getCellAt(c).getPiece()));
                            }else{
                                b.addMovement(new Movements(cell.getCoord(), c));
                            }
                            b.moveToThatPosition(cell.getCoord(), c);

                            if(b.isJaque(turn)){
                                b.goBeforeMovements();
                                System.out.println("This movement is going to make your king be in check");
                                System.out.println("Put other coordinate for avoid the check");
                                coordinate = sc.nextLine();
                            }else{
                                b.deleteMovementsAfter();
                                turn = turn.next();
                                isCorrect = true;
                            }

                        }
                    }
                } else {
                    System.out.println("That position isn't on the moves on the board");
                }
            } else {
                System.out.println("The coord that you put in is incorrect");
            }
        } while (!isCorrect);
    }

    /**
     * método que enseña el tablero, más comodo porque te olvidas de poner el turno
     */
    public void showTable() {
        if (turn == PieceColor.WHITE) {
            showWhiteBoard();
        } else {
            showBlackBoard();
        }
    }

    /**
     * Método que comprueba si está en jaque mate
     * @param ps variable para saber el turno
     * @return true/false si está o no en jaque
     */
    public boolean isInCheckMate(PieceColor ps){
        Board bs = b.getCopyOfBoard();
        Set<Piece> pieces = bs.getAllCells().stream()
                .filter(c -> c.getPiece() != null)
                .filter(c -> c.getPiece().getColor() == ps)
                .map(c -> c.getPiece())
                 .collect(Collectors.toSet());
        for(Piece piece : pieces){
            List<Coord> coords = new LinkedList<Coord>(piece.getNextMovements());
            for(Coord coord : coords){
                bs.moveToThatPosition(piece.getCell().getCoord(), coord);
            }
            if(!bs.isJaque(turn)){
                return false;
            }
        }
        return true;
    }

    /**
     * método para cambiar de las cordenadas de donde se pueda mover la celda que le paso
     * @param cell celda para que pueda ser comprobada y cambiar de color las cordenadas de movimiento
     */
    public void highLightTheCells(Cell cell) {
        b.highLight(cell.getPiece().getNextMovements());
    }

    /**
     * Método que pide una celda y la devuelve
     * @return celda que da el usuario
     */
    public Cell getCellForPlayer() {
        boolean isCorrect = false;
        char character;
        int number;
        Cell theCell = null;
        do {
            System.out.println("Give me a coordinate of a cell like this = [ A1 | a1 ]");
            System.out.println("For the letter = [A - B - C - D - E - F - G - H]");
            System.out.println("For the numbers = [1 - 2 - 3 - 4 - 5 - 6 - 7 - 8]");
            String coordinate = sc.nextLine();
            if (coordinate.length() > 1) {
                character = getTheChar(coordinate);
                number = getTheNumber(coordinate);
                Coord c = new Coord(character, number);
                if (b.containsCellAt(c)) {
                    if (b.containsPieceAt(c)) {
                        if (b.getCellAt(c).getPiece().getColor() == getColorPlayer()) {
                            if (b.getCellAt(c).getPiece().getNextMovements().size() > 0) {
                                theCell = b.getCellAt(c);
                                isCorrect = true;
                            } else {
                                System.out.println("This piece can't move");
                            }
                        } else {
                            System.out.println("The piece that you get isn't yours");
                        }
                    } else {
                        System.out.println("That cell doesn't have a piece");
                    }
                } else {
                    System.out.println("That values are incorrect or doesn't exists on the board");
                }
            } else {
                System.out.println("The coord that you put in is incorrect");
            }
        } while (!isCorrect);
        return theCell;
    }

    /**
     * Método que me da el turno que actual
     * @return turno actual
     */
    public PieceColor getColorPlayer() {
        return turn;
    }

    /**
     * método que comrpueba el character que le he pasado
     * @param str string que lleva una letra y un numero
     * @return devuelve la letra
     */
    public char getTheChar(String str) {
        char aux = ' ';
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                aux = str.charAt(i);
            }
        }
        return aux;
    }

    /**
     * método que comrpueba el numero que le he pasado
     * @param str string que lleva una letra y un numero
     * @return devuelve el numero
     */
    public int getTheNumber(String str) {
        int aux = ' ';
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                aux = Character.getNumericValue(str.charAt(i));
            }
        }
        return aux;
    }

    /**
     * método que llama a screen show white board
     */
    private void showWhiteBoard() {
        System.out.println("Turn of " + player1);
        Screen.show(b, PieceColor.WHITE);
        Screen.showDeletePieces(b.getStore());
    }

    /**
     * método que llama a screen show black board
     */
    private void showBlackBoard() {
        System.out.println("Turn of " + player2);
        Screen.show(b, PieceColor.BLACK);
        Screen.showDeletePieces(b.getStore());
    }


}
