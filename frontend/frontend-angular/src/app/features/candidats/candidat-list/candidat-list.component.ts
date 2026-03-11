import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CandidatApiService } from '../../../core/services/candidat-api.service';
import { Candidat } from '../../../shared/models/candidat.model';

@Component({
  selector: 'app-candidat-list',
  templateUrl: './candidat-list.component.html'
})
export class CandidatListComponent implements OnInit {
  candidats: Candidat[] = [];
  page = 0;
  size = 20;

  filters = this.fb.group({ province: [''], centre: [''], sexe: [''], categorie: [''], cin: [''], nom: [''], prenom: [''] });

  constructor(private api: CandidatApiService, private fb: FormBuilder) {}

  ngOnInit(): void { this.search(); }

  search(): void {
    const payload = this.filters.value as Record<string, string>;
    this.api.filterCandidats(payload, this.page, this.size).subscribe((res) => (this.candidats = res.content));
  }
}