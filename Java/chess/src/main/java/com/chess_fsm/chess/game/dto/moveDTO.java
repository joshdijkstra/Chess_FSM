package com.chess_fsm.chess.game.dto;

import lombok.Data;

@Data
public class moveDTO {
    private int[] pieceAt;
    private int[] moveTo;
}
