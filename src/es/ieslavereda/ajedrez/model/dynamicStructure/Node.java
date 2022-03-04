package es.ieslavereda.ajedrez.model.dynamicStructure;

import es.ieslavereda.ajedrez.model.Piece;

public class Node {
    private Piece info;
    private Node next;
    private Node previous;

    public Node(Piece info){
        this.info = info;
        this.next = null;
        this.previous = null;
    }

    /**
     * Método que permite setear el siguiente nodo a este
     * @param next Siguiente nodo
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Método que permite setear el anterior nodo a este
     * @param previous Anterior nodo
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Método que permite conocer la información que contiene
     * @return devuelve en este caso pieza
     */
    public Piece getInfo() {
        return info;
    }


    /**
     * Método que permite conocer el siguiente nodo
     * @return devuelve el siguiente nodo
     */
    public Node getNext() {
        return next;
    }

    /**
     * Método que permite conocer el nodo antrior
     * @return devuelve el nodo anterior
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * Método que pasa a String la información del nodo
     * @return la string de la información
     */
    @Override
    public String toString() {
        return info.toString();
    }
}
