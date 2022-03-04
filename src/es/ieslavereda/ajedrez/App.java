package es.ieslavereda.ajedrez;

import es.ieslavereda.ajedrez.model.*;
import es.ieslavereda.ajedrez.Control.Game;

import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        Game g = new Game();


        Board b = new Board();
        //Screen.showAlivePieces(b);
        System.out.println(b.getAllCells().stream()
                .filter(c -> c.getPiece() != null && c.getPiece().getColor() == PieceColor.WHITE)
                .map(c-> c.getPiece().getNextMovements())
                .collect(Collectors.toSet())
        );
    }
}
