package es.ieslavereda.ajedrez.model;

import java.io.Serializable;

public class Movements implements Serializable {
    private Coord origen;
    private Coord destino;
    private Piece piece;

    public Movements(Coord origen, Coord destino){
        this.origen = origen;
        this.destino = destino;
    }

    public Movements(Coord origen, Coord destino, Piece piece){
        this.origen = origen;
        this.destino = destino;
        this.piece = piece;
    }

    /**
     * método que recoge la pieza de ese movimiento
     * @return pieza guardada
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * método que recoge la cordenada destino
     * @return cordenada destino
     */
    public Coord getDestino() {
        return destino;
    }

    /**
     * método que recoge la cordenada origen
     * @return cordenada origen
     */
    public Coord getOrigen() {
        return origen;
    }

    @Override
    public String toString() {
        return  "[Origen= " + origen +
                ", destino= " + destino +
                ", piece= " + piece + "]";
    }
}
