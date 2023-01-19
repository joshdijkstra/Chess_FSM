package com.chess_fsm.chess.game;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class GameObjects {
    private final Board board;
    private final String startingFen = "6k1/3pp3/8/1R7/Q7/8/8/4K3 w KQkq - 0 1";

    public GameObjects() {
        this.board = new Board();
        this.board.initPieces(startingFen);
    }

    public Board getBoard() {
        return board;
    }

}
