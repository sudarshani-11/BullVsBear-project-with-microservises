import { Component, OnInit } from '@angular/core';
//import { QuoteServiceService } from './services/quote-service.service';
import { ApicallingService } from './services/apicalling.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  constructor(private quoteService:ApicallingService){}


  ngOnInit(){
  }

}
