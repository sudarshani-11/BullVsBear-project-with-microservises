import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
import { AuthenticationRequest } from '../common/AuthenticationRequest';
import { News } from '../common/news';
import { Quote ,watchlistObj} from '../common/quote';
import {user , token,usermail,useId} from '../common/user'

@Injectable({
  providedIn: 'root'
})
export class ApicallingService {
token:token;
usermail:usermail
userId:useId
  constructor(private httpClient:HttpClient,private router:Router) { }
  

  private baseUrl:String = "/api";

  getLoggedIn(authenticationReuest: AuthenticationRequest){
    const URI_Login = this.baseUrl+"/authenticate";
    console.log(authenticationReuest);
    return this.httpClient.post<any>(URI_Login,authenticationReuest);
  }
  getRegister(user:user){
    const URI_Register=this.baseUrl+"/register";
    console.log(user);
    this.httpClient.post<any>(URI_Register,user) .subscribe(
    _data=>{
      console.log(_data)
        alert("success");
      }
    )
   }

   getUserDetails(email:usermail):Observable<any>
   {
     const URI_Details=this.baseUrl+"/dbservice/getUserDetails/"+email;
     return this.httpClient.get<any>(URI_Details);
   }

  isLoggedIn(){
    return !!localStorage.getItem("jwtToken");
  }

  getQuoteBySearch(stock:string):Observable<SearchResponse>{
    const URI_Search = this.baseUrl+"/stockservice/search/"+stock;
    return this.httpClient.get<SearchResponse>(URI_Search);
  }

  getQuote(stock:string):Observable<any>{
    const URI_Search = this.baseUrl+"/stockservice/quote/"+stock;
    return this.httpClient.get<SearchResponse>(URI_Search);
  }
  setQuoteWatchlist(watchlist:watchlistObj,useId:useId)
  {
    console.log(useId)
    const URI_Watchlist = this.baseUrl+"/dbservice/addUserQuote/"+useId
     console.log("IN API of Watchlist"+watchlist+"By email"+useId)
     return this.httpClient.put<any>(URI_Watchlist,watchlist);
  }
  
  getHistory(stock:string):Observable<any>{
    const URI_Search = this.baseUrl+"/stockservice/history/"+stock;
    return this.httpClient.get<SearchResponse>(URI_Search);
  }
  getToken(){
    this.token=JSON.parse(localStorage.getItem("jwtToken"));
    return this.token.jwtToken;
  }
  getMail()
  {
    this.usermail=JSON.parse(localStorage.getItem("user"))
    console.log(JSON.parse(localStorage.getItem("user")))
    console.log("IN Api"+this.usermail)
    return this.usermail;  
  }
  logout(){
    localStorage.removeItem("jwtToken")
    localStorage.removeItem("user")
    this.router.navigate(["/login"]);
  }
}

interface SearchResponse{
  quotes: Quote[];
  news: News[];
}