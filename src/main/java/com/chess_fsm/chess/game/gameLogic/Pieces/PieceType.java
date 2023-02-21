package com.chess_fsm.chess.game.gameLogic.Pieces;

import lombok.Getter;

@Getter
public enum PieceType {
    PAWN("Pawn"),
    ROOK("Rook"),
    BISHOP("Bishop"),
    KNIGHT("Knight"),
    KING("King"),
    QUEEN("Queen");
    
    private final String pieceType;

    PieceType(String pieceType) { this.pieceType = pieceType;}
}
