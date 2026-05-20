import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Menu } from './pages/menu/menu';
import { Donors } from './pages/donors/donors';
import { Admin } from './pages/admin/admin';
import { Login } from './pages/login/login';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'menu', component: Menu },
  { path: 'donors', component: Donors },
  { path: 'login', component: Login },
  { path: 'admin', component: Admin, canActivate: [authGuard] },
  { path: '**', redirectTo: '' }
];
