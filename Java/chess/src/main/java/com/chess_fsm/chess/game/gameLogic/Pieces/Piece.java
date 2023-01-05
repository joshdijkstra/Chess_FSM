package com.chess_fsm.chess.game.gameLogic.Pieces;

public class Piece {
    public int x;
    public int y;
    public boolean isWhite;
    public PieceType pieceType;

    public Piece(int x, int y, boolean isWhite, PieceType pieceType) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.pieceType = pieceType;
    }

    @Override
    public String toString() {
        return "Piece [x=" + x + ", y=" + y + ", isWhite=" + isWhite + "]";
    }
}
