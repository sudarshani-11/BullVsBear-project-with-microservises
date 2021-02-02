import { Component, OnInit } from '@angular/core';
import{RapidService} from '../rapid.service'
import { ApicallingService } from '../services/apicalling.service';
import { formatDate } from '@angular/common';
import {Quote,prices,HistoryData,watchlistObj} from '../common/quote'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { News } from '../common/news';
import { usermail,useId } from '../common/user';
import { Router } from '@angular/router';
import { ResourceLoader } from '@angular/compiler';
@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
 hdata :HistoryData[];
 charts=[]
  quotes:Quote[];
  newsMany:News[];
  selected:Quote;
  price:prices[];
  qprice:prices;
  searchform:FormGroup;
  isTableCollapsed : boolean=true;
  isHistoryCollapsed : boolean=true;
  isNewsCollapsed : boolean=true;
  isAddWatchlist :boolean=false;
  email:usermail
  user :useId
  constructor(private router:Router,private apiService:ApicallingService) { }

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
GetWatchlist()
{
 
}

ClickedRow(i)
{
  this.isTableCollapsed=false;
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
  this.apiService.getHistory(sym).subscribe(
    data=>{
      console.log(data)
      this.hdata=data.prices
    }
  )
  
}
History(){
  this.isHistoryCollapsed =false;
  this.isNewsCollapsed =true;
    }
AddToWatchlist()
{

   let watchlist = new watchlistObj(this.selected.exchange,this.selected.shortname,this.selected.quoteType,
    this.selected.symbol,this.qprice.currency,this.selected.typeDisp,this.selected.longname,this.qprice.regularMarketPrice)
    this.email=this.apiService.getMail();
    this.apiService.getUserDetails(this.email).subscribe(
      result=>{
        this.user=result.userId
        console.log(result)
        console.log(this.user)
    this.apiService.setQuoteWatchlist(watchlist,this.user).subscribe(
      result=>{console.log(result)}
    )
    alert("Added to watchlist")
      })
}
News(){
  this.isHistoryCollapsed =true;
  this.isNewsCollapsed =false;
}
}