import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApicallingService } from '../services/apicalling.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CustomvalidationService } from '../services/customvalidation.service'
import { first } from 'rxjs/operators';
import {user } from '../common/user'
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;

  constructor(private apiService:ApicallingService,private router:Router,private readonly fb: FormBuilder,private customvalidator:CustomvalidationService) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      firstName: ['',[Validators.required,this.customvalidator.firstNameValidator()]],
      lastName: ['',[Validators.required,this.customvalidator.firstNameValidator()]],
      userName:['',[Validators.required,this.customvalidator.userNameValidator()]],
      password: ['',[Validators.required,this.customvalidator.passwordValidator()]],
      email: ['',[Validators.required,this.customvalidator.emailValidator()]]
      
    });
  }
  get registerFormControl() {
    return this.registerForm.controls;
  }
  onSubmit() {
    this.submitted = true;
    if (this.registerForm.valid) {
      console.table(this.registerForm.value);
      this.apiService.getRegister(this.registerForm.value);
      this.router.navigate(["/home"]);
    }
    else
    {
      console.log("Someting went wrong.Please try again")
    }

  }

}
