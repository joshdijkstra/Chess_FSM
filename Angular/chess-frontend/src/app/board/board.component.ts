import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { take } from 'rxjs';
import { Pieces } from '../model/Piece';
import { moveDTO } from '../model/moveDTO';

declare var SockJS;
declare var Stomp;
@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent {
  board: any;
  activeElement: any;
  originalX: any;
  originalY: any;
  activeX: any;
  activeY: any;
  offsetTop: any;
  offsetLeft: any;
  constructor(private http: HttpClient) {
    this.initializeWebSocketConnection();
  }
  public stompClient: any;
  public msg: any;
  public callback: any;

  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8080/socket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect(
      { login: 'mylogin', passcode: 'mypasscode' },
      function (frame: any) {
        that.stompClient.subscribe('/message', (message: any) => {
          if (message.body) {
            // console.log(JSON.parse(message.body))
            that.diplayPieces(JSON.parse(message.body));
          }
        });
        that.stompClient.send('/app/send/message', {});
      }
    );
  }

  axisVertical = ['1', '2', '3', '4', '5', '6', '7', '8'];
  axisHorizontal = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
  url = 'api/v1/game';

  ngOnInit() {
    // this.showBoard();
    const chessboard = document.getElementById('chessboard');
    if (chessboard != undefined) {
      this.offsetLeft = chessboard?.offsetLeft;
      this.offsetTop = chessboard?.offsetTop;
    }
  }

  // public getBoard = () => {
  //   let header = new HttpHeaders().set('Content-Type', 'application/json');
  //   return this.http.get(this.url, { headers: header });
  // };

  public makeMove = (atX: number, atY: number, toX: number, toY: number) => {
    const requestBody: moveDTO = {
      pieceAt: [atX, atY],
      moveTo: [toX, toY],
    };
    this.stompClient.send('/app/ws-makemove', {}, JSON.stringify(requestBody));
  };

  public isSquareAttacked = (x: number, y: number) => {
    console.log(this.board.squares[x][y].attackedWhite);
    return this.board.squares[x][y].attackedWhite;
  };

  public isLegalSquare = (x: number, y: number) => {
    if (
      this.activeX != null &&
      this.activeY != null &&
      this.board != undefined
    ) {
      const moves =
        this.board.squares[this.activeX][this.activeY].piece.legalMoves;
      const includesArray = (data: any[], arr: any[]) => {
        return data.some(
          (e) => Array.isArray(e) && e.every((o, i) => Object.is(arr[i], o))
        );
      };
      return includesArray(moves, [x, y]);
    }

    return false;
  };

  // public showBoard = () => {
  //   this.getBoard()
  //     .pipe(take(1))
  //     .subscribe({
  //       next: (res) => {
  //         this.diplayPieces(res);
  //       },
  //       error: () => {
  //         console.log('error');
  //       },
  //     });
  // };

  public hasPieceOn = (x: number, y: number) => {
    if (this.board != undefined && this.board.squares[x][y].piece != null) {
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
    return res;
  };

  public grabPiece = (e: MouseEvent) => {
    const element = e.target as HTMLElement;
    this.originalX = element.offsetLeft;
    this.originalY = element.offsetTop;
    const x = e.clientX - 50;
    const y = e.clientY - 50;
    element.style.position = 'absolute';
    element.style.left = `${x}px`;
    element.style.top = `${y}px`;

    this.activeElement = element;
    this.activeX = Math.floor((e.clientX - this.offsetLeft) / 100);
    this.activeY =
      7 - Math.abs(Math.ceil((e.clientY - this.offsetTop - 800) / 100));
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
      const targetX = Math.floor((e.clientX - this.offsetLeft) / 100);
      const targetY =
        7 - Math.abs(Math.ceil((e.clientY - this.offsetTop - 800) / 100));
      if (this.isLegalSquare(targetX, targetY)) {
        this.makeMove(this.activeX, this.activeY, targetX, targetY);
      } else {
        this.activeElement.style.position = 'absolute';
        this.activeElement.style.left = `${this.originalX}px`;
        this.activeElement.style.top = `${this.originalY}px`;
      }
      this.activeElement = null;
      this.activeX = null;
      this.activeY = null;
      this.originalX = null;
      this.originalY = null;
    }
  };
}
