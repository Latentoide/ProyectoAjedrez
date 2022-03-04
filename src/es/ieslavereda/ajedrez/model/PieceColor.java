package es.ieslavereda.ajedrez.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

public enum PieceColor implements Serializable {
    BLACK(Attribute.TEXT_COLOR(0,0,0)), WHITE(Attribute.TEXT_COLOR(255,255,255));

    private Attribute color;
    PieceColor(Attribute color){
        this.color = color;
    }

    public Attribute getAttribute(){
        return color;
    }

    public PieceColor next(){
        return PieceColor.values()[((ordinal()+1)%PieceColor.values().length)];
    }
}
