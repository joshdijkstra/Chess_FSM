import { Injectable } from '@angular/core';
import { moveDTO } from './model/moveDTO';

// Declare SockJS and Stomp
declare var SockJS;
declare var Stomp;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  constructor() {
    this.initializeWebSocketConnection();
  }
  public stompClient : any;
  public msg :any ;
  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8080/socket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect({}, function(frame: any) {
      that.stompClient.subscribe('/message', (message: any) => {
        if (message.body) {
          // console.log(JSON.parse(message.body))
          that.msg = JSON.parse(message.body);
        }
      });
    });
  }

  startGame() {
    this.stompClient.send('/app/send/message' , {}, );
  }

  makeMove(move: moveDTO){
    this.stompClient.send('/app/ws-makemove' , {}, move);
  }
}
