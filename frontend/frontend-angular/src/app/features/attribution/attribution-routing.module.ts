import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttributionComponent } from './attribution/attribution.component';
import { AttributionResultsComponent } from './attribution-results/attribution-results.component';

const routes: Routes = [
  { path: '', component: AttributionComponent },
  { path: 'results', component: AttributionResultsComponent }
];

@NgModule({ imports: [RouterModule.forChild(routes)], exports: [RouterModule] })
export class AttributionRoutingModule {}