package es.ieslavereda.ajedrez.model;

import java.io.Serializable;

//en des uso
public enum Symbols implements Serializable {
    CORNERUPL('╔'), SIDE('║'), CORNERUPR('╗'), UPBOT('═'),
    UPBAR('╤'), BOTTOMBAR('╧'), CORNERBOTTOML('╚'), CORNERBOTTOMR('╝'),
    LINE('│'), LINEAPART('─'), LINEPULLAPART('┼'), SPACE(' '), SIDEBARR('╢'),
    SIDEBARL('╟');

    public char symbol;
    Symbols(char symbol){
        this.symbol = symbol;
    }

    public String toString() {
        return String.valueOf(symbol);
    }
}
