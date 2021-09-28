import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'start-component',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {

  constructor(private _router: Router) {}

  ngOnInit(): void {
  }

  navigateToGame() {
    this._router.navigate(['game']) }
}
