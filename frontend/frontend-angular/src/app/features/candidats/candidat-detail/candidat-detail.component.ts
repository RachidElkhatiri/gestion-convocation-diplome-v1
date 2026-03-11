import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CandidatApiService } from '../../../core/services/candidat-api.service';
import { Candidat } from '../../../shared/models/candidat.model';

@Component({
  selector: 'app-candidat-detail',
  templateUrl: './candidat-detail.component.html'
})
export class CandidatDetailComponent implements OnInit {
  candidat?: Candidat;

  constructor(private route: ActivatedRoute, private api: CandidatApiService) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.api.getCandidat(id).subscribe((res) => (this.candidat = res));
  }
}