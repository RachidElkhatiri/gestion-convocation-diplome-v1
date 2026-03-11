import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { CandidatsRoutingModule } from './candidats-routing.module';
import { UploadComponent } from './upload/upload.component';
import { CandidatGlobalListComponent } from './candidat-global-list/candidat-global-list.component';
import { CandidatListComponent } from './candidat-list/candidat-list.component';
import { CandidatDetailComponent } from './candidat-detail/candidat-detail.component';

@NgModule({
  declarations: [UploadComponent, CandidatGlobalListComponent, CandidatListComponent, CandidatDetailComponent],
  imports: [CommonModule, ReactiveFormsModule, CandidatsRoutingModule]
})
export class CandidatsModule {}