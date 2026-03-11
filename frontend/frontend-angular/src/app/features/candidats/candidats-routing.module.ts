import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadComponent } from './upload/upload.component';
import { CandidatGlobalListComponent } from './candidat-global-list/candidat-global-list.component';
import { CandidatListComponent } from './candidat-list/candidat-list.component';
import { CandidatDetailComponent } from './candidat-detail/candidat-detail.component';

const routes: Routes = [
  { path: '', component: CandidatListComponent },
  { path: 'upload', component: UploadComponent },
  { path: 'global', component: CandidatGlobalListComponent },
  { path: ':id', component: CandidatDetailComponent }
];

@NgModule({ imports: [RouterModule.forChild(routes)], exports: [RouterModule] })
export class CandidatsRoutingModule {}