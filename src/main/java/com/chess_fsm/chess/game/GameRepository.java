package com.chess_fsm.chess.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.Game;

@Service
public class GameRepository {

    private Map<String, Game> games = new HashMap<>();

    public void addGame(Game game) {
        games.put(game.getId(), game);
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public void removeGame(String id) {
        games.remove(id);
    }

    public List<Game> getGamesByPlayerId(String playerId) {
        List<Game> gamesList = new ArrayList<>();

        for (Game game : games.values()) {
            if (game.getPlayer1().getId().equals(playerId) || (game.getPlayer2() != null && game.getPlayer2().getId().equals(playerId))) {
                gamesList.add(game);
            }
        }
        return gamesList;

    }

    public List<Game> getGamesWithOpenSpots() {
        List<Game> gamesList = new ArrayList<>();

        for (Game game : games.values()) {
            if (game.getPlayer2() == null) {
                gamesList.add(game);
            }
        }
        return gamesList;
    }

}

