import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ranking-component',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss'],
})
export class RankingComponent {
  elements: any = [
    {rank: 1, name: 'Cagatay Özyurt', time: '1:23:3456', date: '12.02.2021'},
    {rank: 2, name: 'Jacob Thornton', time: '1:25:1234', date: '21.04.2021'},
    {rank: 3, name: 'Larry the Bird', time: '1:32:2345', date: '15.06.2021'},
  ];

  headElements = ['Rank', 'Name', 'Time', 'Date'];

}
