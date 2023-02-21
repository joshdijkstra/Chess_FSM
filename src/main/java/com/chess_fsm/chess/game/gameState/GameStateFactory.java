package com.chess_fsm.chess.game.gameState;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import com.chess_fsm.chess.game.GameObjects;
import com.chess_fsm.chess.game.transitions.TransitionPhase;
import com.chess_fsm.chess.player.Player;
import com.chess_fsm.chess.player.PlayerService;

@Component
public class GameStateFactory {
    private final PlayerService playerService;
    GameStateService gameStateService;

    public GameStateFactory(PlayerService playerService) {
        this.playerService = playerService;
    }

    public GameState newGameState(String username) {
        GameObjects gameObjects = new GameObjects();
        return new GameState(new CopyOnWriteArrayList<Player>() {
            {
                playerService.get(username);
            }
        }, username, TransitionPhase.LOGIN, playerService, gameObjects);
    }
}
