import {Component, OnInit} from '@angular/core';
import Phaser from 'phaser';
import MainScene from "./MainScene";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {GameControllerService} from "../../../api/services/game-controller.service";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  phaserGame: Phaser.Game;
  config: Phaser.Types.Core.GameConfig;
  gameEndHandler: Function;

  constructor(private _router: Router, private _route: ActivatedRoute, private gameController: GameControllerService) {
    this.config = {
      type: Phaser.AUTO,
      scale: {
        mode: Phaser.Scale.FIT,
        parent: 'gameContainer',
        autoCenter: Phaser.Scale.CENTER_BOTH,
        width: 800,
        height: 600
      },
      scene: [ MainScene ],
      pixelArt: false,
      antialias: true,
      physics: {
        default: 'arcade',
        arcade: {
          gravity: { y: 500 }
        }
      }
    };

    //Game End Handler
    this.gameEndHandler = () => {
      this.phaserGame.destroy(true)
      this._router.routeReuseStrategy.shouldReuseRoute = () => false;
      this._router.onSameUrlNavigation = 'reload';
      this._router.navigate(["end"]);
    }
  }

  ngOnInit() {
    this.phaserGame = new Phaser.Game(this.config);
    this.initListeners();
  }

  private initListeners(): void {
    this.phaserGame.events.once("finish", this.gameEndHandler, this);
  }
}
