import { Component } from '@angular/core';
import { CandidatApiService } from '../../../core/services/candidat-api.service';
import { ToastService } from '../../../shared/services/toast.service';

@Component({ selector: 'app-upload', templateUrl: './upload.component.html' })
export class UploadComponent {
  selectedFile: File | null = null;

  constructor(private api: CandidatApiService, private toast: ToastService) {}

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.selectedFile = input.files?.[0] || null;
  }

  upload(): void {
    if (!this.selectedFile) return;
    this.api.uploadExcel(this.selectedFile).subscribe({
      next: (res) => this.toast.success(`${res.imported} lignes importées`)
    });
  }

  transform(): void {
    this.api.transform().subscribe({ next: (res) => this.toast.success(`${res.transformed} lignes transformées`) });
  }
}