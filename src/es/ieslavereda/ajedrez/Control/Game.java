package es.ieslavereda.ajedrez.Control;

import es.ieslavereda.ajedrez.model.*;
import es.ieslavereda.ajedrez.view.Screen;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    Scanner sc = new Scanner(System.in);
    private Board b;
    private String player1, player2;
    private PieceColor turn;
    private List<Coord> coordsEsp;

    public Game() {
        b = new Board();
        player1 = putTheName();
        turn = PieceColor.WHITE;
        player2 = putTheName();
        coordsEsp = new LinkedList<Coord>();
        coordsEsp.add(new Coord('J', 1));
        coordsEsp.add(new Coord('J', 2));
        coordsEsp.add(new Coord('J', 3));
        coordsEsp.add(new Coord('J', 4));
        startTheGame();

    }

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

    private void startTheGame() {
        boolean near = false;
        do {
            if (b.isJaque(turn)) {
                System.out.println("--------------" + turn + " is in check--------------");
            }
            System.out.println(b.isJaque(turn));
            showTable();
            Cell cell = getCellForPlayer();
            if(cell != null){
                highLightTheCells(cell);
                showTable();
                moveToThatPos(cell);
                b.resetColor();
                showTable();
            }else{
                turn = turn.next();
            }

        } while (!near && !isInCheckMate(turn));
        if (isInCheckMate(turn)) {
            System.out.println("You have lost " + turn);
        }

    }

    public void moveToThatPos(Cell cell) {
        List<Coord> coords = cell.getPiece().getNextMovements();
        System.out.println("The yellow cells are the cells that you can move");
        System.out.println("Put a coord to move into that position");
        char character = ' ';
        int number = 0;
        boolean isCorrect = false;
        do {
            String coordinate = sc.nextLine();
            if (coordinate.length() > 1) {
                character = getTheChar(coordinate);
                number = getTheNumber(coordinate);
                Coord c = new Coord(character, number);
                if (b.containsCellAt(c)) {
                    for (Coord aux : coords) {
                        if (aux.equals(c)) {
                            b.moveToThatPosition(cell.getCoord(), c);

                            if (b.isJaque(turn)) {
                                b.goBeforeMovements();
                                System.out.println("This movement is going to make your king be in check");
                                System.out.println("Put other coordinate for avoid the check");
                                coordinate = sc.nextLine();
                            } else {
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

    public void showTable() {
        if (turn == PieceColor.WHITE) {
            showWhiteBoard();
        } else {
            showBlackBoard();
        }
    }

    public boolean isInCheckMate(PieceColor color) {
        Board bs = b.getCopyOfBoard();
        Set<Piece> pieces = bs.getAllCells().stream()
                .filter(c -> c.getPiece() != null)
                .filter(c -> c.getPiece().getColor() == color)
                .map(c -> c.getPiece())
                .collect(Collectors.toSet());
        for (Piece piece : pieces) {
            List<Coord> coords = new LinkedList<Coord>(piece.getNextMovements());
            for (Coord coord : coords) {
                bs.moveToThatPosition(piece.getCell().getCoord(), coord);
                if (!bs.isJaque(color)) {
                    System.out.println(piece.getCell().getCoord() + " " + coord);
                    return false;
                } else {
                    bs.goBeforeMovements();
                }
            }
        }
        return true;
    }

    public void highLightTheCells(Cell cell) {
        b.highLight(cell.getPiece().getNextMovements());
    }

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
                if (!coordsEsp.contains(c)) {
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
                    if (c.equals(new Coord('J', 1))) {
                        saveGame();
                        System.out.println("You save the game");
                    }
                    if (c.equals(new Coord('J', 2))) {
                        loadGame();
                        System.out.println("You load the game");
                        showTable();
                    }
                    if(c.equals(new Coord('J', 3))){
                        b.castlingL(turn);
                        showTable();
                        theCell = null;
                        isCorrect = true;
                    }
                    if(c.equals(new Coord('J', 4))){
                        b.castlingS(turn);
                        showTable();
                        theCell = null;
                        isCorrect = true;
                    }
                }
            } else {
                System.out.println("Check the coord need to be like this [C2]");
            }
        } while (!isCorrect);
        return theCell;
    }

    public PieceColor getColorPlayer() {
        return turn;
    }

    public char getTheChar(String str) {
        char aux = ' ';
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                aux = str.charAt(i);
            }
        }
        return aux;
    }

    public int getTheNumber(String str) {
        int aux = ' ';
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                aux = Character.getNumericValue(str.charAt(i));
            }
        }
        return aux;
    }

    private void showWhiteBoard() {
        System.out.println("Turn of " + player1);
        Screen.show(b, PieceColor.WHITE);
        Screen.showAlivePieces(b);
        Screen.showDeletePieces(b.getStore());
    }

    private void showBlackBoard() {
        System.out.println("Turn of " + player2);
        Screen.show(b, PieceColor.BLACK);
        Screen.showAlivePieces(b);
        Screen.showDeletePieces(b.getStore());
    }

    private void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Ajedrez")))) {
            oos.writeObject(b.getBefore());
            oos.writeObject(b.getAfter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Ajedrez")))) {
            Stack<Movements> myStack = (Stack<Movements>) ois.readObject();
            Stack<Movements> aux = new Stack<Movements>();
            for (Movements m : myStack) {
                aux.push(m);
            }
            for (Movements m : aux) {
                b.moveToThatPosition(m.getOrigen(), m.getDestino());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
