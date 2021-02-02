import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CustomvalidationService {

  passwordValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('((?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-])(?=.*[A-Z])(?=\\S+$).{8,16})');
      const valid = regex.test(control.value);
      return valid ? null : { invalidPassword: true };
    };
  }
  firstNameValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^[a-zA-Z]{1,20}$');
      const valid = regex.test(control.value);
      return valid ? null : { invalidPassword: true };
    };
  }
  userNameValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^[a-z0-9]{1,15}$');
      const valid = regex.test(control.value);
      return valid ? null : { invalidPassword: true };
    };
  }
  emailValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^[a-z0-9+_.-]+@(.+)$');
      const valid = regex.test(control.value);
      return valid ? null : { invalidPassword: true };
    };
  }

}
