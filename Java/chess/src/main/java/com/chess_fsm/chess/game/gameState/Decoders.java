package com.chess_fsm.chess.game.gameState;

import com.chess_fsm.chess.game.dto.Move;
import com.chess_fsm.chess.game.dto.moveDTO;

public class Decoders {
    public static Move moveDecoder(String move) {
        final char[] xAxis = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
        final char[] yAxis = { '1', '2', '3', '4', '5', '6', '7', '8' };
        Move mv = new Move(
                new int[] { new String(xAxis).indexOf(move.charAt(0)), new String(yAxis).indexOf(move.charAt(1)) },
                new int[] { new String(xAxis).indexOf(move.charAt(2)), new String(yAxis).indexOf(move.charAt(3)) });
        return mv;
    }

    public static moveDTO moveEncoder(Move move) {
        final char[] xAxis = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
        final char[] yAxis = { '1', '2', '3', '4', '5', '6', '7', '8' };
        StringBuilder mv = new StringBuilder();
        mv.append(xAxis[move.getPieceAt()[0]]);
        mv.append(yAxis[move.getPieceAt()[1]]);
        mv.append(xAxis[move.getMoveTo()[0]]);
        mv.append(yAxis[move.getMoveTo()[1]]);
        return new moveDTO(mv.toString());
    }
}
