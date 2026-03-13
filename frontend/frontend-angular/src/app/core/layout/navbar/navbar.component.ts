import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  docsUrl = 'http://localhost:8080/swagger-ui.html';

  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }
}
