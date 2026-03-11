import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AttributionRoutingModule } from './attribution-routing.module';
import { AttributionComponent } from './attribution/attribution.component';
import { AttributionResultsComponent } from './attribution-results/attribution-results.component';

@NgModule({ declarations: [AttributionComponent, AttributionResultsComponent], imports: [CommonModule, ReactiveFormsModule, AttributionRoutingModule] })
export class AttributionModule {}