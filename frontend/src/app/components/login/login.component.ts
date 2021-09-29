import {Component, OnInit} from '@angular/core';
import {LoginControllerService} from "../../api/services/login-controller.service";
import Login from "../../models/Login";
import { FormBuilder } from '@angular/forms';
import FPSConfig = Phaser.Types.Core.FPSConfig;
import {Router} from "@angular/router";


@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  checkoutForm = this.formBuilder.group({
    name: '',
    password: ''
  });

  constructor(private loginService: LoginControllerService, private formBuilder: FormBuilder,
              private _router: Router) {}

  ngOnInit(): void {
  }

  onSubmit(): void {
    const login = new Login(this.checkoutForm.value.name, this.checkoutForm.value.password)
    this.loginService.loginUsingPost$Response({login}).subscribe((data:any) => {
      if(data.status === 200) {
        let json = JSON.parse(data.body)
        console.log(data.body)
        localStorage.setItem('token', json.authToken.token);
        localStorage.setItem('game', JSON.stringify(json.game));
        this._router.navigate(['start'])
      }
    })
  }


}
