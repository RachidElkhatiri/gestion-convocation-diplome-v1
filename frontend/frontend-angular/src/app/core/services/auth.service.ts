import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly tokenKey = 'gcd_token';

  constructor(private router: Router) {}

  login(username: string, password: string): boolean {
    if (!username || !password) {
      return false;
    }
    localStorage.setItem(this.tokenKey, 'demo-jwt-token');
    return true;
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}