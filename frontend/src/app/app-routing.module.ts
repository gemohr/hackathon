import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {StartViewComponent} from "./views/start-view/start-view.component";
import {EndViewComponent} from "./views/end-view/end-view.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'start', component: StartViewComponent },
  { path: 'end', component: EndViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
