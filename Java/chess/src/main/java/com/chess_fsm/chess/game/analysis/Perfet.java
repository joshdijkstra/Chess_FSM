package com.chess_fsm.chess.game.analysis;

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
        boardService.updateBoard(this.board);

        // if (depth == 0){
        // return board.
        // }

        // n_moves = GenerateLegalMoves(move_list);
        // for (i = 0; i < n_moves; i++) {
        // MakeMove(move_list[i]);
        // nodes += this.run(depth - 1);
        // UndoMove(move_list[i]);
        // }
        return nodes;

    }

}
