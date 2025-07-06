import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError: string | null = null;

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) {
  this.loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
}

onSubmit(): void {
  const { username, password } = this.loginForm.value;
  this.authService.login(username, password).subscribe({
    next: res => {
      const role = this.authService.getRole();
      if (role === 'DOCTOR') {
        this.router.navigate(['/doctor/dashboard']);
      } else {
        this.router.navigate(['/patient/dashboard']);
      }
    },
    error: () => {
      this.loginError = 'Invalid username or password.';
    }
  });
  }
}
