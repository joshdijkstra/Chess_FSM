package com.chess_fsm.chess.game;

import java.util.Random;

import com.chess_fsm.chess.player.Player;

import lombok.Data;

@Data
public class Game {
    private String id;
    private Player player1;
    private Player player2;
    private GameObjects gameObjects;

    public Game(String id, Player player1, Player player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.gameObjects = new GameObjects();
    }

    // Create a game waiting for second player
    public Game(String id, Player player1) {
        this.id = id;
        this.player1 = player1;
        this.player2 = null;
        this.gameObjects = new GameObjects();
    }

    public void addPlayer(Player player) {
        if (player1 == null) {
            player1 = player;
        } else if (player2 == null) {
            player2 = player;
        }
    }

    public void startGame() {
        // Assign each player to a color.
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 0) {
            player1.setColour("black");
            player2.setColour("white");
        } else {
            player1.setColour("white");
            player2.setColour("black");
        }

    }
}
