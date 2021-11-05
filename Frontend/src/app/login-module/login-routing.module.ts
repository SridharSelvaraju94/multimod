import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';

import { LoginmoduleComponent } from './loginmodule.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: '',
    component: LoginmoduleComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'logout', component: LogoutComponent },
      { path: 'changePassword', component: ChangePasswordComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
