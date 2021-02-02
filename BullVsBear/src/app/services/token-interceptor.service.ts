import { HttpInterceptor } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { ApicallingService } from './apicalling.service';
import {token} from '../common/user'
@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {
  constructor(private injector: Injector) { }

  intercept(request, next) {
    let apicallingservice = this.injector.get(ApicallingService);
    if (apicallingservice.isLoggedIn()) {
      let tokenizedRequest = request.clone({
        setHeaders: {
          Authorization: `Bearer ${apicallingservice.getToken()}`
        }
      })
      return next.handle(tokenizedRequest)
    } else {
      return next.handle(request);
    }
  }
}
