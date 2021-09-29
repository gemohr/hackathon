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



/**
 * Basic Error Controller
 */
@Injectable({
  providedIn: 'root',
})
export class BasicErrorControllerService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation errorHtmlUsingGet
   */
  static readonly ErrorHtmlUsingGetPath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingGet$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingGetPath, 'get');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingGet(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingGet$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation errorHtmlUsingPut
   */
  static readonly ErrorHtmlUsingPutPath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingPut()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingPut$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingPutPath, 'put');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingPut$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingPut(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingPut$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation errorHtmlUsingPost
   */
  static readonly ErrorHtmlUsingPostPath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingPost()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingPost$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingPostPath, 'post');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingPost$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingPost(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingPost$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation errorHtmlUsingDelete
   */
  static readonly ErrorHtmlUsingDeletePath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingDelete()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingDelete$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingDeletePath, 'delete');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingDelete$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingDelete(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingDelete$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation errorHtmlUsingOptions
   */
  static readonly ErrorHtmlUsingOptionsPath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingOptions()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingOptions$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingOptionsPath, 'options');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingOptions$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingOptions(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingOptions$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation errorHtmlUsingHead
   */
  static readonly ErrorHtmlUsingHeadPath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingHead()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingHead$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingHeadPath, 'head');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingHead$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingHead(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingHead$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation errorHtmlUsingPatch
   */
  static readonly ErrorHtmlUsingPatchPath = '/error';

  /**
   * errorHtml.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `errorHtmlUsingPatch()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingPatch$Response(params?: {
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, BasicErrorControllerService.ErrorHtmlUsingPatchPath, 'patch');
    if (params) {
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
   * errorHtml.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `errorHtmlUsingPatch$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  errorHtmlUsingPatch(params?: {
  }): Observable<void> {

    return this.errorHtmlUsingPatch$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
