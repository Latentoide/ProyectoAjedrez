package es.ieslavereda.ajedrez.model;

import java.io.Serializable;

public enum ChessType implements Serializable {
        WHITE_KING('♚', PieceColor.WHITE),
        WHITE_QUEEN('♛', PieceColor.WHITE),
        WHITE_ROOK('♜', PieceColor.WHITE),
        WHITE_BISHOP('♝', PieceColor.WHITE),
        WHITE_KNIGHT('♞', PieceColor.WHITE),
        WHITE_PAWN('♟', PieceColor.WHITE),
        BLACK_KING('♚', PieceColor.BLACK),
        BLACK_QUEEN('♛', PieceColor.BLACK),
        BLACK_ROOK('♜', PieceColor.BLACK),
        BLACK_BISHOP('♝', PieceColor.BLACK),
        BLACK_KNIGHT('♞', PieceColor.BLACK),
        BLACK_PAWN('♟', PieceColor.BLACK);

        private char shape;
        private PieceColor color;

        ChessType(char shape, PieceColor color){
                this.shape = shape;
                this.color = color;
        }

        public char getShape() {
                return shape;
        }

        public PieceColor getColor() {
                return color;
        }
}
