package com.chess_fsm.chess.game.dto;

import lombok.Data;

@Data
public class Move {
    private int[] pieceAt;
    private int[] moveTo;
}
