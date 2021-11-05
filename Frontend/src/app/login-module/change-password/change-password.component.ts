import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  successMsg!: String;
  errMsg = '';
  // userData = new LoginUserData();
  passwordForm: FormGroup;
  newPassword = new FormControl('', [Validators.required]);
  confirmPassword = new FormControl('', [Validators.required]);
  currentPassword = new FormControl('', [Validators.required]);
  constructor(
    private router: Router,
    // private loginService: LoginService
  ) {
    this.passwordForm = new FormGroup({
      currentPassword: this.currentPassword,
      newPassword: this.newPassword,
      confirmPassword: this.confirmPassword
    });
  }

  ngOnInit() {
    // this.loginService.userData.subscribe(data => {
    //   if (data !== '') {
    //     const userName = JSON.parse(data);
    //     this.userData.username = userName;
    //   } else {
    //     this.router.navigate(['/logout']);
    //   }
    // });
  }

  changePassword(passwordForm:any) {
    // const isPasswordMatch = this.checkPassword(passwordForm);
    // if (passwordForm.valid && isPasswordMatch && this.checkPattern(passwordForm.value.newPassword)) {
    //   this.userData.password = passwordForm.value.newPassword;
    //   this.userData.currentPassword = passwordForm.value.currentPassword;
    //   this.userData.credentialsNonExpired = true;
    //   this.loginService.changePassword(this.userData).subscribe(resp => {
    //     if (resp.error === undefined) {
    //       this.successMsg = resp.meta.status_message;
    //       setTimeout(() => {
    //         this.router.navigate(['/logout']);
    //       }, 3000);
    //     } else {
    //       setTimeout(() => {
    //         this.errMsg = resp.error.meta.error_message;
    //       }, 3000);
    //     }
    //   });

    // } else if (!isPasswordMatch) {
    //   this.errMsg = 'Passwords did not match';
    //   setTimeout(() => {
    //     this.errMsg = '';
    //   }, 3000);
    // }

  }

  checkPassword(passwordForm:any) {
    if (passwordForm.value.newPassword === passwordForm.value.confirmPassword) {
      return true;
    } else {
      return false;
    }
  }

  checkPattern(password:any) {

    let isPasswordValid = true;

    if (password !== null && password !== undefined) {
      // if length is 8 characters or more, increase strength value
      if (password.length < 8) {
        isPasswordValid = false;
      }

      // if password contains both lower and uppercase characters, increase strength value
      if (!password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) {
        isPasswordValid = false;
      }

      // if it has numbers and characters, increase strength value
      if (!password.match(/([0-9])/)) {
        isPasswordValid = false;
      }

      // if it has one special character, increase strength value
      if (!password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) {
        isPasswordValid = false;
      }

      if (!isPasswordValid) {
        this.errMsg = 'Password should be of minimum 8 characters having Uppercase, Numbers and Special characters.';
        setTimeout(() => {
          this.errMsg = '';
        }, 3000);

      }
    }
    return isPasswordValid;
  }


}
