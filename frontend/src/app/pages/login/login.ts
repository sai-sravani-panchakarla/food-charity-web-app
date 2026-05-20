import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';
import { ToastService } from '../../services/toast';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  username = '';
  password = '';
  loading = false;
  showPassword = false;
  errors: any = {};

  constructor(
    private authService: AuthService,
    private router: Router,
    private toast: ToastService
  ) {}

  validate(): boolean {
    this.errors = {};
    if (!this.username.trim())
      this.errors.username = 'Username is required';
    if (!this.password.trim())
      this.errors.password = 'Password is required';
    else if (this.password.length < 6)
      this.errors.password = 'Password must be at least 6 characters';
    return Object.keys(this.errors).length === 0;
  }

  login() {
    if (!this.validate()) {
      this.toast.warning('Please fill all required fields!');
      return;
    }

    this.loading = true;
    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        if (res.token) {
          this.authService.saveToken(res.token, res.username);
          this.toast.success('✅ Login successful! Welcome ' + res.username);
          setTimeout(() => this.router.navigate(['/admin']), 1000);
        } else {
          this.toast.error(res.message || 'Login failed!');
        }
        this.loading = false;
      },
      error: (err) => {
        this.toast.error('Invalid username or password!');
        this.loading = false;
      }
    });
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }
}
