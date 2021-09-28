import Phaser from "phaser";
import Player from "./Player";
import TilemapLayer = Phaser.Tilemaps.TilemapLayer;
import TiledObject = Phaser.Types.Tilemaps.TiledObject;
import StaticGroup = Phaser.Physics.Arcade.StaticGroup;

/**
 * A class that extends Phaser.Scene and wraps up the core logic for the platformer level.
 */
export default class MainScene extends Phaser.Scene {
  player: Player;
  groundLayer: TilemapLayer;
  isPlayerDead: Boolean;
  spikeGroup: StaticGroup;
  endPoint: TiledObject;

  preload() {
    this.load.spritesheet(
      "player",
      "../../../../assets/game/spritesheets/0x72-industrial-player-32px-extruded.png",
      {
        frameWidth: 32,
        frameHeight: 32,
        margin: 1,
        spacing: 2
      }
    );
    this.load.image("spike", "../../../../assets/game/spritesheets/0x72-industrial-spike.png");
    this.load.image(
      "tiles",
      "../../../../assets/game/tilesets/0x72-industrial-tileset-32px-extruded.png"
    );
    this.load.tilemapTiledJSON(
      "map",
      "../../../../assets/game/tilemaps/platformer.json"
    );
  }

  create() {
    this.isPlayerDead = false;

    const map = this.make.tilemap({ key: "map" });
    const tiles = map.addTilesetImage(
      "0x72-industrial-tileset-32px-extruded",
      "tiles"
    );

    map.createLayer("Background", tiles);
    this.groundLayer = map.createLayer("Ground", tiles);
    map.createLayer("Foreground", tiles);

    // Instantiate a player instance at the location of the "Spawn Point" object in the Tiled map.
    // Note: instead of storing the player in a global variable, it's stored as a property of the
    // scene.
    const spawnPoint: TiledObject = map.findObject(
      "Objects",
      obj => obj.name === "Spawn Point"
    );

    this.endPoint = map.findObject(
      "Objects",
      obj => obj.name === "End Point"
    );

    this.player = new Player(this, spawnPoint.x!!, spawnPoint.y!!);

    // Collide the player against the ground layer - here we are grabbing the sprite property from
    // the player (since the Player class is not a Phaser.Sprite).
    this.groundLayer.setCollisionByProperty({ collides: true });
    this.physics.world.addCollider(this.player.sprite, this.groundLayer);

    // The map contains a row of spikes. The spike only take a small sliver of the tile graphic, so
    // if we let arcade physics treat the spikes as colliding, the player will collide while the
    // sprite is hovering over the spikes. We'll remove the spike tiles and turn them into sprites
    // so that we give them a more fitting hitbox.
    this.spikeGroup = this.physics.add.staticGroup();
    this.groundLayer.forEachTile((tile) => {
      if (tile.index === 77) {
        const spike = this.spikeGroup.create(
          tile.getCenterX(),
          tile.getCenterY(),
          "spike"
        );

        // The map has spikes rotated in Tiled (z key), so parse out that angle to the correct body
        // placement
        spike.rotation = tile.rotation;
        if (spike.angle === 0) spike.body.setSize(32, 6).setOffset(0, 26);
        else if (spike.angle === -90)
          spike.body.setSize(6, 32).setOffset(26, 0);
        else if (spike.angle === 90) spike.body.setSize(6, 32).setOffset(0, 0);

        this.groundLayer.removeTileAt(tile.x, tile.y);
      }
    });

    this.cameras.main.startFollow(this.player.sprite);
    this.cameras.main.setBounds(0, 0, map.widthInPixels, map.heightInPixels);

    // Help text that has a "fixed" position on the screen
    this.add
      .text(16, 16, "Arrow keys or WASD to move & jump", {
        font: "18px monospace",
        color: "#000000",
        padding: { x: 20, y: 10 },
        backgroundColor: "#ffffff"
      })
      .setScrollFactor(0);
  }

  update(time: number, delta: number) {
    if (this.isPlayerDead) return;

    // Allow the player to respond to key presses and move itself
    this.player.update();

    if (
      this.player.sprite.y > this.groundLayer.height ||
      this.physics.world.overlap(this.player.sprite, this.spikeGroup)
    ) {
      // Flag that the player is dead so that we can stop update from running in the future
      this.isPlayerDead = true;

      const cam = this.cameras.main;
      cam.shake(100, 0.05);
      cam.fade(250, 0, 0, 0);

      // Freeze the player to leave them on screen while fading but remove the marker immediately
      this.player.freeze();

      cam.once("camerafadeoutcomplete", () => {
        this.player.destroy();
        this.scene.restart();
      });
    }

    //End Game condition
    if(this.player.sprite.x > this.endPoint.x!!) {
      this.game.events.emit("finish")
    }
  }
}
