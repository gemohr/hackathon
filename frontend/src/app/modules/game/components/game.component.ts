import {Component, OnInit} from '@angular/core';
import Phaser from 'phaser';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  phaserGame: Phaser.Game;
  config: Phaser.Types.Core.GameConfig;
  constructor() {
    this.config = {
      type: Phaser.AUTO,
      height: 600,
      width: 800,
      scene: [ MainScene ],
      parent: 'gameContainer',
      physics: {
        default: 'arcade',
        arcade: {
          gravity: { y: 500 }
        }
      }
    };
  }
  ngOnInit() {
    this.phaserGame = new Phaser.Game(this.config);
  }
}

class MainScene extends Phaser.Scene {
  map: Phaser.Tilemaps.Tilemap;
  player: Phaser.GameObjects.Sprite;
  //cursors: ;
  //groundLayer;
  //coinLayer;
  //text;

  constructor() {
    super({ key: 'main' });
  }
  create() {/*
    // load the map
    this.map = this.make.tilemap({key: 'map'});

    // tiles for the ground layer
    var groundTiles = this.map.addTilesetImage('tiles');
    // create the ground layer
    groundLayer = map.createDynamicLayer('World', groundTiles, 0, 0);
    // the player will collide with this layer
    groundLayer.setCollisionByExclusion([-1]);
    // set the boundaries of our game world
    this.physics.world.bounds.width = groundLayer.width;
    this.physics.world.bounds.height = groundLayer.height;
    console.log('create method');*/
  }
  preload() {
    this.load.tilemapTiledJSON("map","../../../../assets/game/map.json")
    this.load.spritesheet('tiles', '../../../../assets/tiles.png', {frameWidth: 70, frameHeight: 70});
    this.load.image('coin', '../../../../assets/coinGold.png');
    this.load.atlas('player', '../../../../assets/player.png', 'assets/player.json');
  }
  update() {
    console.log('update method');
  }
}
