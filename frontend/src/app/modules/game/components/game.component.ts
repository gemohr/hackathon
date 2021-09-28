import {Component, OnInit} from '@angular/core';
import Phaser from 'phaser';
import MainScene from "./MainScene";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  phaserGame: Phaser.Game;
  config: Phaser.Types.Core.GameConfig;
  gameEndHandler: Function;

  constructor(private _router: Router, private _route: ActivatedRoute) {
    this.config = {
      type: Phaser.AUTO,
      height: 600,
      width: 800,
      scene: [ MainScene ],
      parent: 'gameContainer',
      pixelArt: true,
      physics: {
        default: 'arcade',
        arcade: {
          gravity: { y: 500 }
        }
      }
    };

    _route.params.subscribe(val => {
      //this.phaserGame.registry.destroy(); // destroy registry
      //this.phaserGame.events.off(); // disable all active events
      //this.phaserGame.scene.; // restart current scene
    });

    //Game End Handler
    this.gameEndHandler = () => {
      this._router.navigate(['end']);
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
