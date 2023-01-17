package com.chess_fsm.chess.game.gameState;

import java.util.concurrent.CopyOnWriteArrayList;

import com.chess_fsm.chess.game.GameObjects;
import com.chess_fsm.chess.game.transitions.TransitionPhase;
import com.chess_fsm.chess.player.Player;
import com.chess_fsm.chess.player.PlayerService;

import lombok.Data;

@Data
public class GameState {
    private final String username;
    private final TransitionPhase phase;
    private final PlayerService playerService;
    private GameObjects gameObjects;
    private CopyOnWriteArrayList<Player> players;

    public GameState(CopyOnWriteArrayList<Player> players, String username, TransitionPhase phase,
            PlayerService playerService, GameObjects gameObjects) {
        this.players = players;
        this.username = username;
        this.phase = phase;
        this.playerService = playerService;
        this.gameObjects = gameObjects;
    }

}
