import { Component, OnInit } from '@angular/core';
import {GameControllerService} from "../../api/services/game-controller.service";
import Game from "../../models/game";
import TimeFormatterService from "../../services/timeFormatter.service";

@Component({
  selector: 'ranking-component',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss'],
})
export class RankingComponent implements OnInit {

  constructor(private gameController: GameControllerService, private timeFormatter: TimeFormatterService) {
  }

  ngOnInit(): void {
    this.getRankings()
  }

  elements: Game[] = []; /*= [
    {rank: 1, name: 'Cagatay Özyurt', time: '1:23:3456', date: '12.02.2021'},
    {rank: 2, name: 'Jacob Thornton', time: '1:25:1234', date: '21.04.2021'},
    {rank: 3, name: 'Larry the Bird', time: '1:32:2345', date: '15.06.2021'},
  ];*/

  getRankings() {
    this.gameController.getRankingUsingGet().subscribe((data: any) => {

      let json = JSON.parse(data)

      for(let elem of json) {
        this.elements.push(new Game(elem.id, elem.fullName, elem.pos, elem.time, elem.date, elem.picture, elem.pictureId))
      }
    })
  }

  getFormattedTime(element: Game) {
    return this.timeFormatter.formatTime(element.time)
  }

  headElements = ['Pos', 'Name', 'Time', 'Date'];

}
