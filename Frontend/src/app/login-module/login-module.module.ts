import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LoginmoduleComponent } from './loginmodule.component';
import { LoginRoutingModule } from './login-routing.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    LoginComponent,
    LogoutComponent,
    ChangePasswordComponent,
    LoginmoduleComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoginRoutingModule
  ]
})
export class LoginModule { }
