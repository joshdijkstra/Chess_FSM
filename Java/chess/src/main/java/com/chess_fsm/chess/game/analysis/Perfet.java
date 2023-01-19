package com.chess_fsm.chess.game.analysis;

import java.util.List;

import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.BoardService;

public class Perfet {
    public Board board;
    public String fen;
    public BoardService boardService;

    public Perfet(String fen, BoardService boardService) {
        this.board = new Board();
        this.fen = fen;
        this.board.initPieces(fen);
        this.boardService = boardService;
    }

    public int run(int depth) {
        int nodes = 0;
        if (depth == 0) {
            return 1;
        }
        boardService.updateBoard(this.board);

        List<String> moves = this.board.getLegalMoves();
        for (String move : moves) {
            boardService.makeMove(board, move);
            nodes += this.run(depth - 1);
            // this.board = boardService.undoMove(board);
            System.out.println(this.board.toString());
            System.out.println("\n\n\n\n");
        }
        return nodes;

    }

}
