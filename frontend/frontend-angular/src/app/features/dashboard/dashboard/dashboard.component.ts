import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  cards = [
    { title: 'Candidats', value: 'Import / Transform' },
    { title: 'Géo', value: 'Centres / Provinces' },
    { title: 'Attribution', value: 'Planification automatique' }
  ];
}