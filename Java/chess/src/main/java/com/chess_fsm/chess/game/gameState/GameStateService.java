package com.chess_fsm.chess.game.gameState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.dto.Move;
import com.chess_fsm.chess.game.dto.moveDTO;

@Service
public class GameStateService {
    private final GameStateFactory stateFactory;
    private final Map<String, GameState> gameStateMap = new ConcurrentHashMap<>();

    public GameStateService(GameStateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    public GameState getStateForPlayer(String username) {
        GameState gameState = gameStateMap.get(username);
        if (gameState != null) {
            return gameState;
        }

        gameState = stateFactory.newGameState(username);
        gameStateMap.put(username, gameState);
        return gameState;
    }

    public void saveStateForPlayer(String username, GameState gameState) {
        gameStateMap.put(username, gameState);
    }

}
