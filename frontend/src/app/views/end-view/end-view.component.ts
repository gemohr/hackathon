import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'end-view-component',
  templateUrl: './end-view.component.html',
  styleUrls: ['./end-view.component.scss']
})
export class EndViewComponent implements OnInit {

  constructor(private _router: Router) {}

  ngOnInit(): void {
  }

  navigateToGame() {
    this._router.navigate(['game'])
  }
}
