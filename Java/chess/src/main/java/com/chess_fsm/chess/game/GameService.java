package com.chess_fsm.chess.game;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.BoardService;

@Service
public class GameService {


    private final BoardService boardService;
    private final String startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public GameService(BoardService boardService){
        this.boardService = boardService;
    }

    public String getGameDetails(){
        return "New Game Started";
    }

    public Board startNewGame(){
        return boardService.createBoard(startingFen);
    }
}
