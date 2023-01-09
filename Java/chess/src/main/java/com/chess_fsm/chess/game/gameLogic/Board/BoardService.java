package com.chess_fsm.chess.game.gameLogic.Board;

import org.springframework.stereotype.Service;

@Service
public class BoardService {
    public Board createBoard(String fen){
        Board board = new Board();
        board.initPieces(fen);
        return board;
    }

    public void updateLegalMoves(Board board){
        Square[][] squares = board.getSquares();
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                if (squares[row][col].getPiece() != null){
                    squares[row][col].getPiece().getLegalMoves(board);

                }
            }
         }
    }

}
