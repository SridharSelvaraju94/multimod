import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import * as $ from 'jquery';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errMsg = '';
  successMsg = '';
  username = new FormControl('', [Validators.required]);
  password = new FormControl('', [Validators.required]);
  loginForm: FormGroup;
  forgetForm: FormGroup;
  constructor() { 
    this.loginForm = new FormGroup({
      username: this.username,
      password: this.password,
      rememberMe: new FormControl(false),
    });
    this.forgetForm = new FormGroup({
      username: this.username,
    });
  }

  ngOnInit(): void {
    $('.forget-part').hide();
    setTimeout(() => {
      try {
        // this.autoLogin();
      } catch (e) {
        console.error(e);
      }
    }, 300);
  }

  autoLogin() {
    // if (this.cookieService.check(config.session_cookie_name)) {
    //   return this.authService.refreshToken().subscribe((newToken: string) => {
    //     if (newToken) {
    //       return this.router.navigate(['/home']);
    //     }
    //     // If we don't get a new token, we are in trouble so logout.
    //     this.router.navigate(['/logout']);
    //   });
    // }
  }


  login(loginForm: any) {
    // if (loginForm.valid) {
    //   const rememberMe = loginForm.value.rememberMe;
    //   this.loginModel = loginForm.value;
    //   this.loginService.loginUser(this.loginModel).subscribe((resp) => {
    //     if (resp.error !== undefined) {
    //       if (resp.error.error_description === 'Bad credentials') {
    //         this.errMsg = 'username or password is incorrect';
    //       } else if (resp.error.error_description === 'User credentials have expired') {
    //         this.router.navigate(['/changePassword']);
    //       }
    //     } else if (resp) {
    //       this.authService.currentToken = resp.access_token;
    //       if (rememberMe) {
    //         this.cookieService.set(config.session_cookie_name, resp.refresh_token, config.session_cookie_expiry,
    //           config.session_cookie_path);
    //       } else {
    //         this.cookieService.deleteAll();
    //       }
    //       this.userService.getUserByName().subscribe(userResp => {
    //         if (userResp) {
    //           this.router.navigate(['/home']);
    //         }
    //       });
    //     }
    //   });

    // }
  }

  validateEmail() {
    let isValid = true;
    if (this.username.value === '' || this.username.value === null) {
      this.username.setErrors({ userExists: `User Name is Required` });
    } else {
      if (!this.username.value.match(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/)) {
        isValid = false;
      }
      if (!isValid) {
        this.username.setErrors({ userExists: `Invalid User Name` });
      }
    }
    return isValid;
  }

  forgotPassword() {
    $('.login-part').hide();
    $('.forget-part').show();
  }

  forgetPasswordSubmit(forgetForm: any) {
    // this.loginModel.username = forgetForm.value.username;
    // if (this.loginModel.username !== '' && this.loginModel.username !== null && this.loginModel.username !== undefined) {
    //   this.loginModel.password = null;
    //   this.closeMessage();
    //   this.loginService.forgotPassword(this.loginModel).subscribe(resp => {
    //     if (resp.error === undefined) {
    //       this.successMsg = resp.meta.status_message;
    //     } else {
    //       this.errMsg = resp.error.meta.error_message;
    //     }
    //     setTimeout(() => {
    //       this.closeMessage();
    //     }, 10000);
    //   });
    // } else {
    //   this.errMsg = 'Please enter Email ID to get temporary password';
    //   setTimeout(() => {
    //     this.errMsg = '';
    //   }, 3000);
    // }
  }

  closeMessage() {
    this.errMsg = '';
    this.successMsg = '';
  }

  cancel() {
    this.username.setErrors({ userExists: `` });
    $('.login-part').show();
    $('.forget-part').hide();
  }

  addHighlighter(value: string) {
    $('#' + value).parents('.input-group').find('.input-group-addon').addClass('border-btm');
  }

  removeHighligther(value: string) {
    $('#' + value).parents('.input-group').find('.input-group-addon').removeClass('border-btm');
  }

}
