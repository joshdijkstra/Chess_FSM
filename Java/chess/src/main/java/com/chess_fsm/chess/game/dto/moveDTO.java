package com.chess_fsm.chess.game.dto;

import java.util.List;

import lombok.Data;
@Data
public class moveDTO {
    private List<Integer> pieceAt;
    private List<Integer> moveTo;
}
