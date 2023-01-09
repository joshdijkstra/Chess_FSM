import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { take } from 'rxjs';
import { Pieces } from '../model/Piece';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent {
  board: any;
  activeElement: any;
  activeX: any;
  activeY: any;
  constructor(private http: HttpClient) {}

  axisVertical = ['1', '2', '3', '4', '5', '6', '7', '8'];
  axisHorizontal = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
  url = 'api/v1/game';

  ngOnInit() {
    this.showBoard();
  }

  public getBoard = () => {
    let header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(this.url, { headers: header });
  };

  public makeMove = () => {
    let header = new HttpHeaders().set('Content-Type', 'application/json');
    let body = {
      pieceAt: [0, 0],
      moveTo: [0, 1],
    };
    return this.http.post(this.url + '/move', { headers: header, body: body });
  };

  public isLegalSquare = (x: number, y: number) => {
    if (this.activeX != null && this.activeY != null) {
      const thisPiece =
        this.board.squares[this.activeX][this.activeY].piece.legalMoves;
      console.log(thisPiece);
      if (x == 3 && y == 2) {
        return true;
      }
    }
    return false;
  };

  public showBoard = () => {
    this.getBoard()
      .pipe(take(1))
      .subscribe({
        next: (res) => {
          this.diplayPieces(res);
        },
        error: () => {
          console.log('error');
        },
      });
  };

  public hasPieceOn = (x: number, y: number) => {
    if (this.board.squares[x][y].piece != null) {
      return true;
    } else {
      return false;
    }
  };

  public lookUpImage = (x: number, y: number) => {
    const colour = this.board.squares[x][y].piece.isWhite ? 'w' : 'b';
    switch (this.board.squares[x][y].piece.pieceType) {
      case Pieces.PAWN:
        return `assets/pawn_${colour}.png`;
      case Pieces.ROOK:
        return `assets/rook_${colour}.png`;
      case Pieces.KNIGHT:
        return `assets/knight_${colour}.png`;
      case Pieces.BISHOP:
        return `assets/bishop_${colour}.png`;
      case Pieces.KING:
        return `assets/king_${colour}.png`;
      case Pieces.QUEEN:
        return `assets/queen_${colour}.png`;
      default:
        return ``;
    }
  };

  public diplayPieces = (res: any) => {
    this.board = res;
    console.log(res);
    return res;
  };

  public grabPiece = (e: MouseEvent) => {
    const element = e.target as HTMLElement;
    const x = e.clientX - 50;
    const y = e.clientY - 50;
    element.style.position = 'absolute';
    element.style.left = `${x}px`;
    element.style.top = `${y}px`;

    this.activeElement = element;
    this.activeX = Math.floor((e.clientX - 263) / 100);
    this.activeY = 7 - Math.abs(Math.ceil((e.clientY - 54 - 800) / 100));
  };

  public movePiece = (e: MouseEvent) => {
    if (this.activeElement) {
      const x = e.clientX - 50;
      const y = e.clientY - 50;
      this.activeElement.style.position = 'absolute';
      this.activeElement.style.left = `${x}px`;
      this.activeElement.style.top = `${y}px`;
    }
  };

  public dropPiece = (e: MouseEvent) => {
    if (this.activeElement) {
      this.activeElement = null;
      this.activeX = null;
      this.activeY = null;
    }
  };
}
