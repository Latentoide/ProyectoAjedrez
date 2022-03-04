package es.ieslavereda.ajedrez.model.dynamicStructure;

import es.ieslavereda.ajedrez.model.dynamicStructure.Node;

import es.ieslavereda.ajedrez.model.ChessType;
import es.ieslavereda.ajedrez.model.IDeletedPieceManager;
import es.ieslavereda.ajedrez.model.Piece;

public class MyList implements IDeletedPieceManager {

    private Node tail;
    private Node head;
    private int size;

    /**
     * Añade una pieza a la lista genérica
     * @param piece pieza a añadir
     */
    @Override
    public void add(Piece piece) {
        Node aux = new Node(piece);
        if(head==null)
            head = aux;
        else{
            aux.setNext(head);
            head = aux;
        }
        size++;
    }

    /**
     * Método que nos proporciana el primer elemento de la lista
     * @return pieza que se encuentra en la primera posición
     */
    public Piece getFirst() {
        if(head == null)
            return null;
        else{
            Piece p = head.getInfo();
            head = head.getNext();
            return p;
        }
    }

    /**
     * Método que nos permite eliminar un elemento
     * @param piece pieza a borrar
     */
    @Override
    public void remove(Piece piece) {
        if(head==null)
            return;
        if(head.getInfo().equals(piece)) {
            head = head.getNext();
            size--;
        }
        else{
            Node aux2=head,aux1=head.getNext();
            while (aux1!=null && aux1.getInfo()!=piece){
                aux2=aux1;
                aux1=aux1.getNext();
            }
        }
    }

    /**
     * Nos devuelve una cuenta de piezas que le pasemos por el método
     * @param chessType tipo de ficha que queremos mirar
     * @return cuenta de las fichas
     */
    @Override
    public int count(ChessType chessType) {

        int count = 0;
        Node aux = head;

        while (aux!=null){
            if(aux.getInfo().getChessType().equals(chessType))
                count++;

            aux = aux.getNext();
        }

        return count;
    }

    /**
     * metodo que nos devuelve lo largo que es la lista
     * @return largo de la lista
     */
    public int getSize() {
        return size;
    }

    /**
     * método que devuelve el primer nodo de la lista
     * @return el primer nodo de la lista head
     */
    public Node getHead() {
        return head;
    }

    /**
     * método que devuelve el último nodo de la lista
     * @return el último nodo de la lista tail
     */
    public Node getTail() {
        return tail;
    }

    /**
     * método que añade elementos por la cabeza
     * @param piece elemento a introducir
     */
    public void addFirst(Piece piece){
        Node aux = new Node(piece);
        if(size == 0){
            tail = aux;
        }else{
            aux.setNext(head);
            head.setPrevious(aux);
        }
        head = aux;
        size++;
    }

    /**
     * método que añade por la cola
     * @param piece elemento a añadir
     */
    public void addLast(Piece piece){
        Node aux = new Node(piece);
        if(size == 0){
            head = aux;
        }else{
            aux.setPrevious(tail);
            tail.setNext(aux);
        }
        tail = aux;
        size++;
    }

    /**
     * método que quita elementos por la cabeza
     * @return la información que hay dentro de ese nodo en este caso una pieza
     */
    public Piece removeFirst(){
        if(size == 0 || head == null){
            return null;
        }
        Node aux = head;
        if(size == 1){
            head = null;
            tail = null;
        }else{

            head = aux.getNext();
            head.setPrevious(null);

        }
        size--;
        return aux.getInfo();
    }

    /**
     * método que quita elementos por la cola
     * @return la información que hay dentro de ese nodo en este caso una pieza
     */
    public Piece removeLast(){
        if(size == 0 || head == null){
            return null;
        }
        Node aux = tail;
        if(size == 1){
            tail = null;
            head = null;

        }else{
            tail = aux.getPrevious();
            tail.setNext(null);
        }
        size--;
        return aux.getInfo();
    }

    /**
     * Método que busca un elemento en la lista, si no existiera daría -1
     * @param piece pieza a buscar
     * @return devuelve -1 si no está o su posición en la lista
     */
    public int search(Piece piece){
        int pos = -1;
        int i = 0;
        Node aux = head;
        while (aux != null && pos == -1){
            if(aux.getInfo() == piece){
                pos = i;
            }
            i++;
            aux = aux.getNext();
        }
        return pos;
    }

    /**
     * Método que busca un elemento en la lista y lo busca
     * @param number pieza a buscar
     * @return la información que hay dentro de ese nodo en este caso una pieza
     */
    public Piece deleteInSearch(int number){
        if(number < 0 || number > size) {
            return null;
        }
        if(number == 0){
            return removeFirst();
        }
        if(number == size-1){
            return removeLast();
        }

        Node aux = head;
        while (number-->0){
            aux = aux.getNext();
        }

        aux.getPrevious().setNext(aux.getNext());
        aux.getNext().setPrevious(aux.getPrevious());

        size--;
        return aux.getInfo();
    }

    /**
     * Método para saber si la lista está vacia
     * @return booleano de esta vacia o no
     */
    public boolean empty(){
        return size == 0;
    }

    /**
     * Método que para poder pasar a string nuestra lista
     * @return nos devuelve la string entera de la lista
     */
    public String toString(){
        String out = "Size: " + size +"\nValues: " ;
        Node aux = head;
        while (aux != null){
            out += aux + " ";
            aux = aux.getNext();
        }
        out += "\nRevers: ";
        Node aux2 = tail;
        while (aux2 != null){
            out += aux2 + " ";
            aux2 = aux2.getPrevious();
        }

        return out;
    }


}


