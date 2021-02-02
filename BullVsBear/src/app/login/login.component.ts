
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApicallingService } from '../services/apicalling.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {token,user} from '../common/user'
import { error } from 'protractor';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

 export class LoginComponent implements OnInit {
  authForm: FormGroup;
  isSubmitted  =  false;
  token:token;
  user:user;
 constructor(private apiService:ApicallingService,private router:Router,private formBuilder: FormBuilder){}


  ngOnInit() {
    this.authForm  =  this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
  });
  
}
get formControls() { return this.authForm.controls; }
    signIn(){
      this.isSubmitted = true;

      
    this.apiService.getLoggedIn(this.authForm.value).subscribe(
      result=>{ 
        localStorage.setItem("jwtToken",JSON.stringify(result))
        localStorage.setItem("user",JSON.stringify(this.authForm.get('email').value))
        console.log(this.authForm.get('email').value)
        this.router.navigate(["/home"]);
      },
      error=>
      {
        console.log("error")
      }
    );
      
     
}     
 }
