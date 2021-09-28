import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {LoginComponent} from "./components/login/login.component";
import {MDBBootstrapModule } from 'angular-bootstrap-md';
import {StartComponent} from "./components/start/start.component";
import {RankingComponent} from "./components/ranking/ranking.component";
import {StartViewComponent} from "./views/start-view/start-view.component";
import {EndViewComponent} from "./views/end-view/end-view.component";
import {GameModule} from "./modules/game/game.module";
import {APP_BASE_HREF} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StartComponent,
    RankingComponent,
    StartViewComponent,
    EndViewComponent
  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    GameModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: "/frontend"}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
