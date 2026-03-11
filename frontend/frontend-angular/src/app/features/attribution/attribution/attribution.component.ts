import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AttributionApiService } from '../../../core/services/attribution-api.service';
import { ToastService } from '../../../shared/services/toast.service';
import { AttributionRunRequest } from '../../../shared/models/attribution.model';

@Component({ selector: 'app-attribution', templateUrl: './attribution.component.html' })
export class AttributionComponent {
  runId = '';
  form = this.fb.group({ startDate: [''], categorie: [''], processByCategory: [false], resetResults: [false] });

  constructor(private fb: FormBuilder, private api: AttributionApiService, private toast: ToastService, private router: Router) {}

  run(): void {
    const payload: AttributionRunRequest = {
      startDate: this.form.value.startDate || undefined,
      categorie: this.form.value.categorie || undefined,
      processByCategory: this.form.value.processByCategory ?? false,
      resetResults: this.form.value.resetResults ?? false
    };
    this.api.run(payload).subscribe((res) => {
      this.runId = res.runId;
      this.toast.success(`Attribution terminée (${res.totalLignesResultat} lignes)`);
      this.router.navigate(['/attribution/results'], { queryParams: { runId: res.runId } });
    });
  }
}
