import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";
import TimeFormatterService from "../../services/timeFormatter.service";
import Game from "../../models/game";
import {GameControllerService} from "../../api/services/game-controller.service";

@Component({
  selector: 'start-component',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {

  image: any;
  fullName: String;
  pos: String;
  time: String;
  elements: Game[]=[];

  constructor(private _router: Router, private timeFormatter: TimeFormatterService, private sanitizer: DomSanitizer,
              private gameController: GameControllerService) {}

  ngOnInit(): void {
    this.readUser()
  }

  readUser() {
    let gameJson = JSON.parse(localStorage.getItem("game")!!)

    this.fullName = gameJson.fullName;

    if(gameJson.pos === 0) this.pos = ''
    else this.pos = gameJson.pos

    if(gameJson.time === 0) this.time = ''
    else this.time = gameJson.time

    let imgSource = gameJson.picture;
    this.image = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${imgSource}`);

    this.gameController.getRankingUsingGet().subscribe((data: any) => {

      let json = JSON.parse(data)

      for(let elem of json) {
        this.elements.push(new Game(elem.id, elem.fullName, elem.pos, elem.time, elem.date, elem.picture, elem.pictureId));
      }
      let game:Game = this.elements.find((element:Game) => element.id === gameJson.id)!!
      if(game.pos === 0) this.pos = ''
      else this.pos = game.pos.toString()

      if(game.time === 0) this.time = ''
      else this.time = this.timeFormatter.formatTime(game.time)
    })
  }

  navigateToGame() {
    this._router.navigate(['game']) }
}
