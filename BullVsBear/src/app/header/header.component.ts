import { Component, OnInit } from '@angular/core';
import { ApicallingService} from '../services/apicalling.service'
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  constructor(private apiservice:ApicallingService) { }

  ngOnInit() {
     
  }
  logout(){
    this.apiservice.logout();
    console.log("logging out");
  }
  
  

}
