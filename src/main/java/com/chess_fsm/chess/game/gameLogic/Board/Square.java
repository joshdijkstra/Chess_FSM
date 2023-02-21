package com.chess_fsm.chess.game.gameLogic.Board;

import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;

import lombok.Data;

@Data
public class Square {
  private Piece piece;
  private int x;
  private int y;
  private boolean attackedWhite;
  private boolean attackedBlack;

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void isAttacked(boolean attack, boolean isWhite) {
    if (isWhite) {
      this.setAttackedBlack(attack);
    } else {
      this.setAttackedWhite(attack);
    }
  }

}
