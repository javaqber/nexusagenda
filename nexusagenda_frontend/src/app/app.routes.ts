import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { Agenda } from './agenda/agenda.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'agenda', component: Agenda },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];