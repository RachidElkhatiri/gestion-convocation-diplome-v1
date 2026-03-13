import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  links = [
    { label: 'Dashboard', path: '/dashboard' },
    { label: 'Candidats', path: '/candidats' },
    { label: 'Geo', path: '/geo' },
    { label: 'Attribution', path: '/attribution' }
  ];

  docsUrl = 'http://localhost:8080/swagger-ui.html';
}
