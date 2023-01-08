package com.chess_fsm.chess.game.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class legalMoveDTO {
    public List<int[]> moves; 

    public void addMove(int row , int col){
        int[] list = {row,col};
        this.moves.add(list);
    }
}
