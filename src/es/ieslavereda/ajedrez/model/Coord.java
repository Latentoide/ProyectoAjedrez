package es.ieslavereda.ajedrez.model;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author aferrandom
 * Esta es la clase de cordenada, define su puntero "x" y su puntero "y"
 */
public class Coord implements Serializable {
    private char letter;
    private int number;

    public Coord(char letter, int number){
        this.number = number;
        this.letter = String.valueOf(letter).toUpperCase(Locale.ROOT).charAt(0);;
    }

    /**
     * cambiar el hashcode para poder hacer la comparación
     * @return devuelve el número para la ordenaciñón
     */
    @Override
    public int hashCode(){
        return number;
    }

    /**
     * redefinimos el método equals
     * @param o objeto a comparar
     * @return true/false si es igual o no
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Coord){
            Coord aux = (Coord) o;
            return aux.getLetter() == letter && aux.getNumber() == number;
        }else{
            return false;
        }
    }

    /**
     * @return devuelve la letra de la coordenada
     */
    public char getLetter() {
        return letter;
    }

    /**
     * @return devuelve el numero de la coordenada
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return devuelve la coordenada que hay encima suya
     */
    public Coord up(){
        return new Coord(letter, number +1);
    }

    /**
     * @return devuelve la coordenada que hay abajo suya
     */
    public Coord down(){
        return new Coord(letter, number -1);
    }
    /**
     * @return devuelve la coordenada que hay a la derecha suya
     */
    public Coord right(){
        return new Coord((char)((int) letter +1), number);
    }
    /**
     * @return devuelve la coordenada que hay a la izquierda suya
     */
    public Coord left(){
        return new Coord((char)((int) letter -1), number);
    }
    /**
     * @return devuelve la coordenada que hay arriba derecha suya
     */
    public Coord leftUp(){
        return left().up();
    }
    /**
     * @return devuelve la coordenada que hay arriba izquierda suya
     */
    public Coord rightUp(){
        return right().up();
    }
    /**
     * @return devuelve la coordenada que hay abajo izquierda suya
     */
    public Coord rightDown(){
        return right().down();
    }
    /**
     * @return devuelve la coordenada que hay abajo derecha suya
     */
    public Coord leftDown(){
        return left().down();
    }

    public String toString(){
        return "(" + letter + "," + number + ")";
    }




}
