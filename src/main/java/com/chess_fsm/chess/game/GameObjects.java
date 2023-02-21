package com.chess_fsm.chess.game;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class GameObjects {
    private final Board board;
    // private final String startingFen = "6k1/3pp3/8/8/RBQ5/8/8/4K3 w KQkq - 0 1";
    private final String startingFen = "rnbqkbnr/pp1ppppp/8/2p5/4P3/3B1N2/PPPP1PPP/RNBQK2R b KQkq - 1 2";
    // private final String startingFen =
    // "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public GameObjects() {
        this.board = new Board();
        this.board.initPieces(startingFen);
    }

    public Board getBoard() {
        return board;
    }

}
