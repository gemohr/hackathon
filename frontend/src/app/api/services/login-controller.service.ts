/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import Login from '../../models/Login';


/**
 * Login Controller
 */
@Injectable({
  providedIn: 'root',
})
export class LoginControllerService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation loginUsingPost
   */
  static readonly LoginUsingPostPath = '/api/login';

  /**
   * Login.
   *
   * This is used for the controlling the login
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `loginUsingPost()` instead.
   *
   * This method doesn't expect any request body.
   */
  loginUsingPost$Response(params: {

    /**
     * login
     */
    login: Login;
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, LoginControllerService.LoginUsingPostPath, 'post');
    if (params) {
      rb.body(params.login);
    }

    return this.http.request(rb.build({
      responseType: 'text',
      accept: '*/*'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  /**
   * Login.
   *
   * This is used for the controlling the login
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `loginUsingPost$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  loginUsingPost(params: {

    /**
     * login
     */
    login: Login;
  }): Observable<void> {

    return this.loginUsingPost$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
