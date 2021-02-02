import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class RapidService {

  constructor(private httpClient:HttpClient,private router:Router) { }
 
gethistory()
{
  const url="https://apidojo-yahoo-finance-v1.p.rapidapi.com//stock/v3/get-historical-data?symbol=AMRN&region=US";
  let headers = new HttpHeaders();
  headers = headers.set('X-rapidapi-key', '96f3768e62msh0f6044a5745ee71p1ab7d4jsn2e5b8fe1a3b5').set('X-rapidapi-host','apidojo-yahoo-finance-v1.p.rapidapi.com');
      return this.httpClient.get<any>(url, {headers: headers});

}

}