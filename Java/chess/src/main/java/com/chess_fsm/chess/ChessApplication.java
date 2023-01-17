package com.chess_fsm.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chess_fsm.chess.game.analysis.Perfet;
import com.chess_fsm.chess.game.gameLogic.Board.BoardService;

@SpringBootApplication
public class ChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ";
		Perfet perf = new Perfet(fen, new BoardService());
		int depth = perf.run(3);
		System.out.println(depth);
	}

}
