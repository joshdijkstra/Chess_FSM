package com.chess_fsm.chess.game;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class GameObjects {
    private final Board board;
    private final String startingFen = "rnbqkbnr/1pp1pppp/8/8/6P1/8/1PPPPP1P/RNBQKBNR w KQkq - 0 1";

    public GameObjects() {
        this.board = new Board();
        this.board.initPieces(startingFen);
    }

    public Board getBoard() {
        return board;
    }

}
