package com.chess_fsm.chess.game.gameLogic;

public class Piece {
    public int x;
    public int y;
    public boolean isWhite;

    public Piece(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }
}
