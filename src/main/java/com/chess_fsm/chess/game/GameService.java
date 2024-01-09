package com.chess_fsm.chess.game;

import org.springframework.stereotype.Service;
import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.BoardService;
import com.chess_fsm.chess.game.logger.Logger;
import com.chess_fsm.chess.player.Player;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GameService {

    public final String username = "josh" + ThreadLocalRandom.current().nextInt(111, 999);
    private final BoardService boardService;
    private final GameRepository gameRepository;
    private final Logger logger;

    public GameService(BoardService boardService, GameRepository gameRepository, Logger logger) {
        this.boardService = boardService;
        this.gameRepository = gameRepository;
        this.logger = logger;
    }

    public String getGameDetails() {
        return "New Game Started";
    }

    public Game startNewGame(String sessionId) {
        Player player = new Player(sessionId,username);
        List<Game> games = gameRepository.getGamesWithOpenSpots();
        if(games.isEmpty() || games.size() == 0) {
            String id = (ThreadLocalRandom.current().nextInt(1000000000) + "");
            System.out.println("No empty games found, creating new game with id: "+ id );
            Game game = new Game(id, player);
            gameRepository.addGame(game);
            System.out.println(game.getPlayer1());
            return game;

        } else {
            Game game = games.get(0);
            System.out.println("Game found adding player to game with id: "+ game.getId() );
            game.addPlayer(player);
            game.startGame();
            return game;
        }
    }

    public Game makeMove(String sessionId, String moves) {
        System.out.println(moves);
        Game game = gameRepository.getGamesByPlayerId(sessionId).get(0);
        boardService.makeMove(game.getGameObjects().getBoard(), moves);
        logger.logMove(moves, username);
        boardService.updateBoard(game.getGameObjects().getBoard());
        return game;
    }
}
