package com.chess_fsm.chess.game.dto;

import lombok.Data;

@Data
public class moveDTO {
    private String move;

    public moveDTO(String string) {
        this.move = string;
    }

}
