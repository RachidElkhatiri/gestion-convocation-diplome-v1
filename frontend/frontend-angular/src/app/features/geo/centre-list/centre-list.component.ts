import { Component, OnInit } from '@angular/core';
import { GeoApiService } from '../../../core/services/geo-api.service';
import { Centre } from '../../../shared/models/geo.model';

@Component({ selector: 'app-centre-list', templateUrl: './centre-list.component.html' })
export class CentreListComponent implements OnInit {
  centres: Centre[] = [];

  constructor(private api: GeoApiService) {}

  ngOnInit(): void { this.load(); }
  load(): void { this.api.getCentres().subscribe((res) => (this.centres = res)); }
  remove(id: number): void { this.api.deleteCentre(id).subscribe(() => this.load()); }
}