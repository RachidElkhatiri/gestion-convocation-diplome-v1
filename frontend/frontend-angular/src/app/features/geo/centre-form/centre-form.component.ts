import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GeoApiService } from '../../../core/services/geo-api.service';
import { Centre } from '../../../shared/models/geo.model';

@Component({ selector: 'app-centre-form', templateUrl: './centre-form.component.html' })
export class CentreFormComponent implements OnInit {
  id?: number;
  form = this.fb.group({ nom: ['', Validators.required], capaciteJournaliere: [50, Validators.required], adresse: [''], ville: [''], actif: [true] });

  constructor(private fb: FormBuilder, private api: GeoApiService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id')) || undefined;
    if (this.id) this.api.getCentre(this.id).subscribe((res) => this.form.patchValue(res));
  }

  save(): void {
    const payload: Partial<Centre> = {
      nom: this.form.value.nom || '',
      capaciteJournaliere: Number(this.form.value.capaciteJournaliere || 0),
      adresse: this.form.value.adresse || undefined,
      ville: this.form.value.ville || undefined,
      actif: this.form.value.actif ?? true
    };
    const req$ = this.id ? this.api.updateCentre(this.id, payload) : this.api.createCentre(payload);
    req$.subscribe(() => this.router.navigate(['/geo/centres']));
  }
}
