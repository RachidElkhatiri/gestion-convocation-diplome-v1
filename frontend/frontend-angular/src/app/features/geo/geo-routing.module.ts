import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CentreListComponent } from './centre-list/centre-list.component';
import { CentreFormComponent } from './centre-form/centre-form.component';
import { ProvinceListComponent } from './province-list/province-list.component';
import { ProvinceFormComponent } from './province-form/province-form.component';

const routes: Routes = [
  { path: 'centres', component: CentreListComponent },
  { path: 'centres/new', component: CentreFormComponent },
  { path: 'centres/:id', component: CentreFormComponent },
  { path: 'provinces', component: ProvinceListComponent },
  { path: 'provinces/new', component: ProvinceFormComponent },
  { path: 'provinces/:id', component: ProvinceFormComponent },
  { path: '', pathMatch: 'full', redirectTo: 'centres' }
];

@NgModule({ imports: [RouterModule.forChild(routes)], exports: [RouterModule] })
export class GeoRoutingModule {}