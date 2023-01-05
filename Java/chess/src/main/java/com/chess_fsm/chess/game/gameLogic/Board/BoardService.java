package com.chess_fsm.chess.game.gameLogic.Board;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.DTO.moveDTO;

@Service
public class BoardService {
    public Board createBoard(String fen){
        Board board = new Board();
        board.initPieces(fen);
        return board;
    }

}
