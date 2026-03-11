import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { ToastService } from '../../shared/services/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  form = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastService: ToastService,
    private router: Router
  ) {}

  submit(): void {
    if (this.form.invalid) {
      this.toastService.error('Veuillez remplir tous les champs.');
      return;
    }
    const { username, password } = this.form.value;
    const ok = this.authService.login(username || '', password || '');
    if (!ok) {
      this.toastService.error('Identifiants invalides');
      return;
    }
    this.toastService.success('Connexion réussie');
    this.router.navigate(['/dashboard']);
  }
}