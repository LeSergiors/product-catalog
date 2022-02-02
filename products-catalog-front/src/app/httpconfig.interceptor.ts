import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import {ToastrService} from "ngx-toastr";

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {


  constructor(
    private toastr: ToastrService
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.log(error.error);

          const errorData = error.error;
          let errorMsg = errorData.error.replace('NegocioException: ', '');
          errorMsg = errorMsg.replace('MissingServletRequestParameterException: ', '');
          errorMsg = errorMsg.replace('MethodArgumentTypeMismatchException: ', '');

          this.toastr.error(errorMsg);

          console.log('this is server side error');
          return throwError(error.error);
        })
      )
  }
}
