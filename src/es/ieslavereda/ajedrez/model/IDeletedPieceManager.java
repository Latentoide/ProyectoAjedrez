package es.ieslavereda.ajedrez.model;

public interface IDeletedPieceManager {
    void add(Piece piece);
    void remove(Piece piece);
    int count(ChessType chessType);
    public Piece getFirst();
}
