import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { GeoRoutingModule } from './geo-routing.module';
import { CentreListComponent } from './centre-list/centre-list.component';
import { CentreFormComponent } from './centre-form/centre-form.component';
import { ProvinceListComponent } from './province-list/province-list.component';
import { ProvinceFormComponent } from './province-form/province-form.component';

@NgModule({
  declarations: [CentreListComponent, CentreFormComponent, ProvinceListComponent, ProvinceFormComponent],
  imports: [CommonModule, ReactiveFormsModule, GeoRoutingModule]
})
export class GeoModule {}