import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from './components/navbar/navbar';
import { ToastComponent } from './components/toast/toast';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar, ToastComponent],
  template: `
    <app-navbar></app-navbar>
    <app-toast></app-toast>
    <router-outlet></router-outlet>
  `,
  styleUrl: './app.scss'
})
export class App {
  title = 'Hospital Food Charity';
}
