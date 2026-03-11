import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GeoApiService } from '../../../core/services/geo-api.service';
import { Centre, Province } from '../../../shared/models/geo.model';

@Component({ selector: 'app-province-form', templateUrl: './province-form.component.html' })
export class ProvinceFormComponent implements OnInit {
  id?: number;
  centres: Centre[] = [];
  form = this.fb.group({
    nom: ['', Validators.required],
    code: [''],
    centreId: this.fb.control<number | null>(null, Validators.required),
    actif: [true]
  });

  constructor(private fb: FormBuilder, private api: GeoApiService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.api.getCentres().subscribe((res) => (this.centres = res));
    this.id = Number(this.route.snapshot.paramMap.get('id')) || undefined;
    if (this.id) {
      this.api.getProvince(this.id).subscribe((res) =>
        this.form.patchValue({
          nom: res.nom,
          code: res.code || '',
          centreId: res.centreId,
          actif: res.actif ?? true
        })
      );
    }
  }

  save(): void {
    const payload: Partial<Province> = {
      nom: this.form.value.nom || '',
      code: this.form.value.code || undefined,
      centreId: Number(this.form.value.centreId),
      actif: this.form.value.actif ?? true
    };
    const req$ = this.id ? this.api.updateProvince(this.id, payload) : this.api.createProvince(payload);
    req$.subscribe(() => this.router.navigate(['/geo/provinces']));
  }
}
