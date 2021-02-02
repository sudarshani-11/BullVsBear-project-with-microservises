import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApicallingService } from '../services/apicalling.service';
import {News} from '../common/news'
import {user,usermail} from '../common/user'
import {Quote,prices,HistoryData,watchlistObj} from '../common/quote'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { formatDate } from '@angular/common';
@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.css']
})
export class StocksComponent implements OnInit {
 searchform:FormGroup;
  constructor(private apiService:ApicallingService,private router:Router) { }
  quotes:Quote[];
  newsMany:News[];
  selected:Quote;
  price:prices[];
  qprice:prices;
  email:usermail
  user:user;
 isCollapsed : boolean=true;
  ngOnInit() {
    this.searchform = new FormGroup(
      {
        search : new FormControl('')
      }
    )
  }


onSubmit()
{
  
  this.apiService.getQuoteBySearch(this.searchform.get('search').value).subscribe(
    data =>{
      console.log(data);
      this.quotes = data.quotes;
      this.newsMany = data.news;
      console.log("The data from api");

    }
  )
  
  
}

ClickedRow(i)
{
  this.isCollapsed=false;
  console.log(this.quotes[i]);
  const index= i;
  this.selected=this.quotes[i];
  var sym=this.selected.symbol;
  
  this.apiService.getQuote(sym).subscribe(
    data=>{
        console.log(data);
        this.price=data.quoteResponse.result;
        console.log("result data");
        console.log(this.price);
        this.qprice=this.price[0];
        console.log(this.qprice);
        
           })
  
  
}
AddToWatchlist()
{
  if(this.apiService.isLoggedIn){
   let watchlist = new watchlistObj(this.selected.exchange,this.selected.shortname,this.selected.quoteType,
    this.selected.symbol,this.qprice.currency,this.selected.typeDisp,this.selected.longname,this.qprice.regularMarketPrice)
  console.log("watchlist obj"+JSON.stringify(watchlist))  
    this.email=this.apiService.getMail();
  if(this.email != null){
  this.apiService.getUserDetails(this.email).subscribe(
    result=>{
      console.log(result)
       }
     );
      }
    else
    {
      this.router.navigate(["/login"]);
    }
  }

}
}
