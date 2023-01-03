package com.chess_fsm.chess.game.gameLogic.Board;

import org.springframework.stereotype.Service;

@Service
public class BoardService {
    public Board createBoard(String fen){
        Board board = new Board();
        board.initPieces(fen);
        System.out.println(board);
        return board;
    }
}
