package com.chess_fsm.chess.game;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class GameObjects {
    private final Board board;


    public GameObjects() {
        this.board = new Board();
    }

    public Board getBoard() { return board; }
    
}
