import { Component, OnInit } from '@angular/core';
import { GeoApiService } from '../../../core/services/geo-api.service';
import { Province } from '../../../shared/models/geo.model';

@Component({ selector: 'app-province-list', templateUrl: './province-list.component.html' })
export class ProvinceListComponent implements OnInit {
  provinces: Province[] = [];

  constructor(private api: GeoApiService) {}

  ngOnInit(): void { this.load(); }
  load(): void { this.api.getProvinces().subscribe((res) => (this.provinces = res)); }
  remove(id: number): void { this.api.deleteProvince(id).subscribe(() => this.load()); }
}